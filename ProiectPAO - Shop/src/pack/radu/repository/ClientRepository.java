package pack.radu.repository;

import pack.radu.connection.*;
import pack.radu.users.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClientRepository {

    private static ClientRepository instance;

    private static final String INSERT_STATEMENT = "INSERT INTO clients (name, id, balance, discount) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM clients WHERE id = ?";
    private static final String UPDATE_STATEMENT = "UPDATE clients SET name = ? WHERE id = ?";

    private ClientRepository() {
    }

    public static ClientRepository getInstance() {
        if (instance == null) {
            instance = new ClientRepository();
        }

        return instance;
    }

    public Client saveClient(Client client) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setString(1, client.getName());
            statement.setInt(2, client.getId());
            statement.setInt(3, client.getBalance());
            statement.setDouble(4, client.getDiscount());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new client was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new client: " + e.getMessage());
        }
        return client;
    }

    public Client findClient(int id) {
        Client client = new Client();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: client was not found!");
                    return client;
                }

                System.out.println("client was found!");
                client.setId(result.getInt("id"));
                client.setName(result.getString("name"));
                client.setBalance(result.getInt("balance"));
                client.setDiscount(result.getDouble("discount"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find client: " + e.getMessage());
            return new Client();
        }
        return client;
    }

    public void updateClient(int id, String newname) {
        Client client = new Client();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setString(1, newname);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Client was updated successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update client: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to update client: Client was not found!");
        return;
    }

}