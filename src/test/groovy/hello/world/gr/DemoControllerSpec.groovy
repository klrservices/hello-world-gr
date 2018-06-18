package hello.world.gr;

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class DemoControllerSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared @AutoCleanup RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())


    void "test index"() {
        given:
        String response = client.toBlocking().retrieve("/demo")

        expect:
        response == "Hello world"
    }

    void "test toUpper"() {
        given:
        String response = client.toBlocking().retrieve("/demo/to-upper/test")

        expect:
        response == "TEST"
    }

    void "test currentDate"() {
        given:
        String response = client.toBlocking().retrieve("/demo/current-date")
        Date today = new Date()

        expect:
        response.startsWith(today.toString().substring(0,9))
    }
}
