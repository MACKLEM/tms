package com.briup.Macklem.ch00;


public class Teacher{
	private long id;
	private String name;
	private int age;

	public Teacher(){
	
	}
	public Teacher(long id,String name,int age){
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public void setId(long id){
		this.id = id;
	}
	public long getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setAge(int age){
		this.age = age;
	}
	public int getAge(){
		return this.age;
	}
	public String toString(){
		return "Teacher2016/4/27[id:"+this.id+",name:"+this.name+",age:"+this.age+"]";
	}
}