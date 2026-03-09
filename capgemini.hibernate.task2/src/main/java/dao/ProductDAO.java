package dao;

import entity.Product;
import util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductDAO {

	public void addProduct(Product p) {

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();

		em.persist(p);

		em.getTransaction().commit();

		em.close();

		System.out.println("Product added successfully.");
		System.out.println(p);
	}

	public List<Product> getAllProducts() {

		EntityManager em = JPAUtil.getEntityManager();

		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);

		List<Product> list = query.getResultList();

		em.close();

		return list;
	}

	public Product getProductById(int id) {

		EntityManager em = JPAUtil.getEntityManager();

		Product p = em.find(Product.class, id);

		em.close();

		return p;
	}

	public List<Product> getProductsByCategory(String category) {

		EntityManager em = JPAUtil.getEntityManager();

		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.category = :category",
				Product.class);

		query.setParameter("category", category);

		List<Product> list = query.getResultList();

		em.close();

		return list;
	}

	public void updateProductPrice(int id, double newPrice) {

		EntityManager em = JPAUtil.getEntityManager();

		Product p = em.find(Product.class, id);

		if (p != null) {

			em.getTransaction().begin();

			p.setPrice(newPrice);

			em.merge(p);

			em.getTransaction().commit();

			System.out.println("Product price updated successfully.");
			System.out.println(p);

		} else {
			System.out.println("No product found.");
		}

		em.close();
	}

	public void deleteProduct(int id) {

		EntityManager em = JPAUtil.getEntityManager();

		Product p = em.find(Product.class, id);

		if (p != null) {

			em.getTransaction().begin();

			em.remove(p);

			em.getTransaction().commit();

			System.out.println("Product deleted successfully.");

		} else {
			System.out.println("No product found.");
		}

		em.close();
	}
}