package com.me;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertTrue;

/**
 * Single test to invoke the Spring context creation. Test will fail because of issue with early instantiation of
 * prototype scoped bean declared in TestSpringConfig.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringConfig.class, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class MyTest {

    @Test
    public void test() {
        assertTrue(true);
    }

}