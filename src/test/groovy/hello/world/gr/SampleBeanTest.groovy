package hello.world.gr

import spock.lang.Specification

class SampleBeanTest extends Specification {
    void "test not null parameter being uppercased"() {
        given: "not null parameters"
        def bean = new SampleBean("test", 0)

        expect: "bean someString field is TEST"
        bean.someString == "TEST"
    }

    void "test null parameter being NULL"() {
        given: "null parameter"
        def bean = new SampleBean(null, 0)

        expect:
        bean.someString == "NULL"
    }
}
