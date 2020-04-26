package pack.radu;

import pack.radu.items.*;
import pack.radu.services.*;

import java.util.*;


public class Main {

    public static void main(String[] args) {

        UserService myUserService = UserService.getInstance();
        ShopService myShopService = ShopService.getInstance();
        ReadService myReadService = ReadService.getInstance();
        WriteService myWriteService = WriteService.getInstance();
        myReadService.readClientFromFile();
        myReadService.readPhoneFromFile();
        myReadService.readLaptopFromFile();
        myReadService.readAdminFromFile();

        Scanner sc = new Scanner(System.in);
        int option;
        boolean condition = true;
        System.out.println("1. Add client");
        System.out.println("2. Add admin");
        System.out.println("3. Sort and display clients by balance");
        System.out.println("4. Display devices from shop");
        System.out.println("5. Display client inventory");
        System.out.println("6. Client - buy item from shop");
        System.out.println("7. Admin - add item to shop");
        System.out.println("8. Display items by brand");
        System.out.println("9. Display items by max price");
        System.out.println("10. Calculate price by parts");
        System.out.println("0. Close app");
        while (condition) {

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    myUserService.addClient();
                    break;
                case 2:
                    myUserService.addAdmin();
                    break;
                case 3:
                    myUserService.sortAndDisplayClients();
                    break;
                case 4:
                    myShopService.displayDevices();
                    break;
                case 5:
                    myUserService.displayClients();
                    System.out.println("Select client ID:");
                    int clientid = sc.nextInt();
                    myUserService.displayClientInventory(clientid);
                    break;
                case 6:
                    myUserService.displayClients();
                    System.out.println("Select client ID:");
                    int clientid2 = sc.nextInt();
                    myShopService.buyItemFromShop(clientid2);
                    break;
                case 7:
                    myUserService.displayAdmins();
                    System.out.println("Select admin ID:");
                    int adminid = sc.nextInt();
                    myShopService.addItemToShop(adminid);
                    break;
                case 8:
                    System.out.println("Search by brand:");
                    String brand = sc.nextLine();
                    myShopService.searchDeviceByBrand(brand.toLowerCase());
                    break;
                case 9:
                    System.out.println("Search by maximum price:");
                    int price = sc.nextInt();
                    myShopService.searchDeviceByPrice(price);
                    break;
                case 10:
                    System.out.println("Calculate price by parts: ");
                    Device device = myShopService.pickItemFromShop();
                    System.out.println(device.calculatePrice());
                    break;

                case 0:
                    condition = false;
                    System.out.println("App closed!");
                    break;

                default:
                    System.out.println("Wrong option!");
                    break;
            }
        }
        myWriteService.closeLogs();
    }
}