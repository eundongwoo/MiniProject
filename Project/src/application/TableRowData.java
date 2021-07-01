package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class TableRowData {
	private StringProperty name;
	private IntegerProperty price;
	
	public TableRowData(StringProperty name, IntegerProperty price) {
		this.name = name;
		this.price = price;
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	public IntegerProperty priceProperty() {
		return price;
	}

}
