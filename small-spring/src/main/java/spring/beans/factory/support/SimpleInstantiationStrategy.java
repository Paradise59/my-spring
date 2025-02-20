package spring.beans.factory.support;

import spring.beans.BeansException;
import spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        // 1. 通过 beanDefinition 获取 Class 信息
        Class clazz = beanDefinition.getBeanClass();
        try {
            // 2. 判断 ctor 是否为空，如果为空则是无构造函数实例化，否则就是需要有构造函数的实例化
            if (null != ctor) {
                // 把入参信息传递给 newInstance 进行实例化
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
