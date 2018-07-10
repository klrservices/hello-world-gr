package hello.world.gr

import javax.inject.Singleton

@Singleton
class SampleBean {
    String someString
    int someInt

    SampleBean(String someString, int someInt) {
        this.someString = someString ? someString.toUpperCase() : "NULL"
        this.someInt = someInt
    }
}