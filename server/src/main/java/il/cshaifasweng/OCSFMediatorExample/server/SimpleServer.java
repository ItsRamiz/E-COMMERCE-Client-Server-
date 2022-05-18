package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
//import il.cshaifasweng.OCSFMediatorExample.server.Product;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.PreUpdate;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class SimpleServer extends AbstractServer {

	private static Session session;
	private static Session session1;
	private List<Product> productGeneralList = new ArrayList<Product>();
	private int flowersnum = 0;

	public SimpleServer(int port) {
		super(port);

	}

	public void Saveinsess() {
		SessionFactory sessionFactory = getSessionFactory();
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for (int i = 0; i < productGeneralList.size(); i++) {

			session.save(productGeneralList.get(i)); // save the Product in the database
			session.flush();
		}
		session.close();
	}

	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();

		// Add ALL of your entities here. You can also try adding a whole package.
		configuration.addAnnotatedClass(Product.class);
		configuration.addAnnotatedClass(Account.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	public static void generateProducts() {
		System.out.println("arrived to generate products function");
		Product product = new Product(5, "btn", "flower1", "someDetails", "5000");
		System.out.println("finisehd creating the product");
		session.save(product);

		/** The call to session.flush() updates the DB immediately without ending the transaction.
		 * Recommended to do after an arbitrary unit of work.
		 * MANDATORY to do if you are saving a large amount of data - otherwise you may get
		 cache errors.*/

		session.flush();
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) throws SQLException, IOException {

		System.out.println("handleeeeeeeeeeee");
		if (msg instanceof String) {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx1 = session.beginTransaction();

			String recievedStr = (String) msg;
			if (recievedStr.equals("first entry")) { // if arrived here it means we opened the app
				System.out.println("entered first entry");
				List<String> list = session.createSQLQuery("SHOW TABLES from flowers;").list();


				System.out.println(list.get(0));
				System.out.println(list.get(1));

				//System.out.println("the number of rows of products table is:" + countRows());
				int tableFoundIndex = -1;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).equals("products_table")) {
						tableFoundIndex = i; // save the index of the table "products_table"
					}
				}
				if (tableFoundIndex != -1) { // means we found a table
					if (countRows() == 0) { // if found the table, ask if it has more than 0 rows
						System.out.println("didnt find a table (this message is from the server");
						client.sendToClient("not found");
					} else {
						List<Product> resultList = getAllProducts();

						FoundTable foundTbl = new FoundTable("found", resultList);
						client.sendToClient(foundTbl); // if found the table then tell the client, so they know they dont intialize 6 products again

					}
				} else {

					client.sendToClient("not found");
				}
			}


		}

		if (msg instanceof UpdateMessage) {

			UpdateMessage recievedMessage = (UpdateMessage) msg;
			String updateClassName = recievedMessage.getUpdateClass();
			String updateClassFunction = recievedMessage.getUpdateFunction();

			switch (updateClassName) { // checks which class to be updated
				case "product":

					if (updateClassFunction.equals("add")) {
						System.out.println("arrived to here inside add");
						Product recievedProd = recievedMessage.getProduct();
						addItemToCatalog(recievedProd);

					} else if (updateClassFunction.equals("remove")) {
						String idToRemove = recievedMessage.getDelteId();
						removeItemFromCatalog(idToRemove, client);
					}
					else if(updateClassFunction.equals("edit")){
						System.out.println("Arrived edit case in the switch !");
						Product recievedProd = recievedMessage.getProduct();
						editCatalogProduct(recievedProd);
					}


					break;


				case "account":


					break;

				case "cart":


					break;

			}
		}


		if (msg instanceof ArrayList) { // arrived from the initializing of the program, so we initialize the database
			// with the starting Products
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx1 = session.beginTransaction();


			List<Product> resultList = (List<Product>) msg;
			flowersnum = resultList.size();
			for (int i = 0; i < resultList.size(); i++) {
				//productGeneralList.set(i,resultList.get(i));
				session.save(resultList.get(i)); // save the Product in the database
				session.flush();
			}
			tx1.commit();


			for (int i = 0; i < resultList.size(); i++) {
				productGeneralList.add(resultList.get(i));

			}


			session.close();

		} else { // if we arrived to the else it means we reached here from the event handler of the "Apply Changes" button so what we do is take the changes on the product and update the database


		}

	}

	private static List<Product> getAllProducts() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		query.from(Product.class);
		List<Product> result = session.createQuery(query).getResultList();
		return result;
	}

	Long countRows() {
		final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
		Root<Product> root = criteria.from(Product.class);
		criteria.select(criteriaBuilder.count(root));
		return session.createQuery(criteria).getSingleResult();
	}


	void addItemToCatalog(Product recievedProd) {

		int recievedProductID = recievedProd.getID();
		String recievedProductName = recievedProd.getName();
		String recievedProductDetails = recievedProd.getDetails();
		String recievedProductButton = recievedProd.getButton();
		String recievedProductPrice = recievedProd.getPrice();
		String recievedProductImage = recievedProd.getImage();

		if (recievedProductID > flowersnum) { //adding new row in the database table
			System.out.println("add a flower");
			flowersnum++;

			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(recievedProd);
			session.flush();
			productGeneralList.add(recievedProd);

			for (int i = 0; i < productGeneralList.size(); i++) {

				session.save(productGeneralList.get(i)); // save the Product in the database
				session.flush();
			}
			tx.commit();
			session.close();
		}
	}

	void editCatalogProduct(Product productEdit){
		System.out.println("Arrived to edit catalog product 1");
		SessionFactory sessionFactory = getSessionFactory();
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		int recievedProductID = productEdit.getID();
		String recievedProductName = productEdit.getName();
		String recievedProductDetails = productEdit.getDetails();
		String recievedProductButton = productEdit.getButton();
		String recievedProductPrice = productEdit.getPrice();
		String recievedProductImage = productEdit.getImage();

		System.out.println("Arrived to edit catalog product 2");
		Product updateProd  = session.load(Product.class, recievedProductID);

		//System.out.println(updateProd.getButton());
		//Product updateProd = (Product) persistentInstance1 ;
		//updateProd.setID(16);
		updateProd.setButton(recievedProductButton);
		updateProd.setPrice(recievedProductPrice);
		updateProd.setName(recievedProductName);
		updateProd.setDetails(recievedProductDetails);
		updateProd.setImage(recievedProductImage);

		System.out.println("Arrived to edit catalog product 3");
		System.out.println(updateProd.getID());
		session.update(updateProd);
		System.out.println("Arrived to edit catalog product 4");
		tx.commit();
		System.out.println("Arrived to edit catalog product 5");

		/*try {

			Product updatedProduct = new Product(recievedProductID, recievedProductButton, recievedProductName, recievedProductDetails, recievedProductPrice);


			for (int i = 0; i < productGeneralList.size(); i++) {
				if (productGeneralList.get(i).getID() == recievedProductID) {
					productGeneralList.set(i, updatedProduct);
				}
			}


			*//* USE UPDATE METHOD IN THE FUTURE *//*
			Saveinsess();
			tx.commit();


		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}*/
	}

	void removeItemFromCatalog(String prodIdToRemove, ConnectionToClient _client) {

		SessionFactory sessionFactory = getSessionFactory();
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		flowersnum--;

		int removedId = Integer.parseInt(prodIdToRemove);

		try {
			productGeneralList = getAllProducts();
			System.out.println(productGeneralList.get(0).getButton());
			int i ;
			System.out.println("arrived here 1");
			for ( i = 0; i < productGeneralList.size(); i++) {
				if ( productGeneralList.get(i).getID() == removedId) {
					productGeneralList.remove(i);
					break;

				}
			}

			// we must to update the items id
			System.out.println("arrived here 2");
			for(int j=i;j<productGeneralList.size();j++) // update the ID's
			{

				productGeneralList.get(j).updateid();
			}

			System.out.println("arrived here 3");

			deleteAllProducts();

			System.out.println("arrived here 4");

			Saveinsess();
			System.out.println("arrived here 5");
			tx.commit();
			System.out.println("send to client");
			_client.sendToClient(productGeneralList);



		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}





		/*Object persistentInstance = session.load(Product.class, removedId);
		Product perProd = (Product) persistentInstance;
		System.out.println(perProd.getButton());
		if (persistentInstance != null) {
			session.delete(perProd);
		}
		tx.commit();
		session.close();
		productGeneralList = getAllProducts();


		try {

			Product updateProd  = session.load(Product.class, 4);
			System.out.println(updateProd.getButton());
			//Product updateProd = (Product) persistentInstance1 ;
			//updateProd.setID(16);
			updateProd.setButton("afkljafkjal");
			System.out.println(updateProd.getID());
			session.update(updateProd);
			tx.commit();
			//updateIdAfterRemove(removedId+1,flowersnum+1);



		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}*/




		/*try {

			int i ;
			for ( i = 0; i < productGeneralList.size(); i++) {
				if ( productGeneralList.get(i).getID() == removedId) {
					productGeneralList.remove(i);
					break;

				}
			}

			// we must to update the items id
			*//* USE UPDATE METHOD IN THE FUTURE *//*
			for(int j=i;j<productGeneralList.size();j++)
			{

				productGeneralList.get(j).updateid();
			}

			Saveinsess();
			tx.commit();
			System.out.println("send to client");
			_client.sendToClient(productGeneralList);



		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}*/



	public void updateIdAfterRemove(int start,int end){
		for(int i = start ; i<end ; i++){
			Object persistentInstance = session.load(Product.class, i);
			Product updateProd = (Product) persistentInstance ;
			updateProd.setName("Mary");
			session.update(updateProd);
		}
	}

	public void deleteProduct(int deleteIndex) {

		SessionFactory sessionFactory = getSessionFactory();
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Object persistentInstance = session.load(Product.class, deleteIndex);
		Product perProd = (Product) persistentInstance;

		if (persistentInstance != null) {
			session.delete(perProd);
		}

		tx.commit();
		session.close();
	}

	public void deleteAllProducts(){
		SessionFactory sessionFactory = getSessionFactory();
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("arrived to deleteAllProducts 1");
		long rows = countRows();
		System.out.println("arrived to deleteAllProducts 2");
		int castedRows = (int) rows ;
		System.out.println("arrived to deleteAllProducts 3");
		for(int i=1;i<=castedRows;i++){
			deleteProduct(i);
		}
		System.out.println("arrived to deleteAllProducts 4");
	}
}