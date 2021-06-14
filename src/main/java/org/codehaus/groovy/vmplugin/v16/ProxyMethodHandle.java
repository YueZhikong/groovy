package org.codehaus.groovy.vmplugin.v16;

import org.codehaus.groovy.GroovyBugError;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class ProxyMethodHandle {
    private static final Method INVOKE_DEFAULT_METHOD;
    static {
        try {
            INVOKE_DEFAULT_METHOD = InvocationHandler.class.getDeclaredMethod("invokeDefault", Object.class, Method.class, Object[].class);
        } catch (NoSuchMethodException e) {
            throw new GroovyBugError(e);
        }
    }

    private final Proxy proxy;
    private final Method method;

    ProxyMethodHandle(Proxy proxy, Method method) {
        this.proxy = proxy;
        this.method = method;
    }

    Object invokeWithArguments(Object... arguments) throws Throwable {
        return INVOKE_DEFAULT_METHOD.invoke(null, proxy, method, arguments);
    }
}
