package no.fint.consumer.models.rolle;

import no.fint.model.resource.Link;
import no.fint.model.resource.administrasjon.fullmakt.RolleResource;
import no.fint.model.resource.administrasjon.fullmakt.RolleResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;


@Component
public class RolleLinker extends FintLinker<RolleResource> {

    public RolleLinker() {
        super(RolleResource.class);
    }

    public void mapLinks(RolleResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public RolleResources toResources(Collection<RolleResource> collection) {
        RolleResources resources = new RolleResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(RolleResource rolle) {
        if (!isNull(rolle.getNavn()) && !isEmpty(rolle.getNavn().getIdentifikatorverdi())) {
            return createHrefWithId(rolle.getNavn().getIdentifikatorverdi(), "navn");
        }
        
        return null;
    }
    
}

