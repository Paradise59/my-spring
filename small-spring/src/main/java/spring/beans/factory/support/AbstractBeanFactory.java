package spring.beans.factory.support;


import spring.beans.factory.BeanFactory;
import spring.beans.BeansException;
import spring.beans.factory.config.BeanDefinition;

/**
 * 1. 继承了 DefaultSingletonBeanRegistry，也就具备了使用单例注册类方法的能力
 * 2. 实现BeanFactory接口中getBean方法:
 *    2.1 对单例 Bean 对象的获取
 *    2.2 获取不到时：通过getBeanDefinition拿到 Bean 的定义，通过createBean 创建bean对象
 *    2.3 getBeanDefinition 和 createBean【由实现此抽象类的其他类实现】
 * BeanDefinition 注册表接口
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     *  通过反射创建bean，并加入到单例对象集合中
     * @param beanName
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;


}

