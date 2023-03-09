package com.schoollog.security;

public class Views {
	public static class Student {}
	public static class Parent extends Student {}
	public static class Teacher extends Parent {}
	public static class Administrator extends Teacher {}
}
