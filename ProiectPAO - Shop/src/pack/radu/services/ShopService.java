package pack.radu.services;

import pack.radu.users.Client;
import pack.radu.Shop;
import pack.radu.items.Device;
import pack.radu.items.Laptop;
import pack.radu.items.Phone;

import java.util.*;
import java.util.Scanner;

public class ShopService {

    private Shop shop;
    private static ShopService shopService = null;
    private UserService myUserService;
    WriteService myWriteService;

    private ShopService() {
        shop = Shop.getInstance();
        myUserService = UserService.getInstance();
        myWriteService = WriteService.getInstance();
    }

    public static ShopService getInstance() {
        if (shopService == null)
            shopService = new ShopService();
        return shopService;
    }

    public Device pickItemFromShop() {
        Scanner sc = new Scanner(System.in);
        int option;
        System.out.println("Do you want to buy a Phone or Laptop?");
        System.out.println("1) Laptop");
        System.out.println("2) Phone");
        option = sc.nextInt();
        Device device;
        int index, ind;
        switch (option) {
            case 1:
                index = 0;
                for (Laptop lap : shop.getLaptops()) {
                    System.out.println(index + ") " + lap);
                    index++;
                }

                System.out.println("Pick a laptop to buy");
                Scanner s1 = new Scanner(System.in);
                ind = s1.nextInt(); //pick laptop ind
                device = shop.getLaptops().get(ind);
                System.out.println("Shop closed!");
                break;
            case 2:
                index = 0;
                for (Phone phone : shop.getPhones()) {
                    System.out.println(index + ") " + phone);
                    index++;
                }

                System.out.println("Pick a phone to buy");
                Scanner s2 = new Scanner(System.in);
                ind = s2.nextInt();
                device = shop.getPhones().get(ind);
                System.out.println("Shop closed!");
                break;
            default:
                throw new IllegalArgumentException("invalid argument");
        }
        myWriteService.writeLogsToFile("Picked device from shop");
        return device;
    }

    public void buyItemFromShop(int clientid) {

        Client myClient = myUserService.getClientMap().get(clientid);
        Device pickeditem = pickItemFromShop();
        int itemprice = pickeditem.getPrice();
        int clientbalance = myClient.getBalance();

        if (itemprice <= clientbalance) {
            clientbalance -= itemprice;
            myClient.setBalance(clientbalance);
            myClient.getInventory().add(pickeditem);
            System.out.println("pickeditem debug:" + pickeditem.getDeviceID());
            shop.deleteItem(pickeditem.getDeviceID());
            myWriteService.writeLogsToFile("Bought device from shop");
        } else {
            System.out.println("You dont have enough money!");
            myWriteService.writeLogsToFile("Couldn't buy device from shop");
        }
    }

    public void addItemToShop(int adminid) throws NullPointerException {

        if (myUserService.getAdminMap().get(adminid) == null) {
            throw new NullPointerException("Access denied - you have to be an admin");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter brand name: ");
        String brand = sc.nextLine();
        System.out.println("Enter device id: ");
        int deviceid = sc.nextInt();
        System.out.println("Enter device price: ");
        int price = sc.nextInt();
        System.out.println("Enter device storage: ");
        int storage = sc.nextInt();
        System.out.println("Enter device ram: ");
        int ram = sc.nextInt();

        int option;
        System.out.println("Do you want to add a Phone or Laptop?");
        System.out.println("1) Laptop");
        System.out.println("2) Phone");
        option = sc.nextInt();
        Device newdevice;
        Scanner sc2 = new Scanner(System.in);
        switch (option) {
            case 1:
                System.out.println("Enter laptop storage type: ");
                String storagetype = sc2.nextLine();
                System.out.println("Enter laptop gpu type: ");
                String gputype = sc2.nextLine();
                newdevice = new Laptop(brand, deviceid, price, storage, ram, storagetype, gputype);
                break;

            case 2:
                System.out.println("Enter phone battery life: ");
                int batterylife = sc2.nextInt();
                System.out.println("Enter phone camera mega pixels: ");
                int cameramp = sc2.nextInt();
                newdevice = new Phone(brand, deviceid, price, storage, ram, batterylife, cameramp);
                break;

            default:
                throw new RuntimeException("unrecognized device");
        }
        shop.addItem(newdevice);
        myWriteService.writeLogsToFile("Added device to shop");
    }

    public void displayDevices() {
        for (Map.Entry elem : shop.getDeviceMap().entrySet()) {
            Device dev = (Device) elem.getValue();
            System.out.println(dev);
        }
        myWriteService.writeLogsToFile("Display devices");
    }

    public void searchPhonesByBrand(String brand) {
        for (Phone phone : shop.getPhones()) {
            if (brand.equals(phone.getBrand())) {
                System.out.println(phone);
            }
        }
        myWriteService.writeLogsToFile("Search phone by brand");
    }

    public void searchLaptopsByBrand(String brand) {
        for (Laptop laptop : shop.getLaptops()) {
            if (brand.equals(laptop.getBrand())) {
                System.out.println(laptop);
            }
        }
        myWriteService.writeLogsToFile("Search laptop by brand");
    }

    public void searchDeviceByBrand(String brand) {
        Iterator it = shop.getDeviceMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry el = (Map.Entry) it.next();
            Device dev = (Device) el.getValue();
            if (((Device) el.getValue()).getBrand().toLowerCase().equals(brand)) {
                System.out.println(dev);
            }
        }
        myWriteService.writeLogsToFile("Search device by brand");
    }

    public void searchDeviceByPrice(int price) {
        Iterator it = shop.getDeviceMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry el = (Map.Entry) it.next();
            Device dev = (Device) el.getValue();
            if (((Device) el.getValue()).getPrice() <= price) {
                System.out.println(dev);
            }
        }
        myWriteService.writeLogsToFile("Search device by price");
    }

    public void addPhoneToShop(Phone phone) {
        shop.getPhones().add(phone);
        shop.getDeviceMap().put(phone.getDeviceID(), phone);
        myWriteService.writeLogsToFile("Add phone to shop");
    }

    public void addLaptopToShop(Laptop laptop) {
        shop.getLaptops().add(laptop);
        shop.getDeviceMap().put(laptop.getDeviceID(), laptop);
        myWriteService.writeLogsToFile("Added laptop to shop");
    }

    public Shop getShop() {
        return shop;
    }
}
