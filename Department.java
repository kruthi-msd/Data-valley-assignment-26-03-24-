import java.sql.*;

public class Database{

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/departments";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "password";

    public static void main(String[] args) {
        Department department = new Department(1, "Human Resources");
        saveDepartment(department);
    }

    public static void saveDepartment(Department department) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

          
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);

          
            statement = connection.createStatement();

      
            String query = "INSERT INTO department (id, name) VALUES (" 
                + department.getId() + ", '" + department.getName() + "')";

      
            statement.executeUpdate(query);

            System.out.println("Department saved successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            
        } catch (SQLException e) {
            System.out.println("Error connecting to the database!);
        } finally {
    
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
            
            }
        }
    }
}

class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
