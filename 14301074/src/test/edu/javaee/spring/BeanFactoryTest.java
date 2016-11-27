package test.edu.javaee.spring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dev.edu.javaee.spring.bean.BeanDefinition;
import dev.edu.javaee.spring.bean.PropertyValue;
import dev.edu.javaee.spring.bean.PropertyValues;
import dev.edu.javaee.spring.factory.BeanFactory;
import dev.edu.javaee.spring.factory.XMLBeanFactory;
import test.boss;

public class BeanFactoryTest {

	/*@Test
	public void testBeanCreateAndGet() {
		BeanDefinition beanDefinition = new BeanDefinition();
		beanDefinition.setBeanClassName("test.edu.javaee.spring.Dishes");
		
		PropertyValue dishName = new PropertyValue();
		dishName.setName("dishName");
		dishName.setValue("fry eggs");
		
		PropertyValue dishPrice = new PropertyValue();
		dishPrice.setName("dishPrice");
		dishPrice.setValue(20);
		
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.AddPropertyValue(dishName);
		propertyValues.AddPropertyValue(dishPrice);
		
		beanDefinition.setPropertyValues(propertyValues);
		
		
		BeanFactory beanFactory = new XMLBeanFactory();
		// the BeanDefinition doesn`t create the real bean yet
		beanFactory.registerBeanDefinition("egg dishes", beanDefinition);
		Dishes d = (Dishes) beanFactory.getBean("egg dishes");
		
		assertEquals(d.printDishes(), "Dish Name: fry eggs. Dish Price: 20.");
	}*/
	
	//
//	@Test
//	public void testXML(){
//		
//		
//	}

}
