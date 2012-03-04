package com.googlecode.htmleasy.playground.domain.boats;

import com.googlecode.htmleasy.ViewWith;

/**
 * A simple sailing boat POJO.  Notice how its annotated with a view.  This default view will be used
 * to render this object if any controller method returns this type (unless overridden with a view on the method).
 */
@ViewWith("/soy/playground.boats.SailingBoatView")
public class SailingBoat implements Boat {

    private int id;
    private String name;
    private String description;
    private String price;
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

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
