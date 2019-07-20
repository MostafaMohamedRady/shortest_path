package com.algorythma.shipping.model;

public class Neighbor {
	
	private final Person friend;
    private final double hardness;
    
    public Neighbor(Person target, double hardness) { 
    	this.friend = target;
    	this.hardness = hardness;
    }

	public Person getFriend() {
		return friend;
	}

	public double getHardness() {
		return hardness;
	}   	
    
}
