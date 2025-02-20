package spring.test;

import org.junit.Test;
import spring.bean.UserDao;
import spring.bean.UserService;
import spring.beans.PropertyValue;
import spring.beans.PropertyValues;
import spring.beans.factory.config.BeanDefinition;
import spring.beans.factory.config.BeanReference;
import spring.beans.factory.support.DefaultListableBeanFactory;

/**
 * 先把 userDao 注入到 Bean 容器中，然后再把 userService 注入到 Bean 容器中
 */
public class test03 {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
