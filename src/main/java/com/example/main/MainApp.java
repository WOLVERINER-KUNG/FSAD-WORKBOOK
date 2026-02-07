package com.example.main;

import com.example.dao.ProductDAO;
import com.example.entity.Product;
import java.util.Arrays;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        // Insert sample products (run once)
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 60000, 10),
                new Product("Mouse", "Electronics", 500, 50),
                new Product("Keyboard", "Electronics", 1500, 30),
                new Product("Chair", "Furniture", 4000, 15),
                new Product("Table", "Furniture", 8000, 5),
                new Product("Pen", "Stationery", 20, 100),
                new Product("Notebook", "Stationery", 60, 0)
        );

        // Insert into DB
        dao.insertProducts(products);

        // Sorting
        System.out.println("Price Ascending:");
        dao.sortByPriceAsc().forEach(System.out::println);

        System.out.println("Price Descending:");
        dao.sortByPriceDesc().forEach(System.out::println);

        System.out.println("Quantity Descending:");
        dao.sortByQuantity().forEach(System.out::println);

        // Pagination
        System.out.println("First 3 products:");
        dao.firstThreeProducts().forEach(System.out::println);

        System.out.println("Next 3 products:");
        dao.nextThreeProducts().forEach(System.out::println);

        // Aggregates
        System.out.println("Total products: " + dao.totalCount());
        System.out.println("Available products: " + dao.countAvailableProducts());

        // Min/Max price
        Object[] minMax = dao.minMaxPrice();
        System.out.println("Min price: " + minMax[0] + ", Max price: " + minMax[1]);

        // Filtering by price range
        System.out.println("Products priced 1000-10000:");
        dao.filterByPriceRange(1000, 10000).forEach(System.out::println);

        // LIKE queries
        System.out.println("Names start with 'L':");
        dao.nameStartsWith("L").forEach(System.out::println);

        System.out.println("Names end with 'e':");
        dao.nameEndsWith("e").forEach(System.out::println);

        System.out.println("Names contain 'top':");
        dao.nameContains("top").forEach(System.out::println);

        System.out.println("Names with exact length 5:");
        dao.nameExactLength(5).forEach(System.out::println);

        // --------------------------
        // Task 7: GROUP BY description
        // --------------------------
        System.out.println("Count of products by description:");
        List<Object[]> grouped = dao.countProductsByDescription();
        for (Object[] row : grouped) {
            System.out.println("Description: " + row[0] + ", Count: " + row[1]);
        }

        // Shutdown Hibernate
        com.example.util.HibernateUtil.shutdown();
    }
}
