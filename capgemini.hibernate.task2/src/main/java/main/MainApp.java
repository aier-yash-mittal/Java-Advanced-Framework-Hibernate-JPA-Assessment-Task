package main;

import dao.ProductDAO;
import entity.Product;

import java.util.List;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ProductDAO dao = new ProductDAO();

		while (true) {

			System.out.println("\n1 Add Product");
			System.out.println("2 View All Products");
			System.out.println("3 Search Product by ID");
			System.out.println("4 Search Product by Category");
			System.out.println("5 Update Product Price");
			System.out.println("6 Delete Product");
			System.out.println("7 Exit");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:

				sc.nextLine();

				System.out.print("Name: ");
				String name = sc.nextLine();

				System.out.print("Category: ");
				String category = sc.nextLine();

				System.out.print("Price: ");
				double price = sc.nextDouble();

				System.out.print("Quantity: ");
				int quantity = sc.nextInt();

				if (name.isEmpty() || category.isEmpty() || price <= 0 || quantity <= 0) {
					System.out.println("Invalid input.");
					break;
				}

				Product p = new Product(name, category, price, quantity);

				dao.addProduct(p);

				break;

			case 2:

				List<Product> products = dao.getAllProducts();

				if (products.isEmpty())
					System.out.println("No product found.");
				else
					products.forEach(System.out::println);

				break;

			case 3:

				System.out.print("Enter ID: ");
				int id = sc.nextInt();

				Product prod = dao.getProductById(id);

				if (prod == null)
					System.out.println("No product found.");
				else
					System.out.println(prod);

				break;

			case 4:

				sc.nextLine();

				System.out.print("Enter Category: ");
				String cat = sc.nextLine();

				List<Product> list = dao.getProductsByCategory(cat);

				if (list.isEmpty())
					System.out.println("No product found.");
				else
					list.forEach(System.out::println);

				break;

			case 5:

				System.out.print("Enter ID: ");
				int pid = sc.nextInt();

				System.out.print("Enter New Price: ");
				double newPrice = sc.nextDouble();

				dao.updateProductPrice(pid, newPrice);

				break;

			case 6:

				System.out.print("Enter ID: ");
				int did = sc.nextInt();

				dao.deleteProduct(did);

				break;

			case 7:

				System.exit(0);

			}

		}

	}
}