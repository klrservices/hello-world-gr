package hello.world.gr

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus


@Controller("/demo")
class DemoController implements Demo{


    @Override @Get("/")
    String index() {
        return "Hello world!"
    }

    @Override @Get("/to-upper/{param}")
    String toUpper(String param) {
        return param.toUpperCase()
    }

    @Override @Get("/current-date")
    String currentDate() {
        return new Date().toString()
    }

    @Override @Get("/sample{?someString,someInt}")
    SampleBean sample(String someString, int someInt) {
        return new SampleBean(someString,someInt)
    }

    @Override @Get("/concat/{param1}/{param2}")
    String concat(String param1, String param2) {
        return "${param1}${param2}"
    }
}