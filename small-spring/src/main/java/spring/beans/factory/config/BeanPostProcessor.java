package spring.beans.factory.config;

import spring.beans.BeansException;

/**
 * 在实例化之后、初始化前后对Bean实例进行处理
 */
public interface BeanPostProcessor {

    /**
     * 时机：bean实例化后，初始化之前
     * 对 Bean 实例进行修改或替换（如返回代理对象
     *
     * @param bean  已实例化的 Bean 对象及其名称
     * @param beanName 已实例化的 Bean 名称
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 时机：bean实例化后，初始化之后
     * 在 Bean 对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
