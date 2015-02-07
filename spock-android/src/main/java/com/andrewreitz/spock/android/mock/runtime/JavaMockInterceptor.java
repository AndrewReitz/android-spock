package com.andrewreitz.spock.android.mock.runtime;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.spockframework.mock.IMockConfiguration;
import org.spockframework.mock.IMockController;
import org.spockframework.mock.IMockInvocation;
import org.spockframework.mock.IMockMethod;
import org.spockframework.mock.IMockObject;
import org.spockframework.mock.IResponseGenerator;
import org.spockframework.mock.ISpockMockObject;
import org.spockframework.mock.runtime.IProxyBasedMockInterceptor;
import org.spockframework.mock.runtime.MockInvocation;
import org.spockframework.mock.runtime.MockObject;
import org.spockframework.mock.runtime.StaticMockMethod;
import org.spockframework.runtime.GroovyRuntimeUtil;
import spock.lang.Specification;

public class JavaMockInterceptor implements IProxyBasedMockInterceptor {
  private final IMockConfiguration mockConfiguration;
  private final Specification specification;
  private final MetaClass mockMetaClass;

  public JavaMockInterceptor(IMockConfiguration mockConfiguration, Specification specification, MetaClass mockMetaClass) {
    this.mockConfiguration = mockConfiguration;
    this.specification = specification;
    this.mockMetaClass = mockMetaClass;
  }

  public Object intercept(Object target, Method method, Object[] arguments, IResponseGenerator realMethodInvoker) {
    IMockObject mockObject = new MockObject(mockConfiguration.getName(), mockConfiguration.getType(),
        target, mockConfiguration.isVerified(), false, mockConfiguration.getDefaultResponse(), specification);

    if (method.getDeclaringClass() == ISpockMockObject.class) {
      return mockObject;
    }

    // sometimes we see an argument wrapped in PojoWrapper
    // example is when GroovyObject.invokeMethod is invoked directly
    Object[] normalizedArgs = GroovyRuntimeUtil.asUnwrappedArgumentArray(arguments);

    if (target instanceof GroovyObject) {
      if (isMethod(method, "getMetaClass")) {
        return mockMetaClass;
      }
      if (isMethod(method, "setProperty", String.class, Object.class)) {
        Throwable throwable = new Throwable();
        StackTraceElement mockCaller = throwable.getStackTrace()[3];
        if (mockCaller.getClassName().equals("org.codehaus.groovy.runtime.ScriptBytecodeAdapter")) {
          // for some reason, runtime dispatches direct property access on mock classes via ScriptBytecodeAdapter
          // delegate to the corresponding setter method
          String methodName = GroovyRuntimeUtil.propertyToMethodName("set", (String) normalizedArgs[0]);
          return GroovyRuntimeUtil.invokeMethod(target, methodName, GroovyRuntimeUtil.asArgumentArray(normalizedArgs[1]));
        }
      }
    }

    IMockMethod mockMethod = new StaticMockMethod(method);
    IMockInvocation
        invocation = new MockInvocation(mockObject, mockMethod, Arrays.asList(normalizedArgs), realMethodInvoker);
    IMockController mockController = specification.getSpecificationContext().getMockController();

    return mockController.handle(invocation);
  }

  private boolean isMethod(Method method, String name, Class<?>... parameterTypes) {
    return method.getName().equals(name) && Arrays.equals(method.getParameterTypes(), parameterTypes);
  }
}
