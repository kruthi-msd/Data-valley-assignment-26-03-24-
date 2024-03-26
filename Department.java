import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentStore {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/departments";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public void addDepartment(Department department) throws SQLException {
        String SQL = "INSERT INTO department(id, name) VALUES(?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setLong(1, department.getId());
            preparedStatement.setString(2, department.getName());

            preparedStatement.executeUpdate();

        }
    }

    public static void main(String[] args) {
        DepartmentStore departmentStore = new DepartmentStore();

        try {
            Department department = new Department(1, "Sales");
            departmentStore.addDepartment(department);

            System.out.println("Department added successfully!");

        } catch (SQLException e) {
            System.err.println("Error adding department: " + e.getMessage());
        }
    }

    public static class Department {
        private long id;
        private String name;

        public Department(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
