package pack.radu.items;

import java.util.*;

public abstract class Device {

    private String brand;
    private int deviceID;
    private int price;
    private int storage;
    private int ram;

    public Device(String brand, int deviceID, int price, int storage, int ram) {
        this.brand = brand;
        this.deviceID = deviceID;
        this.price = price;
        this.storage = storage;
        this.ram = ram;
    }

    @Override
    public String toString() {
        return "Brand: " + this.brand + ", Device ID: " + this.deviceID + ", Price: " +
                this.price + ", Storage: " + this.storage + ", Ram: " + ram + " ";
    }

    public int calculatePrice() {
        Random ran = new Random();
        float itemprice = getStorage() * (ran.nextFloat() + 1);
        itemprice += getRam() * (5 + ran.nextFloat() * 3);
        return (int)itemprice;
        //this.setPrice((int) itemprice);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
}
