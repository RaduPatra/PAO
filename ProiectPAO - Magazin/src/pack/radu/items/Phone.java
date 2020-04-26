package pack.radu.items;

public class Phone extends Device {

    private int batterylife;
    private int cameraMP;

    public Phone(String brand, int deviceID, int price, int storage, int ram, int batterylife, int cameraMP) {
        super(brand, deviceID, price, storage, ram);
        this.batterylife = batterylife;
        this.cameraMP = cameraMP;
    }

    @Override
    public String toString() {
        return super.toString() + ",Battery life: " + batterylife + ", Camera MegaPixels: " + cameraMP + " ";
    }

    @Override
    public int calculatePrice() {
        float itemprice = super.calculatePrice();
        itemprice += 100 * (getBatterylife() + getCameraMP());
        return (int) itemprice;
        //this.setPrice((int)itemprice);
    }


    public int getBatterylife() {
        return batterylife;
    }

    public void setBatterylife(int batterylife) {
        this.batterylife = batterylife;
    }

    public int getCameraMP() {
        return cameraMP;
    }

    public void setCameraMP(int cameraMP) {
        this.cameraMP = cameraMP;
    }
}
