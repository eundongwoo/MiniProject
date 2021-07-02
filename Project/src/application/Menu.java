package application;

public class Menu {
	
	private String foodName;
	private int price;
	public int count = 0;
	
	Menu(String foodName, int price) {
		this.foodName = foodName;
		this.price = price;		
	}
	
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}


	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
