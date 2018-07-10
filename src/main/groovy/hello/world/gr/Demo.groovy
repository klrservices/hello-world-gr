package hello.world.gr;

import io.micronaut.http.annotation.Get;

import java.util.Date;

public interface Demo {
    @Get("/")
    String index()

    @Get("/to-upper/{param}")
    String toUpper(String param)

    @Get("/current-date")
    String currentDate()

    @Get("/sample{?someString,someInt}")
    SampleBean sample(String someString, int someInt)
}
