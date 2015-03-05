package com.me;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;

/**
 * The factory method for this class in TestSpringConfig gets called at context creation time despite being prototype scoped.
 * The exception occurs if this class implements FactoryBean, BeanFactoryAware, OR BeanClassLoaderAware (possible more).
 */
public class MyFailingBean implements FactoryBean<Object>, BeanFactoryAware, BeanClassLoaderAware {

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) { }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException { }

    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}