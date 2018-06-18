package hello.world.gr

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus


@Controller("/demo")
class DemoController {

    @Get("/")
    String index() {
        return "Hello world"
    }

    @Get("/to-upper/{param}")
    String toUpper(String param) {
        return param.toUpperCase()
    }

    @Get("/current-date")
    String currentDate() {
        return new Date().toString()
    }
}