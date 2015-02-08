package com.andrewreitz.spock.android.mock.runtime;

import java.util.Arrays;
import java.util.List;
import org.spockframework.mock.IMockConfiguration;
import org.spockframework.mock.IMockFactory;
import org.spockframework.mock.runtime.GroovyMockFactory;
import org.spockframework.util.InternalSpockError;
import org.spockframework.util.UnreachableCodeError;
import spock.lang.Specification;

public class AndroidMockFactory implements IMockFactory {
  public static AndroidMockFactory INSTANCE =
      new AndroidMockFactory(Arrays.asList(JavaMockFactory.INSTANCE, GroovyMockFactory.INSTANCE));

  private final List<IMockFactory> mockFactories;

  public AndroidMockFactory(List<IMockFactory> mockFactories) {
    this.mockFactories = mockFactories;
  }

  public boolean canCreate(IMockConfiguration configuration) {
    throw new UnreachableCodeError("canCreate");
  }

  public Object create(IMockConfiguration configuration, Specification specification) {
    for (IMockFactory factory : mockFactories) {
      if (factory.canCreate(configuration)) {
        return factory.create(configuration, specification);
      }
    }

    throw new InternalSpockError("No matching mock factory found");
  }
}
