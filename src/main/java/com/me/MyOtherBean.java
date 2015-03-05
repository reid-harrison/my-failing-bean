package com.me;

import org.springframework.stereotype.Component;

/**
 * This Component will trigger an exception during Spring context creation when it is injected into TestSpringConfig.
 * The bean factory will attempt to instantiate a prototype scoped bean to see if it matches this class' type. The issue
 * also occurs when this bean is in a library dependency whose package is component scanned by TestSpringConfig.
 */
@Component
public class MyOtherBean { }