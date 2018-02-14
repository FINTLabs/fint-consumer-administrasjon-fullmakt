package no.fint.consumer.config;

import com.google.common.collect.ImmutableMap;
import no.fint.consumer.utils.RestEndpoints;
import no.fint.model.administrasjon.fullmakt.Fullmakt;
import no.fint.model.administrasjon.fullmakt.Rolle;
import no.fint.model.administrasjon.kodeverk.Ansvar;
import no.fint.model.administrasjon.kodeverk.Art;
import no.fint.model.administrasjon.kodeverk.Funksjon;
import no.fint.model.administrasjon.kodeverk.Prosjekt;
import no.fint.model.administrasjon.personal.Personalressurs;

import java.util.Map;

public class LinkMapper {

	public static Map<String, String> linkMapper(String contextPath) {
		return ImmutableMap.<String,String>builder()
			.put(Fullmakt.class.getName(), contextPath + RestEndpoints.FULLMAKT)
			.put(Rolle.class.getName(), contextPath + RestEndpoints.ROLLE)
            .put(Personalressurs.class.getName(), "/administrasjon/personal/personalressurs")
            .put(Ansvar.class.getName(), "/administrasjon/kodeverk/ansvar")
            .put(Prosjekt.class.getName(), "/administrasjon/kodeverk/prosjekt")
            .put(Art.class.getName(), "/administrasjon/kodeverk/art")
            .put(Funksjon.class.getName(), "/administrasjon/kodeverk/funksjon")
			.build();
	}

}
