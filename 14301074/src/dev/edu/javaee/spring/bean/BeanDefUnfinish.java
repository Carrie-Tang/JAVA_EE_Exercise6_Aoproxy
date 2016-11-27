package dev.edu.javaee.spring.bean;


import java.util.ArrayList;
import java.util.List;

import dev.edu.javaee.spring.factory.BeanFactory;

public class BeanDefUnfinish {
	private BeanDefinition beanDefinition;
	//private BeanFactory beanFactory;
	private String id;
	private List<_Property> list = new ArrayList<>();
	
	public BeanDefUnfinish(/*BeanFactory beanFactory,*/ String id, BeanDefinition beanDefinition){
		this.beanDefinition = beanDefinition;
		this.id = id;
		//this.beanFactory = beanFactory;
	}
	
//	boolean isFinish(){
//		return list.isEmpty();
//	}
	
	public String getId() {
		return id;
	}

	public void put(PropertyValue propertyValue, String ref){
		list.add(new _Property(propertyValue, ref));
	}
	
	//
	public void setAllRefClasses(BeanFactory beanFactory){
		for (_Property p : list) {
			//
			//System.out.println("Ref name£» " + p.refClassName + " & id is " + id);
			
			Object object = beanFactory.getBean(p.refClassName);
			//
		
			p.propertyValue.setValue(object);
			beanDefinition.getPropertyValues().AddPropertyValue(p.propertyValue);
		}
		
		//
		beanFactory.registerBeanDefinition(id, beanDefinition);
	}
	
	
	public BeanDefinition getBeanDefinition() {
		return beanDefinition;
	}


	class _Property{
		private PropertyValue propertyValue;
		private String refClassName;
		
		public _Property(PropertyValue propertyValue, String refClassName) {
			this.propertyValue =propertyValue;
			this.refClassName = refClassName;
		}
		/*
		public PropertyValue getPropertyValue() {
			return propertyValue;
		}

		public String getRefClassName() {
			return refClassName;
		}*/
	}
	
}
