package no.fint.consumer.models.fullmakt;

import no.fint.model.resource.Link;
import no.fint.model.resource.administrasjon.fullmakt.FullmaktResource;
import no.fint.model.resource.administrasjon.fullmakt.FullmaktResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;


@Component
public class FullmaktLinker extends FintLinker<FullmaktResource> {

    public FullmaktLinker() {
        super(FullmaktResource.class);
    }

    public void mapLinks(FullmaktResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public FullmaktResources toResources(Collection<FullmaktResource> collection) {
        FullmaktResources resources = new FullmaktResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(FullmaktResource fullmakt) {
        if (!isNull(fullmakt.getSystemId()) && !isEmpty(fullmakt.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(fullmakt.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

