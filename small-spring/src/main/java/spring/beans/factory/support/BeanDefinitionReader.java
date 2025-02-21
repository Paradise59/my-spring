package spring.beans.factory.support;

import spring.beans.BeansException;
import spring.core.io.Resource;
import spring.core.io.ResourceLoader;

/**
 * 包含三个加载Bean定义的方法
 * 读取资源信息并解析，将 BeanDefinition 注册到 Spring 容器中去
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;
}
