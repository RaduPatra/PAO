package pack.radu.items;

import java.util.Random;

public class Laptop extends Device {

    private String storagetype;
    private String gputype;

    public Laptop(String brand, int deviceID, int price, int storage, int ram, String storagetype, String gputype) {
        super(brand, deviceID, price, storage, ram);
        this.storagetype = storagetype;
        this.gputype = gputype;
    }

    @Override
    public String toString() {
        return super.toString() + ",Storage type: " + storagetype + ", Gpu Type: " + gputype + " ";
    }

    @Override
    public int calculatePrice() {
        float itemprice = super.calculatePrice();
        Random rand = new Random();

        if (getStoragetype().toLowerCase().equals("ssd"))
            itemprice *= 1.5;
        else if (getStoragetype().toLowerCase().equals("hdd"))
            itemprice *= 1.1;

        if (getGputype().toLowerCase().equals("dedicated"))
            itemprice += 1000 + rand.nextFloat() * 1500;
        else if (getGputype().toLowerCase().equals("integrated"))
            itemprice += 500 + rand.nextFloat() * 1000;

        return (int) itemprice;
        //this.setPrice((int)itemprice);
    }

    public String getStoragetype() {
        return storagetype;
    }

    public void setStoragetype(String storagetype) {
        this.storagetype = storagetype;
    }

    public String getGputype() {
        return gputype;
    }

    public void setGputype(String gputype) {
        this.gputype = gputype;
    }
}
