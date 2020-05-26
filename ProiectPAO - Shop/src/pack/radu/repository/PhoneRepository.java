package pack.radu.repository;

import pack.radu.connection.*;
import pack.radu.items.*;
import pack.radu.users.Admin;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PhoneRepository {

    private static PhoneRepository instance;

    private static final String INSERT_STATEMENT = "INSERT INTO phones (brand, deviceID, price, storage, ram, batterylife, cameraMP) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM phones WHERE deviceID = ?";
    private static final String UPDATE_STATEMENT = "UPDATE phones SET price = ? WHERE deviceID = ?";

    private PhoneRepository() {
    }

    public static PhoneRepository getInstance() {
        if (instance == null) {
            instance = new PhoneRepository();
        }

        return instance;
    }

    public Phone savePhone(Phone phone) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setString(1, phone.getBrand());
            statement.setInt(2, phone.getDeviceID());
            statement.setInt(3, phone.getPrice());
            statement.setInt(4, phone.getStorage());
            statement.setInt(5, phone.getRam());
            statement.setInt(6, phone.getBatterylife());
            statement.setInt(7, phone.getCameraMP());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new phone was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new phone: " + e.getMessage());
        }
        return phone;
    }

    public Phone findPhone(int id) {
        Phone phone = new Phone();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: phone was not found!");
                    return phone;
                }

                System.out.println("phone was found!");
                phone.setBrand(result.getString("brand"));
                phone.setDeviceID(result.getInt("deviceID"));
                phone.setPrice(result.getInt("price"));
                phone.setStorage(result.getInt("storage"));
                phone.setRam(result.getInt("ram"));
                phone.setBatterylife(result.getInt("batterylife"));
                phone.setCameraMP(result.getInt("cameraMP"));

            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find phone: " + e.getMessage());
            return new Phone();
        }
        return phone;
    }

    public void updatePhone(int id, int newprice) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setInt(1, newprice);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Phone was updated successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update phone: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to update phone: Phone was not found!");
        return;
    }

}