package com.hcl.ticketbooking;

import java.util.Arrays;

import javax.management.RuntimeErrorException;

public class Demo {

	public static void main(String[] args) {
		try{main1();}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	public static void main1() throws Exception {
		// TODO Auto-generated method stub
		try {
			method();//throw new Exception("Hi");
		}catch(RuntimeExp ex) {
			System.out.println("hiiii");
			throw new RuntimeExp(ex.toString());
		}
	}
	static void method() throws Exception{
		throw new RuntimeExp("hello");
	}

}
