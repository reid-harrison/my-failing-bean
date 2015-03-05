package com.me;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;

@Configuration
@ComponentScan(basePackages = "com.me")
public class TestSpringConfig {

    //My failing bean will be instantiated (test will fail) because of this injection
    @Inject
    MyOtherBean myOtherBean;

    /**
     * Causes exception because of early instantiation. No qualifying bean will be found for the String parameter.
     */
    @Bean(name = {"my-failing-bean"})
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Profile("test")
    @Lazy
    public MyFailingBean myFailingBean(final String stringParam) {
        return new MyFailingBean();
    }

}