package pack.radu.users;

import pack.radu.items.Device;
import pack.radu.users.User;

import java.util.ArrayList;

public class Client extends User implements Comparable<Client> {
    private int balance;
    private double discount;
    public ArrayList<Device> inventory = new ArrayList<>();


    public Client() {
        super();
    }

    public Client(String name, int id, int balance, double discount) {
        super(name, id);
        this.discount = discount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Client: " + super.toString() + ", Balance: " + this.balance + ",Discount: " + this.discount + " ";
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public ArrayList<Device> getInventory() {
        return inventory;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public int compareTo(Client comparesto) {
        int comparebal = comparesto.getBalance();
        return comparebal - this.balance;
    }

    public void setInventory(ArrayList<Device> inventory) {
        this.inventory = inventory;
    }


}
