package spring.beans.factory;

import spring.beans.BeansException;

public interface BeanFactory {

    Object getBean(String name) throws BeansException;

}