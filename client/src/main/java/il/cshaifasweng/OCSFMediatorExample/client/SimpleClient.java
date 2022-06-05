package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.FoundTable;
import il.cshaifasweng.OCSFMediatorExample.entities.Product;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

import java.util.ArrayList;
import java.util.List;

public class SimpleClient extends AbstractClient {

	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		System.out.println("handle start");

		if(msg instanceof List){
			System.out.println("arrived to msg instanceof LIST in simple client");
			List<Product> listt = (List<Product>) msg;
			//for(int i=0 ; i<5;i++) System.out.println(listt.get(i).getID());


			for(int i=0 ;i<listt.size();i++){
				System.out.println(listt.get(i).getName());
			}
			UpdateGuiEvent updateEvent = new UpdateGuiEvent(listt);
			EventBus.getDefault().post(updateEvent);

		}
		if(msg instanceof String){
			String recievedStr = (String)msg ;

				if(recievedStr.equals("not found")){
				System.out.println("didnt find a table"); // we didnt find the table , so we need to create 6 items at the beggings
				// now tell the primary controller that the table isnt found
				InitDatabaseEvent event = new InitDatabaseEvent(true);
				EventBus.getDefault().post(event);
			   }
				if(recievedStr.equals("mail not found")){
					// post an event that the mail is not found
					MailChecker mailCheckEvent = new MailChecker(false);
					EventBus.getDefault().post(mailCheckEvent);
				}
				if(recievedStr.equals("wrong password")){
					// post an event that the password is incorrect
					MailChecker mailCheckEvent = new MailChecker(true);
					mailCheckEvent.setPasswordExists(false);
					EventBus.getDefault().post(mailCheckEvent);
				}



		}
		else if(msg instanceof FoundTable){
			FoundTable ft = (FoundTable) msg ;
			List<Product> ftList = ft.getRecievedProducts();
			RetrieveDataBaseEvent retEvent = new RetrieveDataBaseEvent(ftList);
			EventBus.getDefault().post(retEvent);
		}
	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
