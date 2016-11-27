package test;

import test.annotation.Autowired;

public class boss 
{
  private office office;
  private car car;
  
  public boss() 
{
	
  }
  
  @Autowired
  public boss(car car ,office office)
{
      this.car = car;
      this.office = office ;
  }

  public String toString()
{
	  return "Boss has "+ car.toString()+" and in "+ office.toString();
  }
}
