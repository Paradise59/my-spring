package spring.common;

import spring.beans.BeansException;
import spring.beans.PropertyValue;
import spring.beans.PropertyValues;
import spring.beans.factory.ConfigurableListableBeanFactory;
import spring.beans.factory.config.BeanDefinition;
import spring.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}
