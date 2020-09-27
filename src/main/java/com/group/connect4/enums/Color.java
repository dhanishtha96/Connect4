package com.group.connect4.enums;

public enum Color {
	Y("YELLOW"),
	R("RED"),
	N("NONE");
	
	private final String color;

	Color(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return this.color;
	}
}
