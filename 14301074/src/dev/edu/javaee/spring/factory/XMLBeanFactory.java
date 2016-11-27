package dev.edu.javaee.spring.factory;


import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Aopproxy.FooInterface;
import Aopproxy.ProxyFactoryBean;
import Aopproxy.Resource;
import dev.edu.javaee.spring.bean.BeanDefUnfinish;
import dev.edu.javaee.spring.bean.BeanDefinition;
import dev.edu.javaee.spring.bean.BeanUtil;
import dev.edu.javaee.spring.bean.PropertyValue;
import dev.edu.javaee.spring.bean.PropertyValues;
import test.car;
import test.annotation.Autowired;

public class XMLBeanFactory extends AbstractBeanFactory{
	public XMLBeanFactory(){}
	
	public XMLBeanFactory(Resource resource){
		
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(resource.getInputStream());
			List<BeanDefUnfinish> bufList = new ArrayList<>();
			//
			NodeList list = doc.getElementsByTagName("bean");
			//
			for(int i = 0; i < list.getLength(); i++){
				Element element = (Element) list.item(i);
				//get properties
				String idValue, classValue;
				idValue = element.getAttribute("id");
				classValue = element.getAttribute("class");
				//
				NodeList properties = element.getElementsByTagName("property");
//				System.out.println("id: " + idValue + " name: " + classValue);
//				System.out.println("Found : " + properties.getLength());
				//
				BeanDefinition bdf = new BeanDefinition();
				bdf.setBeanClassName(classValue);
				PropertyValues pvs = new PropertyValues();
				boolean found = true;
				//bean unfinshed for ref
				BeanDefUnfinish buf = new BeanDefUnfinish(idValue, bdf);
				
				for(int j = 0; j < properties.getLength(); j++){
					Element e = (Element) properties.item(j);
					PropertyValue pv = new PropertyValue();
					String refName = e.getAttribute("ref");
					String name = e.getAttribute("name");
					String value = e.getAttribute("value");
					//
					pv.setName(name);
					if(!refName.isEmpty()){
						String clsName = BeanUtil.getClassName(refName);
						if(clsName == null){
							//
							found = false;
							buf.put(pv, refName);
						}else{
							BeanUtil.addRefObj(clsName, pv, pvs);
						}
					}else{
						//handle with inner ref and value
						NodeList refsList = e.getElementsByTagName("ref");
						int refCount = refsList.getLength();
						if(refCount > 0){
							refName = ((Element)refsList.item(0)).getAttribute("local");
							//Node fir =  refsList.item(0).getFirstChild();
							//System.out.println("fir is null?" + (fir == null));
							//log
							//System.out.println("regetting refName: " + refName);
							String clsName = BeanUtil.getClassName(refName);
							if(clsName == null){
								//
								found = false;
								buf.put(pv, refName);
							}else{
								BeanUtil.addRefObj(clsName, pv, pvs);
							}
						}
						else{
							//parse inner <value>
							NodeList valueList = e.getElementsByTagName("value");
							if(valueList.getLength() > 0){
								value = valueList.item(0).getFirstChild().getNodeValue();
//								//log
//								System.out.println("inner value parse: " + value);
							}
							pv.setValue(value);
							pvs.AddPropertyValue(pv);
						}
					}
				}
				//
				bdf.setPropertyValues(pvs);
				if(found){
					//System.out.println(idValue + " is registered");
					registerBeanDefinition(idValue, bdf);
				}else{
					bufList.add(buf);
				}
			}
			
			//remain unregistered bean
			if(!bufList.isEmpty()){
//				//log
//				System.out.println("get in");
				for (BeanDefUnfinish b : bufList) {
					b.setAllRefClasses(this);
					//
					//check exceptions if b is a factory bean
					Object bean = b.getBeanDefinition().getBean();
					if(bean instanceof ProxyFactoryBean) {
//						//log
//						System.out.println("found factory bean");
						//
						ProxyFactoryBean pfb = (ProxyFactoryBean) bean;
						pfb.setFactory(this);
						//
						pfb.setup(b.getId());
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	@Override
	protected BeanDefinition GetCreatedBean(BeanDefinition beanDefinition) {
		String beanClassName = beanDefinition.getBeanClassName();
		try {
			// set BeanClass for BeanDefinition
			Class<?> beanClass = Class.forName(beanClassName);
			beanDefinition.setBeanClass(beanClass);
			// set Bean Instance for BeanDefinition
			Object bean = beanDefinition.getBean();
			if(bean == null)
				bean = beanClass.newInstance();	
			else{
//				//log
//				System.out.println("I'm already existing");
			}
			
			PropertyValues pvs = beanDefinition.getPropertyValues();
//			//log
//			System.out.println("pvs is null?" + (pvs == null));
			
			
			//
			if(pvs == null){
				return beanDefinition;
			}
			
			List<PropertyValue> fieldDefinitionList = pvs.GetPropertyValues();
			//check
			if(beanClass.getName().equals("test.boss")){
				for(PropertyValue propertyValue: fieldDefinitionList)
				{
					BeanUtil.setField(bean, propertyValue.getName(), propertyValue.getValue());
				}
				//
				beanDefinition.setBean(bean);
				return beanDefinition;
			}
			
			for(PropertyValue propertyValue: fieldDefinitionList)
			{
				BeanUtil.invokeSetterMethod(bean, propertyValue.getName(), propertyValue.getValue());
			}
			
			beanDefinition.setBean(bean);
			
			return beanDefinition;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
