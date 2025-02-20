package spring.beans.factory.support;

import net.sf.cglib.proxy.NoOp;
import spring.beans.BeansException;
import spring.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements  InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        // 创建 CGLIB 的增强器对象 Enhancer
        Enhancer enhancer = new Enhancer();
        // 指定代理类的父类
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        // 设置回调逻辑
        enhancer.setCallback(new NoOp() {
            // NoOp 的作用： 不拦截任何方法调用，代理类直接调用父类（原始类）的方法
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        // 若构造函数 ctor 为 null，调用无参构造函数创建代理对象
        if (null == ctor) return enhancer.create();
        // 若构造函数存在，通过参数类型和值创建代理对象。
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}
