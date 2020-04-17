package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;

import java.util.Date;

public class CarWithDate {
    private String brand = null;
    private int doors = 0;
    private Date productionDate=null;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
}
