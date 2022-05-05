package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Product;
import il.cshaifasweng.OCSFMediatorExample.entities.RemovedProduct;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
//import il.cshaifasweng.OCSFMediatorExample.server.Product;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;


public class SimpleServer extends AbstractServer {

	private static Session session;
	private static Session session1;
	private List<Product> productGeneralList = new ArrayList<Product>();

	public SimpleServer(int port) {
		super(port);

	}
	public void Saveinsess(){
		for (int i = 0; i < productGeneralList.size(); i++) {

			session.save(productGeneralList.get(i)); // save the Product in the database
			session.flush();
		}
	}
	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();

		// Add ALL of your entities here. You can also try adding a whole package.
		configuration.addAnnotatedClass(Product.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	public static void generateProducts()  {
		System.out.println("arrived to generate products function");
		Product product = new Product(5,"btn","flower1","someDetails","5000");
		System.out.println("finisehd creating the product");
		session.save(product);

		/** The call to session.flush() updates the DB immediately without ending the transaction.
		 * Recommended to do after an arbitrary unit of work.
		 * MANDATORY to do if you are saving a large amount of data - otherwise you may get
		 cache errors.*/

		session.flush();
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) throws SQLException {



		/*DatabaseMetaData databaseMetaData = null;
		ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
		System.out.println("arrived to handlemessage");
		while(resultSet.next()) {
			String tableName = resultSet.getString("TABLE_NAME");
			System.out.println("the name of the table is:" + tableName);
			//String remarks = resultSet.getString("REMARKS");
		}*/

		int flowersnum = 0;
		/*Session sessionProd = null;
		List<Object> list = sessionProd.createQuery("show tables from Database_name").list();
		System.out.println(list.get(0));*/
		if (msg instanceof ArrayList) { // arrived from the initializing of the program, so we initialize the database
			// with the starting Products
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx1 = session.beginTransaction();

        	/*DatabaseMetaData databaseMetaData = null;
		ResultSet resultSet = databaseMetaData.getTables(null, "flowers", null, new String[]{"TABLE"});
		System.out.println("arrived to handlemessage");
		while(resultSet.next()) {
			String tableName = resultSet.getString("TABLE_NAME");
			System.out.println("the name of the table is:" + tableName);
			//String remarks = resultSet.getString("REMARKS");
		}*/


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

		} else // if we arrived to the else it means we reached here from the event handler of the "Apply Changes" button
		{// so what we do is take the changes on the product and update the database

			if(msg instanceof RemovedProduct) {

				RemovedProduct recievedmsg = (RemovedProduct) msg;



				System.out.println("remove a flower");
				flowersnum--;
				String IDREMOVEDITEM = "5";
				SessionFactory sessionFactory = getSessionFactory();
				session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				//System.out.println("the recieved string id is : " + IDREMOVEDITEM);
				//System.out.println("arrived here!!0");


				try {
					System.out.println("arrived here!!1");
					int i;
					       int removedId= 0;



					try{
						removedId = Integer.valueOf(IDREMOVEDITEM);
						//System.out.println(number); // output = 25
					}
					catch (NumberFormatException ex){
						ex.printStackTrace();
					}





					System.out.println("arrived here!!2");
					for (i = 0; i < productGeneralList.size(); i++) {
						if ( productGeneralList.get(i).getID() ==removedId) {
							productGeneralList.remove(i);

						}
					}

					// we must to update the items id
					/* USE UPDATE METHOD IN THE FUTURE */
					/*for(int j=i;j<productGeneralList.size();j++)
					{

						productGeneralList.get(j).updateid();
					}*/
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
				}

			}


			Product recievedProduct = (Product) msg;
			int recievedProductID = recievedProduct.getID();
			String recievedProductName = recievedProduct.getName();
			String recievedProductDetails = recievedProduct.getDetails();
			String recievedProductButton = recievedProduct.getButton();
			String recievedProductPrice = recievedProduct.getPrice();
			String recievedProductImage = recievedProduct.getImage();

			if (recievedProductID >flowersnum) { //adding new row in the database table
				System.out.println("add a flower");
				flowersnum++;
				SessionFactory sessionFactory = getSessionFactory();
				session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				session.save(recievedProduct);
				session.flush();
				productGeneralList.add(recievedProduct);

				Saveinsess();
				tx.commit();
				session.close();
			}


			else {

				SessionFactory sessionFactory = getSessionFactory();
				session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				try {

					Product updatedProduct = new Product(recievedProductID, recievedProductButton, recievedProductName, recievedProductDetails, recievedProductPrice);


					for (int i = 0; i < productGeneralList.size(); i++) {
						if (productGeneralList.get(i).getID() == recievedProductID) {
							productGeneralList.set(i, updatedProduct);
						}
					}


					/* USE UPDATE METHOD IN THE FUTURE */
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
				}

			}

		}

	}}




