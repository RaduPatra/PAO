package pack.radu.repository;

import pack.radu.connection.*;
import pack.radu.users.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {

    private static AdminRepository instance;

    private static final String INSERT_STATEMENT = "INSERT INTO admins (name, id) VALUES (?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM admins WHERE id = ?";
    private static final String UPDATE_STATEMENT = "UPDATE admins SET name = ? WHERE id = ?";

    private AdminRepository() {
    }

    public static AdminRepository getInstance() {
        if (instance == null) {
            instance = new AdminRepository();
        }

        return instance;
    }

    public Admin saveAdmin(Admin admin) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setString(1, admin.getName());
            statement.setInt(2, admin.getId());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new admin was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new admin: " + e.getMessage());
            return new Admin();
        }
        return admin;
    }

    public Admin findAdmin(int id) {
        Admin admin = new Admin();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: admin was not found!");
                    return admin;
                }

                System.out.println("admin was found!");
                admin.setId(result.getInt("id"));
                admin.setName(result.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find admin: " + e.getMessage());
            return new Admin();
        }
        return admin;
    }

    public void updateAdmin(int id, String newname) {
        Admin admin = new Admin();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setString(1, newname);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Admin was updated successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update admin: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to update admin: Admin was not found!");
        return;
    }


}