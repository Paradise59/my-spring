package spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import spring.beans.BeansException;
import spring.beans.PropertyValue;
import spring.beans.PropertyValues;
import spring.beans.factory.config.BeanDefinition;
import spring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * 实现了 Bean 的实例化操作 ：实现AbstractBeanFactory 中的 createBean
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    // 定义了一个创建对象的实例化策略属性类 InstantiationStrategy instantiationStrategy，这里我们选择了 Cglib 的实现类
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName,BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;

    }

    /**
     * 有参数的创建bean对象 采用构造器注入
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        // beanClass.getDeclaredConstructors() 方式可以获取到你所有的构造函数，是一个集合
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            // 循环比对出构造函数集合与入参信息 args 的匹配情况, 其实应该还要比对入参类型是否一致，这里简化处理
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * 用于将 Bean 定义中的属性值（包括对其他 Bean 的引用）设置到目标 Bean 实例中
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            // 获取属性集合 从 BeanDefinition 中提取所有属性配置
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 如果value是BeanReference类型，说明该属性引用了另一个Bean,若被引用的 Bean 未初始化，会触发其创建和属性注入流程
                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化,
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}