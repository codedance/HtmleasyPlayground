package com.googlecode.htmleasy.playground.domain.boats;

public class PowerBoat implements Boat {
	
	private int id;
	private String name;
	private double lengthOverAll;
	private double enginePower;
	private double maxSpeedKnots;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public double getLengthOverAll() {
		return lengthOverAll;
	}
	public void setLengthOverAll(double lengthOverAll) {
		this.lengthOverAll = lengthOverAll;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getEnginePower() {
		return enginePower;
	}
	public void setEnginePower(double enginePower) {
		this.enginePower = enginePower;
	}
	public double getMaxSpeedKnots() {
		return maxSpeedKnots;
	}
	public void setMaxSpeedKnots(double maxSpeedKnots) {
		this.maxSpeedKnots = maxSpeedKnots;
	}
}
