A very simple project that produces the following exception with Spring _4.1.4.RELEASE_. The configuration found in this
project worked in version _4.0.4.RELEASE_.

For further description of the issue please see [this StackOverflow question](http://stackoverflow.com/questions/28859716/prototype-scoped-spring-bean-incorrectly-created-at-context-creation-time-after).

    #:>mvn clean install
    [INFO] Scanning for projects...
    [INFO]
    [INFO] ------------------------------------------------------------------------
    [INFO] Building my-failing-bean 00.00.01
    [INFO] ------------------------------------------------------------------------
    [INFO]
    [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ my-failing-bean ---
    [WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
    [INFO] skip non existing resourceDirectory C:\Users\gtb012\git-work\broken-spring-tests\src\main\resources
    [INFO]
    [INFO] --- maven-compiler-plugin:2.5.1:compile (default-compile) @ my-failing-bean ---
    [INFO] Nothing to compile - all classes are up to date
    [INFO]
    [INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ my-failing-bean ---
    [WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
    [INFO] skip non existing resourceDirectory C:\Users\gtb012\git-work\broken-spring-tests\src\test\resources
    [INFO]
    [INFO] --- maven-compiler-plugin:2.5.1:testCompile (default-testCompile) @ my-failing-bean ---
    [INFO] Nothing to compile - all classes are up to date
    [INFO]
    [INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ my-failing-bean ---
    [INFO] Surefire report directory: C:\Users\gtb012\git-work\broken-spring-tests\target\surefire-reports

    -------------------------------------------------------
     T E S T S
    -------------------------------------------------------
    Running com.me.MyTest
    Mar 04, 2015 9:56:12 PM org.springframework.test.context.support.DefaultTestContextBootstrapper getDefaultTestExecutionListenerClassNames
    INFO: Loaded default TestExecutionListener class names from location [META-INF/spring.factories]: [org.springframework.test.context.web.ServletTestExecutionListener, org.springframework.test.context.support.DependencyInjectionTestExecutionListener, org.springframework.test.context.support.DirtiesContextTestExecutionListener, org.springframework.test.context.transaction.TransactionalTestExecutionListener, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener]
    Mar 04, 2015 9:56:12 PM org.springframework.test.context.support.DefaultTestContextBootstrapper instantiateListeners
    INFO: Could not instantiate TestExecutionListener [org.springframework.test.context.transaction.TransactionalTestExecutionListener]. Specify custom listener classes or make the default listener classes (and their required dependencies) available. Offending class: [org/springframework/transaction/interceptor/TransactionAttributeSource]
    Mar 04, 2015 9:56:12 PM org.springframework.test.context.support.DefaultTestContextBootstrapper instantiateListeners
    INFO: Could not instantiate TestExecutionListener [org.springframework.test.context.web.ServletTestExecutionListener]. Specify custom listener classes or make the default listener classes (and their required dependencies) available. Offending class: [javax/servlet/ServletContext]
    Mar 04, 2015 9:56:12 PM org.springframework.test.context.support.DefaultTestContextBootstrapper instantiateListeners
    INFO: Could not instantiate TestExecutionListener [org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener]. Specify custom listener classes or make the default listener classes (and their required dependencies) available. Offending class: [org/springframework/transaction/interceptor/TransactionAttribute]
    Mar 04, 2015 9:56:12 PM org.springframework.test.context.support.DefaultTestContextBootstrapper getTestExecutionListeners
    INFO: Using TestExecutionListeners: [org.springframework.test.context.support.DependencyInjectionTestExecutionListener@28bf3bbb, org.springframework.test.context.support.DirtiesContextTestExecutionListener@22ea86e3]
    Mar 04, 2015 9:56:12 PM org.springframework.context.support.GenericApplicationContext prepareRefresh
    INFO: Refreshing org.springframework.context.support.GenericApplicationContext@707a7686: startup date [Wed Mar 04 21:56:12 EST 2015]; root of context hierarchy
    Mar 04, 2015 9:56:12 PM org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor <init>
    INFO: JSR-330 'javax.inject.Inject' annotation found and supported for autowiring
    Mar 04, 2015 9:56:12 PM org.springframework.context.support.GenericApplicationContext refresh
    WARNING: Exception encountered during context initialization - cancelling refresh attempt
    org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'testSpringConfig': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: com.me.MyOtherBean com.me.TestSpringConfig.myOtherBean; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'my-failing-bean' defined in com.me.TestSpringConfig: Unsatisfied dependency expressed through constructor argument with index 0 of type [java.lang.String]: : No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:334)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1202)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:537)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:476)
        at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:303)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:299)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:194)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:762)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:757)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:480)
        at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:125)
        at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:60)
        at org.springframework.test.context.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:68)
        at org.springframework.test.context.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:86)
        at org.springframework.test.context.DefaultTestContext.getApplicationContext(DefaultTestContext.java:72)
        at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:117)
        at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:83)
        at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:212)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.createTest(SpringJUnit4ClassRunner.java:200)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner$1.runReflectiveCall(SpringJUnit4ClassRunner.java:252)
        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.methodBlock(SpringJUnit4ClassRunner.java:254)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:217)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:83)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
        at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
        at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:68)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:163)
        at org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:252)
        at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:141)
        at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:112)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:606)
        at org.apache.maven.surefire.util.ReflectionUtils.invokeMethodWithArray(ReflectionUtils.java:189)
        at org.apache.maven.surefire.booter.ProviderFactory$ProviderProxy.invoke(ProviderFactory.java:165)
        at org.apache.maven.surefire.booter.ProviderFactory.invokeProvider(ProviderFactory.java:85)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:115)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:75)
    Caused by: org.springframework.beans.factory.BeanCreationException: Could not autowire field: com.me.MyOtherBean com.me.TestSpringConfig.myOtherBean; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'my-failing-bean' defined in com.me.TestSpringConfig: Unsatisfied dependency expressed through constructor argument with index 0 of type [java.lang.String]: : No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:561)
        at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:88)
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:331)
        ... 45 more
    Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'my-failing-bean' defined in com.me.TestSpringConfig: Unsatisfied dependency expressed through constructor argument with index 0 of type [java.lang.String]: : No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:749)
        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:464)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1111)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1006)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.getNonSingletonFactoryBeanForTypeCheck(AbstractAutowireCapableBeanFactory.java:896)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.getTypeForFactoryBean(AbstractAutowireCapableBeanFactory.java:791)
        at org.springframework.beans.factory.support.AbstractBeanFactory.isTypeMatch(AbstractBeanFactory.java:542)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doGetBeanNamesForType(DefaultListableBeanFactory.java:436)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanNamesForType(DefaultListableBeanFactory.java:412)
        at org.springframework.beans.factory.BeanFactoryUtils.beanNamesForTypeIncludingAncestors(BeanFactoryUtils.java:187)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.findAutowireCandidates(DefaultListableBeanFactory.java:1112)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1051)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:949)
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:533)
        ... 47 more
    Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.raiseNoSuchBeanDefinitionException(DefaultListableBeanFactory.java:1308)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1054)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:949)
        at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:813)
        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:741)
        ... 60 more

    Mar 04, 2015 9:56:12 PM org.springframework.test.context.TestContextManager prepareTestInstance
    SEVERE: Caught exception while allowing TestExecutionListener [org.springframework.test.context.support.DependencyInjectionTestExecutionListener@28bf3bbb] to prepare test instance [com.me.MyTest@559b6982]
    java.lang.IllegalStateException: Failed to load ApplicationContext
        at org.springframework.test.context.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:94)
        at org.springframework.test.context.DefaultTestContext.getApplicationContext(DefaultTestContext.java:72)
        at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:117)
        at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:83)
        at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:212)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.createTest(SpringJUnit4ClassRunner.java:200)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner$1.runReflectiveCall(SpringJUnit4ClassRunner.java:252)
        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.methodBlock(SpringJUnit4ClassRunner.java:254)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:217)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:83)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
        at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
        at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:68)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:163)
        at org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:252)
        at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:141)
        at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:112)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:606)
        at org.apache.maven.surefire.util.ReflectionUtils.invokeMethodWithArray(ReflectionUtils.java:189)
        at org.apache.maven.surefire.booter.ProviderFactory$ProviderProxy.invoke(ProviderFactory.java:165)
        at org.apache.maven.surefire.booter.ProviderFactory.invokeProvider(ProviderFactory.java:85)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:115)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:75)
    Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'testSpringConfig': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: com.me.MyOtherBean com.me.TestSpringConfig.myOtherBean; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'my-failing-bean' defined in com.me.TestSpringConfig: Unsatisfied dependency expressed through constructor argument with index 0 of type [java.lang.String]: : No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:334)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1202)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:537)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:476)
        at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:303)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:299)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:194)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:762)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:757)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:480)
        at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:125)
        at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:60)
        at org.springframework.test.context.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:68)
        at org.springframework.test.context.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:86)
        ... 31 more
    Caused by: org.springframework.beans.factory.BeanCreationException: Could not autowire field: com.me.MyOtherBean com.me.TestSpringConfig.myOtherBean; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'my-failing-bean' defined in com.me.TestSpringConfig: Unsatisfied dependency expressed through constructor argument with index 0 of type [java.lang.String]: : No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:561)
        at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:88)
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:331)
        ... 45 more
    Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'my-failing-bean' defined in com.me.TestSpringConfig: Unsatisfied dependency expressed through constructor argument with index 0 of type [java.lang.String]: : No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:749)
        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:464)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1111)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1006)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.getNonSingletonFactoryBeanForTypeCheck(AbstractAutowireCapableBeanFactory.java:896)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.getTypeForFactoryBean(AbstractAutowireCapableBeanFactory.java:791)
        at org.springframework.beans.factory.support.AbstractBeanFactory.isTypeMatch(AbstractBeanFactory.java:542)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doGetBeanNamesForType(DefaultListableBeanFactory.java:436)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanNamesForType(DefaultListableBeanFactory.java:412)
        at org.springframework.beans.factory.BeanFactoryUtils.beanNamesForTypeIncludingAncestors(BeanFactoryUtils.java:187)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.findAutowireCandidates(DefaultListableBeanFactory.java:1112)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1051)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:949)
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:533)
        ... 47 more
    Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.raiseNoSuchBeanDefinitionException(DefaultListableBeanFactory.java:1308)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1054)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:949)
        at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:813)
        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:741)
        ... 60 more

    Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.73 sec <<< FAILURE!
    test(com.me.MyTest)  Time elapsed: 0.006 sec  <<< ERROR!
    java.lang.IllegalStateException: Failed to load ApplicationContext
        at org.springframework.test.context.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:94)
        at org.springframework.test.context.DefaultTestContext.getApplicationContext(DefaultTestContext.java:72)
        at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:117)
        at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:83)
        at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:212)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.createTest(SpringJUnit4ClassRunner.java:200)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner$1.runReflectiveCall(SpringJUnit4ClassRunner.java:252)
        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.methodBlock(SpringJUnit4ClassRunner.java:254)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:217)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:83)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
        at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
        at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:68)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:163)
        at org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:252)
        at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:141)
        at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:112)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:606)
        at org.apache.maven.surefire.util.ReflectionUtils.invokeMethodWithArray(ReflectionUtils.java:189)
        at org.apache.maven.surefire.booter.ProviderFactory$ProviderProxy.invoke(ProviderFactory.java:165)
        at org.apache.maven.surefire.booter.ProviderFactory.invokeProvider(ProviderFactory.java:85)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:115)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:75)
    Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'testSpringConfig': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: com.me.MyOtherBean com.me.TestSpringConfig.myOtherBean; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'my-failing-bean' defined in com.me.TestSpringConfig: Unsatisfied dependency expressed through constructor argument with index 0 of type [java.lang.String]: : No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:334)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1202)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:537)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:476)
        at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:303)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:299)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:194)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:762)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:757)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:480)
        at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:125)
        at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:60)
        at org.springframework.test.context.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:68)
        at org.springframework.test.context.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:86)
        ... 31 more
    Caused by: org.springframework.beans.factory.BeanCreationException: Could not autowire field: com.me.MyOtherBean com.me.TestSpringConfig.myOtherBean; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'my-failing-bean' defined in com.me.TestSpringConfig: Unsatisfied dependency expressed through constructor argument with index 0 of type [java.lang.String]: : No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:561)
        at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:88)
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:331)
        ... 45 more
    Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'my-failing-bean' defined in com.me.TestSpringConfig: Unsatisfied dependency expressed through constructor argument with index 0 of type [java.lang.String]: : No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:749)
        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:464)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1111)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1006)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.getNonSingletonFactoryBeanForTypeCheck(AbstractAutowireCapableBeanFactory.java:896)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.getTypeForFactoryBean(AbstractAutowireCapableBeanFactory.java:791)
        at org.springframework.beans.factory.support.AbstractBeanFactory.isTypeMatch(AbstractBeanFactory.java:542)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doGetBeanNamesForType(DefaultListableBeanFactory.java:436)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanNamesForType(DefaultListableBeanFactory.java:412)
        at org.springframework.beans.factory.BeanFactoryUtils.beanNamesForTypeIncludingAncestors(BeanFactoryUtils.java:187)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.findAutowireCandidates(DefaultListableBeanFactory.java:1112)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1051)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:949)
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:533)
        ... 47 more
    Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [java.lang.String] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.raiseNoSuchBeanDefinitionException(DefaultListableBeanFactory.java:1308)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1054)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:949)
        at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:813)
        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:741)
        ... 60 more


    Results :

    Tests in error:
      test(com.me.MyTest): Failed to load ApplicationContext

    Tests run: 1, Failures: 0, Errors: 1, Skipped: 0

    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD FAILURE
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 2.870s
    [INFO] Finished at: Wed Mar 04 21:56:12 EST 2015
    [INFO] Final Memory: 9M/367M
    [INFO] ------------------------------------------------------------------------
    [ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test (default-test) on project my-failing-bean: There are test failures.
    [ERROR]
    [ERROR] Please refer to C:\Users\gtb012\git-work\broken-spring-tests\target\surefire-reports for the individual test results.
    [ERROR] -> [Help 1]
    [ERROR]
    [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
    [ERROR] Re-run Maven using the -X switch to enable full debug logging.
    [ERROR]
    [ERROR] For more information about the errors and possible solutions, please read the following articles:
    [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException

    Process finished with exit code 1