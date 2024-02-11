package com.epam.rd.autocode.assessment.basics.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Vehicle implements Serializable {

    protected long id;
    protected String make;
    protected String model;
    protected String characteristics;
    protected int yearOfProduction;
    protected long odometer;
    protected String color;
    protected String licensePlate;
    protected int numberOfSeats;
    protected BigDecimal price;
    protected char requiredLicense;
    protected BodyType bodyType;

    public Vehicle() {
    }

    public Vehicle(long id, String make, String model, String characteristics, int yearOfProduction, long odometer,
            String color, String licensePlate, int numberOfSeats, BigDecimal price, char requiredLicense,
            BodyType bodyType) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.characteristics = characteristics;
        this.yearOfProduction = yearOfProduction;
        this.odometer = odometer;
        this.color = color;
        this.licensePlate = licensePlate;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.requiredLicense = requiredLicense;
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return String.format(
                "Vehicle{id=%d, make='%s', model='%s', characteristics='%s', yearOfProduction=%d, odometer=%d, color='%s', licensePlate='%s', numberOfSeats=%d, price=%s, requiredLicense=%s, bodyType=%s}",
                id, make, model, characteristics, yearOfProduction, odometer, color, licensePlate, numberOfSeats, price,
                requiredLicense, bodyType);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((make == null) ? 0 : make.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + ((characteristics == null) ? 0 : characteristics.hashCode());
        result = prime * result + yearOfProduction;
        result = prime * result + (int) (odometer ^ (odometer >>> 32));
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
        result = prime * result + numberOfSeats;
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + requiredLicense;
        result = prime * result + ((bodyType == null) ? 0 : bodyType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehicle other = (Vehicle) obj;
        if (id != other.id)
            return false;
        if (make == null) {
            if (other.make != null)
                return false;
        } else if (!make.equals(other.make))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (characteristics == null) {
            if (other.characteristics != null)
                return false;
        } else if (!characteristics.equals(other.characteristics))
            return false;
        if (yearOfProduction != other.yearOfProduction)
            return false;
        if (odometer != other.odometer)
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (licensePlate == null) {
            if (other.licensePlate != null)
                return false;
        } else if (!licensePlate.equals(other.licensePlate))
            return false;
        if (numberOfSeats != other.numberOfSeats)
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (requiredLicense != other.requiredLicense)
            return false;
        if (bodyType != other.bodyType)
            return false;
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public long getOdometer() {
        return odometer;
    }

    public void setOdometer(long odometer) {
        this.odometer = odometer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public char getRequiredLicense() {
        return requiredLicense;
    }

    public void setRequiredLicense(char requiredLicense) {
        this.requiredLicense = requiredLicense;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

}
