package businessLogic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import gui.MainGUI;
import gui.MainUserGUI;

public class BLFactory {

	BLFacade appFacadeInterface;
	
	public BLFacade getBusinessLogicFactory(int local) {
		
		ConfigXML c=ConfigXML.getInstance();
		if(local==1) {
			DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			appFacadeInterface=new BLFacadeImplementation(da);
			

		}else { //If remote
					
			 String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
			 
				//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
				URL url;
				try {
					url = new URL(serviceName);
				

		 
		        //1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
//		        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
		        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
		 
		        Service service = Service.create(url, qname);
		        

		         appFacadeInterface = service.getPort(BLFacade.class);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return appFacadeInterface;
	}
}
