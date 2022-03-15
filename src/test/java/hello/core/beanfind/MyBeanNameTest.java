package hello.core.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.*;

public class MyBeanNameTest {

    @Test
    public void sameClassDifferentBeanName() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        TestClass bean1 = ac.getBean("bean1", TestClass.class);
        TestClass bean2 = ac.getBean("bean2", TestClass.class);
        bean1.increase();
        bean2.increase();
        assertThat(bean1.getN()).isEqualTo(2);

        assertThat(bean1).isInstanceOf(TestClass.class);
        assertThat(bean2).isInstanceOf(TestClass.class);
    }

    static class TestClass {
        private static int n = 0;

        public void increase() {
            n++;
        }

        public int getN() {
            return n;
        }
    }

    @Configuration
    static class Config {
        @Bean("bean1")
        public TestClass testClass1() {
            return new TestClass();
        }

        @Bean("bean2")
        public TestClass testClass2() {
            return new TestClass();
        }
    }
}
