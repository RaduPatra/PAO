package pack.radu;

import pack.radu.items.Device;
import pack.radu.items.Laptop;
import pack.radu.items.Phone;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    private ArrayList<Laptop> laptops = new ArrayList<>();
    private ArrayList<Phone> phones = new ArrayList<>();
    private HashMap<Integer, Device> deviceMap = new HashMap<>();
    private static Shop shop = null;

    public static Shop getInstance()
    {
        if (shop == null)
            shop = new Shop();
        return shop;
    }

    public void addItem(Device device) {
        if (device instanceof Laptop) {
            laptops.add((Laptop) device);
        } else {
            phones.add((Phone) device);
        }
        //device.calculatePrice();
        deviceMap.put(device.getDeviceID(), device);
    }

    public void deleteItem(int itemid) {

        if (deviceMap.get(itemid) instanceof Laptop) {
            int index = 0;
            for (Laptop laptop : laptops) {
                if (itemid == laptop.getDeviceID()) {
                    laptops.remove(index);
                    index++;
                    break;
                }
            }
        } else if (deviceMap.get(itemid) instanceof Phone) {
            int index = 0;
            for (Phone phone : phones) {
                if (itemid == phone.getDeviceID()) {
                    phones.remove(index);
                    index++;
                    break;
                }
            }
        }
        deviceMap.remove(itemid);
    }

    public ArrayList<Laptop> getLaptops() {
        return laptops;
    }

    public ArrayList<Phone> getPhones() {
        return phones;
    }

    public HashMap<Integer, Device> getDeviceMap() {
        return deviceMap;
    }
}
