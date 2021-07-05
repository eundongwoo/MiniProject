package application;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Person {

	private String id;
	private String pwd;
	private String name;
	private String money="0";
	
	public Person() {
	}
	public Person(String id,String pwd)
	{
		this.id = id;
		this.pwd = pwd;
	}

	public Person(String id, String pwd, String name) {
		this(id,pwd);
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getPwd() {
		return pwd;
	}

	public String getName() {
		return name;
	}

	public String getMoney() {
		return money;
	}
	
	public void setMoney(String money) {
		this.money = money;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Person)
		{
			Person p=(Person)obj;  
			return p.id.equals(id)&& p.pwd.equals(pwd);
		}else
		{
			return false;  
		}
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id.hashCode();
	}
	
	
}
