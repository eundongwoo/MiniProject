package application;

import javafx.beans.property.StringProperty;

public class UserTableRowData {

	private StringProperty id;
	private StringProperty pwd;
	private StringProperty name;
	
	
	public UserTableRowData(StringProperty id, StringProperty pwd, StringProperty name) {
	
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}


	public StringProperty idProperty() {
		return id;
	}


	public StringProperty pwdProperty() {
		return pwd;
	}


	public StringProperty nameProperty() {
		return name;
	}

}
