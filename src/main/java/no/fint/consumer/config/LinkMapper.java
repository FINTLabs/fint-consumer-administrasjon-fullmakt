package no.fint.consumer.config;

import no.fint.consumer.utils.RestEndpoints;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import no.fint.model.administrasjon.fullmakt.Fullmakt;
import no.fint.model.administrasjon.fullmakt.Rolle;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String,String>builder()
            .put(Fullmakt.class.getName(), contextPath + RestEndpoints.FULLMAKT)
            .put(Rolle.class.getName(), contextPath + RestEndpoints.ROLLE)
            .put("no.fint.model.administrasjon.kodeverk.Ramme", "/administrasjon/kodeverk/ramme")
            .put("no.fint.model.administrasjon.kodeverk.Funksjon", "/administrasjon/kodeverk/funksjon")
            .put("no.fint.model.administrasjon.kodeverk.Objekt", "/administrasjon/kodeverk/objekt")
            .put("no.fint.model.administrasjon.organisasjon.Organisasjonselement", "/administrasjon/organisasjon/organisasjonselement")
            .put("no.fint.model.administrasjon.kodeverk.Art", "/administrasjon/kodeverk/art")
            .put("no.fint.model.administrasjon.kodeverk.Anlegg", "/administrasjon/kodeverk/anlegg")
            .put("no.fint.model.administrasjon.kodeverk.Diverse", "/administrasjon/kodeverk/diverse")
            .put("no.fint.model.administrasjon.kodeverk.Aktivitet", "/administrasjon/kodeverk/aktivitet")
            .put("no.fint.model.administrasjon.kodeverk.Ansvar", "/administrasjon/kodeverk/ansvar")
            .put("no.fint.model.administrasjon.personal.Personalressurs", "/administrasjon/personal/personalressurs")
            .put("no.fint.model.administrasjon.kodeverk.Kontrakt", "/administrasjon/kodeverk/kontrakt")
            .put("no.fint.model.administrasjon.kodeverk.Prosjekt", "/administrasjon/kodeverk/prosjekt")
            .put("no.fint.model.administrasjon.kodeverk.Formal", "/administrasjon/kodeverk/formal")
            .put("no.fint.model.administrasjon.kodeverk.Lopenummer", "/administrasjon/kodeverk/lopenummer")
            /* .put(TODO,TODO) */
            .build();
    }

}
