public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        productService.updateProduct(1, "Gaming Laptop", 70000.00, 5);
    }
}