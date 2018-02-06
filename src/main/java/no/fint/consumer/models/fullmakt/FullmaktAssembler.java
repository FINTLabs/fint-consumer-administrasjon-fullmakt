package no.fint.consumer.models.fullmakt;

import no.fint.model.administrasjon.fullmakt.Fullmakt;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class FullmaktAssembler extends FintResourceAssembler<Fullmakt> {

    public FullmaktAssembler() {
        super(FullmaktController.class);
    }


    @Override
    public FintResourceSupport assemble(Fullmakt fullmakt , FintResource<Fullmakt> fintResource) {
        return createResourceWithId(fullmakt.getSystemId().getIdentifikatorverdi(), fintResource, "systemid");
    }
    
    
}

