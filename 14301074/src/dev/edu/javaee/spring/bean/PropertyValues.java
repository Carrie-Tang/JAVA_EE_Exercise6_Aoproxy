package dev.edu.javaee.spring.bean;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
	private List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
	public void AddPropertyValue(PropertyValue propertyValue){
		propertyValues.add(propertyValue);
	}
	public List<PropertyValue> GetPropertyValues()
	{
		return propertyValues;
	}
	
	public String toString(){
		String string = new String("--PropertyValues:\n");
		for (PropertyValue propertyValue : propertyValues) {
			string += "Name: " + propertyValue.getName() + 
					" Value: " + propertyValue.getValue() + "\n";
		}
		
		return string;
	}
}
