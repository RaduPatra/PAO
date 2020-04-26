package pack.radu;

import pack.radu.items.*;
import pack.radu.services.*;

import java.util.*;


public class Main {

    /*
     * 1.add client - done
     * 2.add shop item - done
     * 3.display all clients sorted - done
     * 4.display items owned by client- done?
     * 5.trade with another client - scrap
     * 6.search item in shop by brand - done
     * 7.search items with price > x -tdo
     * 8.client buy item from shop( and add to inv) - done
     * 11. Clients who spent the most sorted (in shop) -maybe
     * */
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
        while (condition) {
            System.out.println("\nTODO - instructions");
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
                case 4://display devices
                    myShopService.displayDevices();
                    break;
                case 5://display client inventory
                    myUserService.displayClients();
                    System.out.println("Select client ID:");
                    int clientid = sc.nextInt();
                    myUserService.displayClientInventory(clientid);
                    break;
                case 6://client - buy item
                    myUserService.displayClients();
                    System.out.println("Select client ID:");
                    int clientid2 = sc.nextInt();
                    myShopService.buyItemFromShop(clientid2);
                    break;
                case 7://admin - add item to shop
                    myUserService.displayAdmins();
                    System.out.println("Select admin ID:");
                    int adminid = sc.nextInt();
                    myShopService.addItemToShop(adminid);
                    break;
                case 8://sort by brand
                    System.out.println("Search by brand:");
                    String brand = sc.nextLine();
                    myShopService.searchDeviceByBrand(brand.toLowerCase());
                    break;
                case 9://sort by max price
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