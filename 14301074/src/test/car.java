package test;

import test.annotation.Component;

@Component("car")
public class car 
{
	private String carId="888";
	private String carColor="red";
	
    public String getCarId() 
{
		return carId;
	}

	public void setCarId(String carId) 
{
		this.carId = carId;
	}

	public String getCarColor() 
{
		return carColor;
	}

	public void setCarColor(String carColor) 
{
		this.carColor = carColor;
	}

	public String toString()
{
    	return "Car is " +carColor+" with " +carId;
    }
}
