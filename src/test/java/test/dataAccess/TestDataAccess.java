package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Event;
import domain.Question;
import domain.Registered;
import domain.Sport;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}


		
	


		public String findSport(String s) {
			Sport spo =db.find(Sport.class, s);
			//Sport sp=new Sport("Futbol");
			if(spo!=null) {
				return spo.toString();
			}else
				return s;
			
		}

		public boolean removeRegistered(Registered reg) {
			System.out.println(">> DataAccessTest: removeRegistered");
			Registered r = db.find(Registered.class, reg.getUsername());
			if (r!=null) {
				db.getTransaction().begin();
				db.remove(r);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
		}
		
		public List<Registered> getAllRegistered() {
			TypedQuery<Registered> Rquery = db.createQuery("SELECT r FROM Registered r", Registered.class);
			List<Registered> listR = Rquery.getResultList();
			return listR;
		}

		
		
}

