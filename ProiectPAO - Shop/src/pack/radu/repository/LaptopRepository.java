package pack.radu.repository;

import pack.radu.connection.*;
import pack.radu.items.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LaptopRepository {

    private static LaptopRepository instance;

    private static final String INSERT_STATEMENT = "INSERT INTO laptops (brand, deviceID, price, storage, ram, storagetype, gputype) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM laptops WHERE deviceID = ?";
    private static final String UPDATE_STATEMENT = "UPDATE laptops SET price = ? WHERE deviceID = ?";

    private LaptopRepository() {
    }

    public static LaptopRepository getInstance() {
        if (instance == null) {
            instance = new LaptopRepository();
        }

        return instance;
    }

    public Laptop saveLaptop(Laptop laptop) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setString(1, laptop.getBrand());
            statement.setInt(2, laptop.getDeviceID());
            statement.setInt(3, laptop.getPrice());
            statement.setInt(4, laptop.getStorage());
            statement.setInt(5, laptop.getRam());
            statement.setString(6, laptop.getStoragetype());
            statement.setString(7, laptop.getGputype());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new laptop was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new laptop: " + e.getMessage());
        }
        return laptop;
    }

    public Laptop findLaptop(int id) {
        Laptop laptop = new Laptop();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: laptop was not found!");
                    return laptop;
                }

                System.out.println("laptop was found!");
                laptop.setBrand(result.getString("brand"));
                laptop.setDeviceID(result.getInt("deviceID"));
                laptop.setPrice(result.getInt("price"));
                laptop.setStorage(result.getInt("storage"));
                laptop.setRam(result.getInt("ram"));
                laptop.setStoragetype(result.getString("storagetype"));
                laptop.setGputype(result.getString("gputype"));

            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find laptop: " + e.getMessage());
            return new Laptop();
        }
        return laptop;
    }

    public void updateLaptop(int id, int newprice) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setInt(1, newprice);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Laptop was updated successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update laptop: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to update laptop: Laptop was not found!");
        return;
    }

}