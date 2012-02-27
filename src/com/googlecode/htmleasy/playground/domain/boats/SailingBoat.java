package com.googlecode.htmleasy.playground.domain.boats;

public class SailingBoat implements Boat {
	
	private int id;
	private String name;
	private double lengthOverAll;
	private double sailArea;
	private int numberOfCrew;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLengthOverAll() {
		return lengthOverAll;
	}
	public void setLengthOverAll(double lengthOverAll) {
		this.lengthOverAll = lengthOverAll;
	}
	public double getSailArea() {
		return sailArea;
	}
	public void setSailArea(double sailArea) {
		this.sailArea = sailArea;
	}
	public int getNumberOfCrew() {
		return numberOfCrew;
	}
	public void setNumberOfCrew(int numberOfCrew) {
		this.numberOfCrew = numberOfCrew;
	}

}
