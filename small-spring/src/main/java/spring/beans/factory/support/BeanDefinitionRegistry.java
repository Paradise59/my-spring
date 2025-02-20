package spring.beans.factory.support;

import spring.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition 注册表
 */
public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册 BeanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
