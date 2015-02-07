package com.andrewreitz.spock.android;

import com.andrewreitz.spock.android.mock.runtime.AndroidMockFactory;
import groovy.lang.Closure;
import java.util.Map;
import org.spockframework.mock.MockImplementation;
import org.spockframework.mock.MockNature;
import org.spockframework.mock.runtime.MockConfiguration;
import org.spockframework.runtime.GroovyRuntimeUtil;
import spock.lang.Specification;

public abstract class AndroidSpecification extends Specification {
  @Override public Object createMock(String name, Class<?> type, MockNature nature,
      MockImplementation implementation, Map<String, Object> options, Closure closure) {

    Object mock = AndroidMockFactory.INSTANCE.create(
        new MockConfiguration(name, type, nature, implementation, options), this);
    if (closure != null) {
      GroovyRuntimeUtil.invokeClosure(closure, mock);
    }
    return mock;
  }
}
