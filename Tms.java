package com.briup.Macklem.ch00;

import java.util.Scanner;

public class Tms {
	
	private Teacher[] teas = new Teacher[3];
	private int index = 0; 
	
	public void add(Teacher tea){
		
		if(index>=teas.length){
			
			Teacher[] demo = new Teacher[teas.length+3];
			
			System.arraycopy(teas,0,demo,0,teas.length);
			teas = demo;
		}
		teas[index++] = tea;;
	}
	
	public void deleteById(long id){
		
		int teaIndex = queryIndexById(id); // 1
		if(teaIndex!=-1){
			for(int i=teaIndex;i<index-1;i++){
				teas[i] = teas[i+1];
			}
			teas[--index] = null;
		}
	}
	
	
	private int queryIndexById(long id){
		int teaIndex= -1;
		for(int i=0;i<index;i++){
			if(teas[i].getId() == id){
				teaIndex = i;
				break;
			}
		}
		return teaIndex;
	}
	
	public Teacher queryById(long id){
		
		int teaIndex = queryIndexById(id);
		return teaIndex==-1?null:teas[teaIndex];
	}

	
	public Teacher[] queryAll(){
		Teacher[] demo = new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
		return demo;
	}
	
	public void update(Teacher tea){
		for(int i=0;i<index;i++){
			if(tea.getId() == teas[i].getId()){
				teas[i].setName(tea.getName());
				teas[i].setAge(tea.getAge());
			}
		}
	}
	
	public void menu(){
		System.out.println("********教师管理系统*******");
		System.out.println("*1，查看所有教师信息*");
		System.out.println("*2，添加教师信息*");
		System.out.println("*3，删除教师信息*");
		System.out.println("*4，查询教师信息*");
		System.out.println("*5，修改教师信息*");
		System.out.println("*exit，退出*");
		System.out.println("*help，帮助*");
		System.out.println("***************************");
	}

	public static void main(String[] args){
		
		Tms tms = new Tms();
		tms.menu();	
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("请输入功能编号：");
			
			String option = scanner.nextLine();
			switch(option){
				case "1":
					System.out.println("以下是所有教师的信息：");
					Teacher[] teas = tms.queryAll();
					for(Teacher tea : teas){
						System.out.println(tea);
					}
					System.out.println("总计："+teas.length+" 人");
					break;
				case "2":
					while(true){
						System.out.println("请输入教师信息【id#name#age】或者输入break回到上一级目录");
						String teaStr = scanner.nextLine();
						if(teaStr.equals("break")){
							break;
						}
						String[] teaArr = teaStr.split("#");
						long id = Long.parseLong(teaArr[0]);
						String name = teaArr[1];
						int age = Integer.parseInt(teaArr[2]);
						
						Teacher tea = new Teacher(id,name,age);
						
						Teacher dbTea = tms.queryById(id);
						if(dbTea!=null){
							System.out.println("该id已经被人占用，请重新录入！");
							continue;
						}

						tms.add(tea);
						System.out.println("添加成功！");
					}
					
					break;
				case "3"://删除
					while(true){
						System.out.print("请输入您要删除教师的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						tms.deleteById(Long.parseLong(id));
						System.out.println("删除成功！教师个数为："+tms.index);
					}
					break;
				case "4"://查询
					while(true){
						System.out.print("请输入您要查询教师的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.queryById(Long.parseLong(id));
						System.out.println("以下是您要查找的教师的信息：");
						System.out.println(tea!=null?tea:"not found!");
					}
					break;
				case "5"://修改
					while(true){
						System.out.print("请输入您要修改教师的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.queryById(Long.parseLong(id));
						if(tea == null){
							System.out.println("该教师不存在！");
							continue;
						}
						System.out.println("原信息为："+tea);
						System.out.println("请输入您要修改的信息【name#age】");
						String teaStr = scanner.nextLine();
						String[] teaArr = teaStr.split("#");

						String name = teaArr[0];
						int age = Integer.parseInt(teaArr[1]);

						Teacher newTea = new Teacher(Long.parseLong(id),name,age);

						tms.update(newTea);
						System.out.println("修改成功！");
					}
					break;
				case "help":
					tms.menu();
					break;
				case "exit":
					System.out.println("bye bye");
					System.exit(0);
				default:
					System.out.println("输入出错，请重新输入！");
			}
		}
			
	}
}