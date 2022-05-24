package com.bobocode.lesson7;

import com.bobocode.homework6.LogInvocation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Parameter;

public class DemoApp {

    // for run it, I had to add [--add-opens java.base/java.lang=ALL-UNNAMED] as VM options
    public static void main(String[] args) {
        TestMethodService helloService = checkNotNullProxy(TestMethodService.class);
        helloService.gloryToUkraine(11); // logs nothing to the console
        helloService.gloryToUkraine(null); // logs method invocation to the console
    }

    /**
     * Creates a proxy of the provided class that logs its method invocations. If a method that
     * is marked with {@link CheckNotNull} annotation is invoked, it prints to the console the following statement:
     *
     * @param targetClass a class that is extended with proxy
     * @param <T>         target class type parameter
     * @return an instance of a proxy class
     */
    public static <T> T checkNotNullProxy(Class<T> targetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);

        MethodInterceptor methodInterceptor = (o, method, objects, methodProxy) ->{
            if (method.isAnnotationPresent(CheckNotNull.class)){
                for(Object argument : objects){
                    if(argument == null){
                        throw new IllegalArgumentException(" Null in arguments");
                    }
                }
            }
            for(int i = 0; i < method.getParameters().length; i++){
                if (method.getParameters()[i].isAnnotationPresent(CheckNotNull.class) && objects[i] == null){
                    throw new IllegalArgumentException(String.format(" Null in argument %s", method.getParameters()[i].getName()));
                }
            }
            return methodProxy.invokeSuper(o, objects);
        };
        enhancer.setCallback(methodInterceptor);

        return (T)enhancer.create();
    }
}
