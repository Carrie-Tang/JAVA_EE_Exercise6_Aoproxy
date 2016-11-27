package test.edu.javaee.spring;

import Aopproxy.FooInterface;
import Aopproxy.LocalFileResource;
import Aopproxy.ProxyFactoryBean;
//import Aopproxy.ProxyFactoryBean;
import dev.edu.javaee.spring.factory.BeanFactory;
import dev.edu.javaee.spring.factory.XMLBeanFactory;
//import test.boss;
//import test.car;
//import test.office;

public class testmain {

	public static void main(String[] args) {
		/*try {
			BeanFactory beanFactory = new XMLBeanFactory("bean.xml");
			boss boss = (boss) beanFactory.getBean("boss");
			
			System.out.println(boss.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		LocalFileResource resource = new LocalFileResource("xml/aop.xml");
		BeanFactory beanFactory = new XMLBeanFactory(resource);
//		((ProxyFactoryBean)beanFactory.getBean("foo")).proxy();
	    FooInterface foo = (FooInterface)beanFactory.getBean("foo");
	    foo.printFoo();
	    foo.dummyFoo();
//		ProxyFactoryBean pfb = (ProxyFactoryBean)beanFactory.getBean("foo");
//		System.out.println(pfb.getInterceptorNames());
//		System.out.println(pfb.getProxyInterfaces());
//		System.out.println(pfb.getTarget());
	}

}
