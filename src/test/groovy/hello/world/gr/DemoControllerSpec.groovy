package hello.world.gr;

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import groovy.json.*

class DemoControllerSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared @AutoCleanup RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())
    @Shared JsonSlurper jsonSlurper = new JsonSlurper()

    void 'test index'() {
        given:
        String response = client.toBlocking().retrieve('/demo')

        expect:
        response == 'Hello world!'
    }

    void 'test toUpper'() {
        given:
        String response = client.toBlocking().retrieve('/demo/to-upper/test')

        expect:
        response == 'TEST'
    }

    void 'test currentDate'() {
        given:
        String response = client.toBlocking().retrieve('/demo/current-date')
        Date today = new Date()

        expect:
        response.startsWith(today.toString().substring(0,9))
    }

    void 'test not null sample'() {
        given: 'some not null parameters'
        def sampleBeanJson = client.toBlocking().retrieve('/demo/sample?someString=abc&someInt=2')
        def sampleBean = jsonSlurper.parseText(sampleBeanJson)

        expect: 'int parameter is preserved'
        sampleBean.someInt == 2
        and: 'string parameter is uppercased'
        sampleBean.someString == 'ABC'
    }

    void 'test null sample'() {
        given: 'string parameter empty'
        def sampleBeanJson = client.toBlocking().retrieve('/demo/sample?someString=&someInt=2')
        def sampleBean = jsonSlurper.parseText(sampleBeanJson)

        expect: 'int parameter preserved'
        sampleBean.someInt == 2
        and: 'string property set to NULL'
        sampleBean.someString == 'NULL'
    }
}
