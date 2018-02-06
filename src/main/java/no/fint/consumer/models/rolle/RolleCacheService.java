package no.fint.consumer.models.rolle;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import no.fint.cache.CacheService;
import no.fint.consumer.config.Constants;
import no.fint.consumer.config.ConsumerProps;
import no.fint.consumer.event.ConsumerEventUtil;
import no.fint.event.model.Event;
import no.fint.model.relation.FintResource;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import no.fint.model.administrasjon.fullmakt.Rolle;
import no.fint.model.administrasjon.fullmakt.FullmaktActions;

@Slf4j
@Service
public class RolleCacheService extends CacheService<FintResource<Rolle>> {

    public static final String MODEL = Rolle.class.getSimpleName().toLowerCase();

    @Autowired
    private ConsumerEventUtil consumerEventUtil;

    @Autowired
    private ConsumerProps props;

    public RolleCacheService() {
        super(MODEL, FullmaktActions.GET_ALL_ROLLE);
    }

    @PostConstruct
    public void init() {
        Arrays.stream(props.getOrgs()).forEach(this::createCache);
    }

    @Scheduled(initialDelayString = ConsumerProps.CACHE_INITIALDELAY_ROLLE, fixedRateString = ConsumerProps.CACHE_FIXEDRATE_ROLLE)
    public void populateCacheAll() {
        Arrays.stream(props.getOrgs()).forEach(this::populateCache);
    }

    public void rebuildCache(String orgId) {
		flush(orgId);
		populateCache(orgId);
	}

    private void populateCache(String orgId) {
		log.info("Populating Rolle cache for {}", orgId);
        Event event = new Event(orgId, Constants.COMPONENT, FullmaktActions.GET_ALL_ROLLE, Constants.CACHE_SERVICE);
        consumerEventUtil.send(event);
    }


    public Optional<FintResource<Rolle>> getRolleByNavn(String orgId, String navn) {
        Identifikator needle = new Identifikator();
        needle.setIdentifikatorverdi(navn);
        return getOne(orgId, (fintResource) -> needle.equals(fintResource.getResource().getNavn()));
    }


	@Override
    public void onAction(Event event) {
        update(event, new TypeReference<List<FintResource<Rolle>>>() {
        });
    }
}
