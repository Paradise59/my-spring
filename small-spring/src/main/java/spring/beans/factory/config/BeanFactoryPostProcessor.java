package spring.beans.factory.config;

import spring.beans.BeansException;
import spring.beans.factory.ConfigurableListableBeanFactory;

/**
 * 对Bean 对象扩展 : 允许在 Bean 对象注册后但未实例化之前，对 Bean 的定义信息BeanDefinition执行修改操作。
 * BeanFactoryPostProcessor是在 Bean实例化之前 修改Bean的定义信息。
 */
public interface BeanFactoryPostProcessor {
    /**
     *  在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *  遍历并修改容器中的 BeanDefinition 信息
     * @param beanFactory  ConfigurableListableBeanFactory（可访问和修改所有 BeanDefinition）
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
