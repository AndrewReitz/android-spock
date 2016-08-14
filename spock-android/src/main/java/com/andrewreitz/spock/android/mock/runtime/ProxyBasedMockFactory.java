package com.andrewreitz.spock.android.mock.runtime;

import com.android.dx.stock.ProxyBuilder;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.spockframework.mock.CannotCreateMockException;
import org.spockframework.mock.ISpockMockObject;
import org.spockframework.mock.runtime.DynamicProxyMockInterceptorAdapter;
import org.spockframework.mock.runtime.IProxyBasedMockInterceptor;
import org.spockframework.runtime.InvalidSpecException;
import org.spockframework.util.Nullable;
import org.spockframework.util.ReflectionUtil;

/**
 * Some implementation details of this class are inspired from Spring, EasyMock
 * Class Extensions, JMock, Mockito, and this thread:
 * http://www.nabble.com/Callbacks%2C-classes-and-instances-to4092596.html
 *
 * @author Peter Niederwieser
 * @author Andrew Reitz
 */
public class ProxyBasedMockFactory {
  private static final boolean DEX_MAKER_AVAILABLE =
      ReflectionUtil.isClassAvailable("com.android.dx.stock.ProxyBuilder") &&
          ReflectionUtil.isClassAvailable("com.android.dx.rop.type.Type");

  public static ProxyBasedMockFactory INSTANCE = new ProxyBasedMockFactory();

  private static final UnsafeAllocator UNSAFE_ALLOCATOR = UnsafeAllocator.create();

  public Object create(Class<?> mockType, List<Class<?>> additionalInterfaces,
      @Nullable List<Object> constructorArgs, IProxyBasedMockInterceptor mockInterceptor,
      ClassLoader classLoader) throws CannotCreateMockException {
    Object proxy;

    if (mockType.isInterface()) {
      proxy = createDynamicProxyMock(mockType, additionalInterfaces, constructorArgs,
          mockInterceptor, classLoader);
    } else if (DEX_MAKER_AVAILABLE) {
      proxy = DexMakerMockFactory.createMock(mockType, additionalInterfaces, constructorArgs,
          mockInterceptor, classLoader);
    } else {
      throw new CannotCreateMockException(mockType,
          ". Mocking of non-interface types requires the DexMaker library. Please put dexmaker-1.4 "
              + "and dexmaker-dx-1.4 or higher on the class path.");
    }

    return proxy;
  }

  private Object createDynamicProxyMock(Class<?> mockType, List<Class<?>> additionalInterfaces,
      List<Object> constructorArgs, IProxyBasedMockInterceptor mockInterceptor,
      ClassLoader classLoader) {
    if (constructorArgs != null) {
      throw new InvalidSpecException("Interface based mocks may not have constructor arguments");
    }
    List<Class<?>> interfaces = new ArrayList<Class<?>>();
    interfaces.add(mockType);
    interfaces.addAll(additionalInterfaces);
    interfaces.add(ISpockMockObject.class);
    return Proxy.newProxyInstance(classLoader, interfaces.toArray(new Class<?>[interfaces.size()]),
        new DynamicProxyMockInterceptorAdapter(mockInterceptor));
  }

  // inner class to defer class loading
  private static class DexMakerMockFactory {
    static Object createMock(Class<?> type, List<Class<?>> additionalInterfaces,
        @Nullable List<Object> constructorArgs, final IProxyBasedMockInterceptor interceptor,
        ClassLoader classLoader) {
      List<Class<?>> interfaces = new ArrayList<Class<?>>();
      interfaces.addAll(additionalInterfaces);
      interfaces.add(ISpockMockObject.class);

      try {
        Class<?> mockedClass = ProxyBuilder.forClass(type)
            .implementing(interfaces.toArray(new Class<?>[interfaces.size()]))
            .parentClassLoader(classLoader)
            .buildProxyClass();

        Object mock = UNSAFE_ALLOCATOR.newInstance(mockedClass);
        ProxyBuilder.setInvocationHandler(mock, new InvocationHandler() {
          @Override public Object invoke(Object proxy, Method method, Object[] args)
              throws Throwable {
            return interceptor.intercept(proxy, method, args, null);
          }
        });

        return mock;
      } catch (RuntimeException e) {
        throw e;
      } catch (Exception e) {
        throw new CannotCreateMockException(type, e);
      }
    }
  }
}
