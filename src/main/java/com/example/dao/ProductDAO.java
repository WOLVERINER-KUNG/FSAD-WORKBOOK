package com.example.dao;

import com.example.entity.Product;
import com.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProductDAO {

    // Insert multiple products
    public void insertProducts(List<Product> products) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        for (Product p : products) {
            session.save(p);
        }
        session.getTransaction().commit();
        session.close();
    }

    // Get all products sorted by price ascending
    public List<Product> sortByPriceAsc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery("FROM Product p ORDER BY p.price ASC", Product.class).list();
        session.close();
        return list;
    }

    // Get all products sorted by price descending
    public List<Product> sortByPriceDesc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery("FROM Product p ORDER BY p.price DESC", Product.class).list();
        session.close();
        return list;
    }

    // Get all products sorted by quantity descending
    public List<Product> sortByQuantity() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery("FROM Product p ORDER BY p.quantity DESC", Product.class).list();
        session.close();
        return list;
    }

    // Pagination: first 3
    public List<Product> firstThreeProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery("FROM Product", Product.class)
                .setFirstResult(0)
                .setMaxResults(3)
                .list();
        session.close();
        return list;
    }

    // Pagination: next 3
    public List<Product> nextThreeProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery("FROM Product", Product.class)
                .setFirstResult(3)
                .setMaxResults(3)
                .list();
        session.close();
        return list;
    }

    // Count total products
    public Long totalCount() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long count = session.createQuery("SELECT COUNT(p) FROM Product p", Long.class).uniqueResult();
        session.close();
        return count;
    }

    // Count products where quantity > 0
    public Long countAvailableProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long count = session.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0", Long.class
        ).uniqueResult();
        session.close();
        return count;
    }

    // Find min and max price
    public Object[] minMaxPrice() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Object[] result = (Object[]) session.createQuery(
                "SELECT MIN(p.price), MAX(p.price) FROM Product p"
        ).uniqueResult();
        session.close();
        return result;
    }

    // Filter products by price range
    public List<Product> filterByPriceRange(double min, double max) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery(
                        "FROM Product p WHERE p.price BETWEEN :min AND :max", Product.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .list();
        session.close();
        return list;
    }

    // LIKE: name starts with
    public List<Product> nameStartsWith(String prefix) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery(
                        "FROM Product p WHERE p.name LIKE :prefix", Product.class)
                .setParameter("prefix", prefix + "%")
                .list();
        session.close();
        return list;
    }

    // LIKE: name ends with
    public List<Product> nameEndsWith(String suffix) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery(
                        "FROM Product p WHERE p.name LIKE :suffix", Product.class)
                .setParameter("suffix", "%" + suffix)
                .list();
        session.close();
        return list;
    }

    // LIKE: name contains
    public List<Product> nameContains(String pattern) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery(
                        "FROM Product p WHERE p.name LIKE :pattern", Product.class)
                .setParameter("pattern", "%" + pattern + "%")
                .list();
        session.close();
        return list;
    }

    // Exact length of name
    public List<Product> nameExactLength(int length) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery(
                        "FROM Product p WHERE LENGTH(p.name) = :len", Product.class)
                .setParameter("len", length)
                .list();
        session.close();
        return list;
    }

    // -------------------
    // Task 7: GROUP BY description
    // -------------------
    public List<Object[]> countProductsByDescription() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> list = session.createQuery(
                "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description", Object[].class
        ).list();
        session.close();
        return list;
    }
}
