package no.novari.fint.consumer.config;

import java.util.Map;
import com.google.common.collect.ImmutableMap;
import no.novari.fint.consumer.utils.RestEndpoints;
import no.novari.fint.model.administrasjon.fullmakt.Fullmakt;
import no.novari.fint.model.administrasjon.fullmakt.Rolle;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String,String>builder()
            .put(Fullmakt.class.getName(), contextPath + RestEndpoints.FULLMAKT)
            .put(Rolle.class.getName(), contextPath + RestEndpoints.ROLLE)
            .put("no.novari.fint.model.administrasjon.kodeverk.Ramme", "/model/administrasjon/kodeverk/ramme")
            .put("no.novari.fint.model.administrasjon.kodeverk.Funksjon", "/model/administrasjon/kodeverk/funksjon")
            .put("no.novari.fint.model.administrasjon.kodeverk.Objekt", "/model/administrasjon/kodeverk/objekt")
            .put("no.novari.fint.model.administrasjon.organisasjon.Organisasjonselement", "/model/administrasjon/organisasjon/organisasjonselement")
            .put("no.novari.fint.model.administrasjon.kodeverk.Art", "/model/administrasjon/kodeverk/art")
            .put("no.novari.fint.model.administrasjon.kodeverk.Anlegg", "/model/administrasjon/kodeverk/anlegg")
            .put("no.novari.fint.model.administrasjon.kodeverk.Diverse", "/model/administrasjon/kodeverk/diverse")
            .put("no.novari.fint.model.administrasjon.kodeverk.Aktivitet", "/model/administrasjon/kodeverk/aktivitet")
            .put("no.novari.fint.model.administrasjon.kodeverk.Ansvar", "/model/administrasjon/kodeverk/ansvar")
            .put("no.novari.fint.model.administrasjon.personal.Personalressurs", "/model/administrasjon/personal/personalressurs")
            .put("no.novari.fint.model.administrasjon.kodeverk.Kontrakt", "/model/administrasjon/kodeverk/kontrakt")
            .put("no.novari.fint.model.administrasjon.kodeverk.Prosjekt", "/model/administrasjon/kodeverk/prosjekt")
            .put("no.novari.fint.model.administrasjon.kodeverk.Formal", "/model/administrasjon/kodeverk/formal")
            .put("no.novari.fint.model.administrasjon.kodeverk.Lopenummer", "/model/administrasjon/kodeverk/lopenummer")
            /* .put(TODO,TODO) */
            .build();
    }

}
