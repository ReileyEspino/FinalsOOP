import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    // Add a new product to the database
    public void addProduct(String name, double price, int stock) {
        String query = "INSERT INTO Product (product_name, price, stock) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, stock);
            stmt.executeUpdate();
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
        }
    }

    // Retrieve all products from the database
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products: " + e.getMessage());
        }
        return products;
    }

    // Update an existing product
    public void updateProduct(int productId, String name, double price, int stock) {
        String query = "UPDATE Product SET product_name = ?, price = ?, stock = ? WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, stock);
            stmt.setInt(4, productId);
            stmt.executeUpdate();
            System.out.println("Product updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
        }
    }

    // Delete a product from the database
    public void deleteProduct(int productId) {
        String query = "DELETE FROM Product WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
            System.out.println("Product deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
    }
}
