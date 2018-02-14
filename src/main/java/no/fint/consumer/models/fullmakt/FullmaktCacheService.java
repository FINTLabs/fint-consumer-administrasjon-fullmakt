package no.fint.consumer.models.fullmakt;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import no.fint.cache.CacheService;
import no.fint.consumer.config.Constants;
import no.fint.consumer.config.ConsumerProps;
import no.fint.consumer.event.ConsumerEventUtil;
import no.fint.event.model.Event;
import no.fint.model.administrasjon.fullmakt.Fullmakt;
import no.fint.model.administrasjon.fullmakt.FullmaktActions;
import no.fint.model.relation.FintResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FullmaktCacheService extends CacheService<FintResource<Fullmakt>> {

    public static final String MODEL = Fullmakt.class.getSimpleName().toLowerCase();

    @Autowired
    private ConsumerEventUtil consumerEventUtil;

    @Autowired
    private ConsumerProps props;

    public FullmaktCacheService() {
        super(MODEL, FullmaktActions.GET_ALL_FULLMAKT);
    }

    @PostConstruct
    public void init() {
        Arrays.stream(props.getOrgs()).forEach(this::createCache);
    }

    @Scheduled(initialDelayString = ConsumerProps.CACHE_INITIALDELAY_FULLMAKT, fixedRateString = ConsumerProps.CACHE_FIXEDRATE_FULLMAKT)
    public void populateCacheAll() {
        Arrays.stream(props.getOrgs()).forEach(this::populateCache);
    }

    public void rebuildCache(String orgId) {
		flush(orgId);
		populateCache(orgId);
	}

    private void populateCache(String orgId) {
		log.info("Populating Fullmakt cache for {}", orgId);
        Event event = new Event(orgId, Constants.COMPONENT, FullmaktActions.GET_ALL_FULLMAKT, Constants.CACHE_SERVICE);
        consumerEventUtil.send(event);
    }


    public Optional<FintResource<Fullmakt>> getFullmaktBySystemId(String orgId, String systemId) {
        return getOne(orgId, (fintResource) -> Optional
                .ofNullable(fintResource)
                .map(FintResource::getResource)
                .map(Fullmakt::getSystemId)
                .map(id -> id.equals(systemId))
                .orElse(false));
    }


	@Override
    public void onAction(Event event) {
        update(event, new TypeReference<List<FintResource<Fullmakt>>>() {
        });
    }
}
