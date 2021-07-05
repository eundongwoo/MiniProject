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

	
	//지울꺼임 .. 찍어보는 함수
	public void print()
	{
		System.out.println(this.id);
		System.out.println(this.pwd);
		System.out.println(this.name);
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
	
	public void member_info()
	{
		System.out.println("멤버:====================");
		for(Person p: RegisterController.member)
		{
			System.out.println("아이디:"+p.id);
			System.out.println("비밀번호:"+p.pwd);
			System.out.println("이름:"+p.name);
		}
	}
	
}
