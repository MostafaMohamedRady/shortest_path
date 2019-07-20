package com.algorythma.shipping.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ShippingScenario {
	
	private Person target;
	private Package packageInfo;
	private double testingCost;
	
	public ShippingScenario(Person person, String[] packageInfo, double cost) {
		this.target = person;
		this.packageInfo=new Package(packageInfo[0],packageInfo[1],packageInfo[2],packageInfo[3]);
		this.testingCost = cost;
	}
	
	public Person getTarget() {
		return target;
	}
	public void setTarget(Person target) {
		this.target = target;
	}
	public double getTestingCost() {
		return testingCost;
	}
	public void setTestingCost(double testingCost) {
		this.testingCost = testingCost;
	}

	public Package getPackageInfo() {
		return packageInfo;
	}

	public void setPackageInfo(Package packageInfo) {
		this.packageInfo = packageInfo;
	}

	/**
	 * calculate the cost of shipping
	 * @return calculatedCost
	 */
	public double calculatedCost(){
		double cost = Math.sqrt(target.getMinHardness()) * packageInfo.getNormalizedWeight();
		if (cost != Double.POSITIVE_INFINITY) {
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.HALF_UP);
			cost = Double.valueOf(df.format(cost));
		}
		return cost;
	}

}
