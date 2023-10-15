
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.mail.internet.AddressException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccessInterface;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Sport;
import domain.Team;
import exceptions.EventFinished;
import exceptions.EventNotFinished;
import exceptions.QuestionAlreadyExist;
import test.dataAccess.TestDataAccess;

public class gertaerakSortuDAWTest {
	

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	 private Event ev;

	
	

	@Test
	//La gertaera ya existía
	public void test1() {
		
			//Crear parametros de evento
			String description="Athletico-Athletic";		
			Date date=new Date(123,10,1); //01-11-2023
			Team t1=new Team("Athletico");
			Team t2=new Team("Athletic");
			String s="Futbol";
			
			//Crear el evento
			ev=new Event(description, date,  t1, t2);
			
			//Mirar si el deporte esta en la BD
			testDA.open();
			String sport=testDA.findSport(s); 
			testDA.close();
			 
			
			//Conseguir el resultado del metodo con el sut
			boolean result = sut.gertaerakSortu(description, date, sport );
			  assertFalse(result);

		
		   }
	@Test
	//El deporte no existe
	public void test2() {
		
		//Crear parametros de evento
		String description="Athletico-Athletic";		
		Team t1=new Team("Athletico");
		Team t2=new Team("Athletic");
		String s="Volleyball";
		Date date=new Date(123,10,1); //01-11-2023
		
		//Crear el evento
		ev=new Event(description, date,  t1, t2);
		
		//Mirar si el deporte esta en la BD (no está)
		testDA.open();
		String sport=testDA.findSport(s);
		testDA.close();
		 
	
		//Conseguir el resultado del metodo con el sut
		boolean result = sut.gertaerakSortu(description, date, sport );
		  assertFalse(result);
			
		}
	
		@Test
		//La gertaera no existe
		public void test3() {
			//Conseguir el resultado del metodo con el sut
			String description="Salamanca-Athletic";		
			Team t1=new Team("Salamanca");
			Team t2=new Team("Athletic");
			String s="Futbol";
			Date date=new Date(123,10,1); //01-11-2023
			
			//Crear el evento
			ev=new Event(description, date,  t1, t2);
			
			//Mirar si el deporte esta en la BD
			testDA.open();
			String sport=testDA.findSport(s);
			testDA.close();
			 
			//Conseguir el resultado del metodo con el sut
			boolean result = sut.gertaerakSortu(description, date, sport );
			  assertTrue(result);
		
			   }
	
}
