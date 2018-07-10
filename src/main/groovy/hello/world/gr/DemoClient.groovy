package hello.world.gr

import io.micronaut.http.client.Client

@Client(id = "rest-hello-world-gr-svc")
interface DemoClient extends Demo{

}