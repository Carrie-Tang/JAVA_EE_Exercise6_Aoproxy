package test.edu.javaee.spring;

public class Dishes {
	private String dishName;
	private int dishPrice;
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	
	public int getDishPrice() {
		return dishPrice;
	}
	public void setDishPrice(int dishPrice) {
		this.dishPrice = dishPrice;
	}
	public String printDishes()
	{
		return String.format("Dish Name: %s. Dish Price: %d." ,getDishName(),getDishPrice());
	}
}
