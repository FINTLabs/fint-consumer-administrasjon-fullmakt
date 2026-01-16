package no.novari.fint.consumer.models.fullmakt;

import no.novari.fint.model.resource.administrasjon.fullmakt.FullmaktResource;
import no.novari.fint.model.resource.administrasjon.fullmakt.FullmaktResources;
import no.novari.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public FullmaktResources toResources(Stream<FullmaktResource> stream, int offset, int size, int totalItems) {
        FullmaktResources resources = new FullmaktResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(FullmaktResource fullmakt) {
        return getAllSelfHrefs(fullmakt).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(FullmaktResource fullmakt) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(fullmakt.getSystemId()) && !isEmpty(fullmakt.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(fullmakt.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(FullmaktResource fullmakt) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(fullmakt.getSystemId()) && !isEmpty(fullmakt.getSystemId().getIdentifikatorverdi())) {
            builder.add(fullmakt.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

