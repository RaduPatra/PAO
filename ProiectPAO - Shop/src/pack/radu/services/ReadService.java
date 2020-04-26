package pack.radu.services;

import pack.radu.items.*;
import pack.radu.users.*;

import java.io.*;


public class ReadService implements FileLocations {

    private static ReadService readService = null;
    UserService myUserService;
    ShopService myShopService;
    WriteService myWriteService;

    private ReadService(){
        myUserService = UserService.getInstance();
        myShopService = ShopService.getInstance();
        myWriteService = WriteService.getInstance();
    }

    public static ReadService getInstance(){
        if (readService == null)
            readService = new ReadService();
        return readService;
    }

    public void readClientFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CLIENTS_FILE))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] dataFields = currentLine.split(",");
                Client client = new Client(dataFields[0], Integer.parseInt(dataFields[1]), Integer.parseInt(dataFields[2]), Double.parseDouble(dataFields[3]));
                myUserService.addClientToList(client);
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return;
        }
        myWriteService.writeLogsToFile("Read clients from file");
        System.out.println("Successfully read " + myUserService.getClients().size() + " clients!");
    }

    public void readAdminFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ADMINS_FILE))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] dataFields = currentLine.split(",");
                Admin admin = new Admin(dataFields[0], Integer.parseInt(dataFields[1]));
                myUserService.addAdminToList(admin);
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return;
        }
        myWriteService.writeLogsToFile("Read clients from file");
        System.out.println("Successfully read " + myUserService.getAdmins().size() + " clients!");
    }

    public void readPhoneFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PHONES_FILE))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] dataFields = currentLine.split(",");
                Phone phone = new Phone(dataFields[0], Integer.parseInt(dataFields[1]), Integer.parseInt(dataFields[2]),Integer.parseInt(dataFields[3]),Integer.parseInt(dataFields[4]),Integer.parseInt(dataFields[5]),Integer.parseInt(dataFields[6]));
                myShopService.addPhoneToShop(phone);
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return;
        }
        myWriteService.writeLogsToFile("Read phones from file");
        System.out.println("Successfully read " + myShopService.getShop().getPhones().size() + " phones!");
    }

    public void readLaptopFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(LAPTOPS_FILE))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] dataFields = currentLine.split(",");
                Laptop laptop = new Laptop(dataFields[0], Integer.parseInt(dataFields[1]), Integer.parseInt(dataFields[2]),Integer.parseInt(dataFields[3]),Integer.parseInt(dataFields[4]),dataFields[5],dataFields[6]);
                myShopService.addLaptopToShop(laptop);
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return;
        }
        myWriteService.writeLogsToFile("Read laptops from file");
        System.out.println("Successfully read " + myShopService.getShop().getLaptops().size() + " laptops!");
    }
}
