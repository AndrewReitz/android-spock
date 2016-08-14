package com.andrewreitz.spock.android.mock.runtime;

import groovy.lang.MetaClass;
import java.lang.reflect.Modifier;
import java.util.Collections;
import org.spockframework.mock.CannotCreateMockException;
import org.spockframework.mock.IMockConfiguration;
import org.spockframework.mock.IMockFactory;
import org.spockframework.mock.MockImplementation;
import org.spockframework.mock.runtime.IProxyBasedMockInterceptor;
import org.spockframework.mock.runtime.JavaMockInterceptor;
import org.spockframework.runtime.GroovyRuntimeUtil;
import spock.lang.Specification;

public class JavaMockFactory implements IMockFactory {
  public static JavaMockFactory INSTANCE = new JavaMockFactory();

  public boolean canCreate(IMockConfiguration configuration) {
    return configuration.getImplementation() == MockImplementation.JAVA;
  }

  public Object create(IMockConfiguration configuration, Specification specification) {
    if (Modifier.isFinal(configuration.getType().getModifiers())) {
      throw new CannotCreateMockException(configuration.getType(),
          " because Java mocks cannot mock final classes. If the code under test is written in Groovy, use a Groovy mock.");
    }
    if (configuration.isGlobal()) {
      throw new CannotCreateMockException(configuration.getType(),
          " because Java mocks cannot mock globally. If the code under test is written in Groovy, use a Groovy mock.");
    }

    MetaClass mockMetaClass = GroovyRuntimeUtil.getMetaClass(configuration.getType());
    IProxyBasedMockInterceptor interceptor = new JavaMockInterceptor(configuration, specification, mockMetaClass);

    return ProxyBasedMockFactory.INSTANCE.create(configuration.getType(), Collections.<Class<?>>emptyList(),
        configuration.getConstructorArgs(), interceptor, specification.getClass().getClassLoader());
  }
}
