package no.fint.consumer.models.rolle;

import no.fint.model.administrasjon.fullmakt.Rolle;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class RolleAssembler extends FintResourceAssembler<Rolle> {

    public RolleAssembler() {
        super(RolleController.class);
    }


    @Override
    public FintResourceSupport assemble(Rolle rolle , FintResource<Rolle> fintResource) {
        return createResourceWithId(rolle.getNavn().getIdentifikatorverdi(), fintResource, "navn");
    }
    
    
}

