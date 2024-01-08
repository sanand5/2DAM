## Errores
![Alt text](image.png)

```java
package practica_06.gestor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // Replace these values with your actual database information
        String url = "jdbc:mysql://your_database_url:3306/your_database_name";
        String user = "your_username";
        String password = "your_password";

        // Create an instance of Conexion
        Conexion conexion = new Conexion(url, user, password);

        try {
            // Get a connection to the database
            Connection connection = conexion.getConnection();

            // Check if the connection is successful
            if (connection != null) {
                System.out.println("Connected to the database!");

                // Execute an INSERT query (replace this query with your own)
                String insertQuery = "INSERT INTO example_table (name) VALUES (?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    // Set parameter values for the INSERT query
                    insertStatement.setString(1, "John Doe"); // Replace "John Doe" with the actual name you want to insert

                    // Execute the INSERT query
                    int rowsAffected = insertStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Insert successful. Rows affected: " + rowsAffected);
                    } else {
                        System.out.println("Insert failed.");
                    }
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection when done
            conexion.closeConnection();
        }
    }
}

```