package no.fint.consumer.config;

import com.google.common.collect.ImmutableMap;
import no.fint.consumer.utils.RestEndpoints;
import no.fint.model.administrasjon.fullmakt.Fullmakt;
import no.fint.model.administrasjon.fullmakt.Rolle;
import no.fint.model.administrasjon.kodeverk.*;
import no.fint.model.administrasjon.organisasjon.Organisasjonselement;
import no.fint.model.administrasjon.personal.Personalressurs;

import java.util.Map;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String, String>builder()
                .put(Fullmakt.class.getName(), contextPath + RestEndpoints.FULLMAKT)
                .put(Rolle.class.getName(), contextPath + RestEndpoints.ROLLE)

                .put(Aktivitet.class.getName(), "/administrasjon/kodeverk/aktivitet")
                .put(Anlegg.class.getName(), "/administrasjon/kodeverk/anlegg")
                .put(Ansvar.class.getName(), "/administrasjon/kodeverk/ansvar")
                .put(Art.class.getName(), "/administrasjon/kodeverk/art")
                .put(Diverse.class.getName(), "/administrasjon/kodeverk/diverse")
                .put(Funksjon.class.getName(), "/administrasjon/kodeverk/funksjon")
                .put(Kontrakt.class.getName(), "/administrasjon/kodeverk/kontrakt")
                .put(Lopenummer.class.getName(), "/administrasjon/kodeverk/lopenummer")
                .put(Objekt.class.getName(), "/administrasjon/kodeverk/objekt")
                .put(Prosjekt.class.getName(), "/administrasjon/kodeverk/prosjekt")
                .put(Ramme.class.getName(), "/administrasjon/kodeverk/ramme")

                .put(Personalressurs.class.getName(), "/administrasjon/personal/personalressurs")
				.put(Organisasjonselement.class.getName(), "/administrasjon/organisasjon/organisasjonselement")
                .build();
    }

}
