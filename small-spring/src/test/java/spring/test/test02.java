package spring.test;

import org.junit.Test;
import spring.bean.UserService;
import spring.beans.factory.config.BeanDefinition;
import spring.beans.factory.support.DefaultListableBeanFactory;

/**
 * 完成对有参bean的实例化方法 测试
 */
public class test02 {
    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "ly");
        userService.queryUserInfo();
    }
}
