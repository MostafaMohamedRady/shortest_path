package com.algorythma.shipping.model;

public class Person implements Comparable<Person>{
	
	private final String name;
	private Neighbor[] network;
	private double minHardness = Double.POSITIVE_INFINITY;
	private Person previous;
    
    public Person(String name) {
    	this.name = name;
    }
    
    public String toString() {
    	return name; 
    }        
    
	public Neighbor[] getNetwork() {
		return network;
	}

	public void setNetwork(Neighbor[] network) {
		this.network = network;
	}

	public double getMinHardness() {
		return minHardness;
	}

	public void setMinHardness(double minHardness) {
		this.minHardness = minHardness;
	}

	public Person getPrevious() {
		return previous;
	}

	public void setPrevious(Person previous) {
		this.previous = previous;
	}

	public String getName() {
		return name;
	}

	public int compareTo(Person other)
    {
        return Double.compare(minHardness, other.minHardness);
    }
    
}
