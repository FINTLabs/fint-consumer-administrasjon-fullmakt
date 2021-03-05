package no.fint.consumer.config

import no.fint.model.administrasjon.fullmakt.Fullmakt
import no.fint.model.administrasjon.fullmakt.Rolle
import no.fint.model.administrasjon.kodeverk.Ansvar
import no.fint.model.administrasjon.kodeverk.Art
import no.fint.model.administrasjon.kodeverk.Funksjon
import no.fint.model.administrasjon.kodeverk.Prosjekt
import no.fint.model.administrasjon.organisasjon.Organisasjonselement
import no.fint.model.administrasjon.personal.Personalressurs
import spock.lang.Specification

class LinkMapperSpec extends Specification {
    def 'Assert that links to Kontodimensjon are present'() {
        given:
        def mapper = LinkMapper.linkMapper('/')

        expect:
        mapper.containsKey(Fullmakt.name)
        mapper.containsKey(Rolle.name)

        mapper.containsKey(Art.name)
        mapper.containsKey(Ansvar.name)
        mapper.containsKey(Funksjon.name)
        mapper.containsKey(Prosjekt.name)

        mapper.containsKey(Personalressurs.name)
        mapper.containsKey(Organisasjonselement.name)
    }
}
