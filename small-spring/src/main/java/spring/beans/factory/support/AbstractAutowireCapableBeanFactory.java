package spring.beans.factory.support;

import spring.beans.BeansException;
import spring.beans.factory.config.BeanDefinition;

/**
 * 实现了 Bean 的实例化操作 ：实现AbstractBeanFactory 中的 createBean
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            // 通过反射实例化对象
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 将实例化的对象放入单例对象集合中
        addSingleton(beanName, bean);
        return bean;
    }

}