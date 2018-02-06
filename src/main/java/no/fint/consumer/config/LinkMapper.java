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
			/* .put(TODO,TODO) */
			.build();
	}

}
