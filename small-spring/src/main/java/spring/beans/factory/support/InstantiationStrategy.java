package spring.beans.factory.support;

import spring.beans.BeansException;
import spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Bean 实例化策略
 */
public interface InstantiationStrategy {
    /**
     * Constructor :
     * @param beanDefinition
     * @param beanName
     * @param ctor 构造器，为了拿到符合入参信息相对应的构造函数。
     * @param args 具体的入参信息
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
