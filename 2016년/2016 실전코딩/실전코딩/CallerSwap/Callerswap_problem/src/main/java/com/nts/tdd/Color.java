package com.nts.tdd;

public class Color {

	private String color;

	public String getColor() {
		return color;
	}

	public Color(String color) {
		this.color = color;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        
        
        return ((Color)obj).getColor() == color;
	}
}
