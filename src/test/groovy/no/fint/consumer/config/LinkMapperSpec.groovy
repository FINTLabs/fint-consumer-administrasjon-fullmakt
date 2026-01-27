package no.fint.consumer.config

import spock.lang.Specification

class LinkMapperSpec extends Specification{
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
