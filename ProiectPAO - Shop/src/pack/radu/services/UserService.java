package pack.radu.services;

import pack.radu.users.*;
import pack.radu.items.Device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import pack.radu.repository.*;

public class UserService {

    private static UserService userService = null;
    private final ClientRepository clientRepository = ClientRepository.getInstance();
    private final AdminRepository adminRepository = AdminRepository.getInstance();
    private ArrayList<Client> clients;
    private ArrayList<Admin> admins;
    private HashMap<Integer, Client> clientMap;
    private HashMap<Integer, Admin> adminMap;

    private WriteService myWriteService = WriteService.getInstance();

    private UserService() {
        clients = new ArrayList<>();
        admins = new ArrayList<>();
        clientMap = new HashMap<>();
        adminMap = new HashMap<>();
    }

    public static UserService getInstance() {
        if (userService == null)
            userService = new UserService();
        return userService;
    }

    public void addClient() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter client name: ");
        String name = sc.nextLine();
        System.out.println("Enter client id: ");
        int id = sc.nextInt();
        System.out.println("Enter client balance: ");
        int balance = sc.nextInt();
        int discount = 1;
        Client newclient = new Client(name, id, balance, discount);

        clients.add(newclient);
        clientMap.put(id, newclient);
        System.out.println("Added: " + newclient);
        myWriteService.writeLogsToFile("Added new client");
        Client clientToSave = saveClient(name, id, balance, discount);
        System.out.println(clientToSave);
    }

    public void addAdmin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Admin name: ");
        String name = sc.nextLine();
        System.out.println("Enter Admin id: ");
        int id = sc.nextInt();
        Admin newadmin = new Admin(name, id);

        admins.add(newadmin);
        adminMap.put(id, newadmin);
        System.out.println("Added: " + newadmin);
        myWriteService.writeLogsToFile("Added new admin");
        Admin adminToSave = saveAdmin(name, id);
        System.out.println(adminToSave);
    }

    public void sortAndDisplayClients() {
        Collections.sort(clients);
        displayClients();
        myWriteService.writeLogsToFile("Sort and display clients");
    }

    public void displayClients() {
        int index = 0;
        for (User u : clients) {
            System.out.println();
            System.out.println(index + ") " + u);
            index++;
        }
        myWriteService.writeLogsToFile("Display clients");
    }

    public void displayAdmins() {
        int index = 0;
        for (User u : admins) {
            System.out.println();
            System.out.println(index + ") " + u);
            index++;
        }
        myWriteService.writeLogsToFile("Display admins");
    }

    public void displayClientInventory(int clientid) {
        Client client = clientMap.get(clientid);
        int index = 0;
        for (Device dev : client.getInventory()) {
            System.out.println(index + ") " + dev);
            index++;
        }
        myWriteService.writeLogsToFile("Display client inventory");
    }

    public void addClientToList(Client client) {
        clients.add(client);
        clientMap.put(client.getId(), client);
        myWriteService.writeLogsToFile("Add client to list");
    }

    public void addAdminToList(Admin admin) {
        admins.add(admin);
        adminMap.put(admin.getId(), admin);
        myWriteService.writeLogsToFile("Add admin to list");
    }

    public Client saveClient(String name, int id, int balance, double discount) {
        Client client = new Client(name, id, balance, discount);
        return clientRepository.saveClient(client);
    }

    public Admin saveAdmin(String name, int id) {
        Admin admin = new Admin(name, id);
        return adminRepository.saveAdmin(admin);
    }

    public Client findClient(int id) {
        return clientRepository.findClient(id);
    }

    public Admin findAdmin(int id) {
        return adminRepository.findAdmin(id);
    }

    public void updateAdmin(int id, String name) {
        adminRepository.updateAdmin(id, name);
    }

    public void updateClient(int id, String name) {
        clientRepository.updateClient(id, name);
    }


    public ArrayList<Client> getClients() {
        return clients;
    }

    public HashMap<Integer, Client> getClientMap() {
        return clientMap;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public HashMap<Integer, Admin> getAdminMap() {
        return adminMap;
    }
}
