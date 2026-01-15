package no.novari.fint.consumer.models.rolle;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

import no.fint.cache.CacheService;
import no.fint.cache.model.CacheObject;
import no.fint.event.model.Event;
import no.fint.event.model.ResponseStatus;

import no.novari.fint.consumer.config.Constants;
import no.novari.fint.consumer.config.ConsumerProps;
import no.novari.fint.consumer.event.ConsumerEventUtil;
import no.novari.fint.model.administrasjon.fullmakt.FullmaktActions;
import no.novari.fint.model.administrasjon.fullmakt.Rolle;
import no.novari.fint.model.felles.kompleksedatatyper.Identifikator;
import no.novari.fint.model.resource.administrasjon.fullmakt.RolleResource;
import no.novari.fint.relations.FintResourceCompatibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@ConditionalOnProperty(name = "fint.consumer.cache.disabled.rolle", havingValue = "false", matchIfMissing = true)
public class RolleCacheService extends CacheService<RolleResource> {

    public static final String MODEL = Rolle.class.getSimpleName().toLowerCase();

    @Value("${fint.consumer.compatibility.fintresource:true}")
    private boolean checkFintResourceCompatibility;

    @Autowired
    private FintResourceCompatibility fintResourceCompatibility;

    @Autowired
    private ConsumerEventUtil consumerEventUtil;

    @Autowired
    private ConsumerProps props;

    @Autowired
    private RolleLinker linker;

    private JavaType javaType;

    private ObjectMapper objectMapper;

    public RolleCacheService() {
        super(MODEL, FullmaktActions.GET_ALL_ROLLE, FullmaktActions.UPDATE_ROLLE);
        objectMapper = new ObjectMapper();
        javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, RolleResource.class);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @PostConstruct
    public void init() {
        props.getAssets().forEach(this::createCache);
    }

    @Scheduled(initialDelayString = Constants.CACHE_INITIALDELAY_ROLLE, fixedRateString = Constants.CACHE_FIXEDRATE_ROLLE)
    public void populateCacheAll() {
        props.getAssets().forEach(this::populateCache);
    }

    public void rebuildCache(String orgId) {
		flush(orgId);
		populateCache(orgId);
	}

    @Override
    public void populateCache(String orgId) {
		log.info("Populating Rolle cache for {}", orgId);
        Event event = new Event(orgId, Constants.COMPONENT, FullmaktActions.GET_ALL_ROLLE, Constants.CACHE_SERVICE);
        consumerEventUtil.send(event);
    }


    public Optional<RolleResource> getRolleByNavn(String orgId, String navn) {
        return getOne(orgId, navn.hashCode(),
            (resource) -> Optional
                .ofNullable(resource)
                .map(RolleResource::getNavn)
                .map(Identifikator::getIdentifikatorverdi)
                .map(navn::equals)
                .orElse(false));
    }


	@Override
    public void onAction(Event event) {
        List<RolleResource> data;
        if (checkFintResourceCompatibility && fintResourceCompatibility.isFintResourceData(event.getData())) {
            log.info("Compatibility: Converting FintResource<RolleResource> to RolleResource ...");
            data = fintResourceCompatibility.convertResourceData(event.getData(), RolleResource.class);
        } else {
            data = objectMapper.convertValue(event.getData(), javaType);
        }
        data.forEach(resource -> {
            linker.mapLinks(resource);
            linker.resetSelfLinks(resource);
        });
        if (FullmaktActions.valueOf(event.getAction()) == FullmaktActions.UPDATE_ROLLE) {
            if (event.getResponseStatus() == ResponseStatus.ACCEPTED || event.getResponseStatus() == ResponseStatus.CONFLICT) {
                List<CacheObject<RolleResource>> cacheObjects = data
                    .stream()
                    .map(i -> new CacheObject<>(i, linker.hashCodes(i)))
                    .collect(Collectors.toList());
                addCache(event.getOrgId(), cacheObjects);
                log.info("Added {} cache objects to cache for {}", cacheObjects.size(), event.getOrgId());
            } else {
                log.debug("Ignoring payload for {} with response status {}", event.getOrgId(), event.getResponseStatus());
            }
        } else {
            List<CacheObject<RolleResource>> cacheObjects = data
                    .stream()
                    .map(i -> new CacheObject<>(i, linker.hashCodes(i)))
                    .collect(Collectors.toList());
            updateCache(event.getOrgId(), cacheObjects);
            log.info("Updated cache for {} with {} cache objects", event.getOrgId(), cacheObjects.size());
        }
    }
}
