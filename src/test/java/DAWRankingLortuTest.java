import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccessInterface;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Registered;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.dataAccess.TestDataAccess;

// POR FAVOR EJECUTA ApplicationLauncher DESPUES DE EJECUTAR LA CLASE DE DAWRankingLortuTest (como mencionado en el documento)

public class DAWRankingLortuTest {
	
	static DataAccess sut=new DataAccess();
	static TestDataAccess testDA=new TestDataAccess();
	private List<Registered> listr;
	
	@Test
	//Database consists of more than 1 registered user
	public void test1() {
		
			int result = 0;
			List<Registered> expected = testDA.getAllRegistered();
			
			
			
			List<Registered> received = sut.rankingLortu();
			
			for(Registered e : expected) {
				for(Registered r : received) {
					if(e.getUsername().equals(r.getUsername())) {
						result++;
					}
						
				}
				
			}
			
			if (received.size() == expected.size()) {
				assertEquals(received.size(), result, 0);
			}
		
	}
	@Test
	//The database do not has registered users 
	public void test2() {
			
			List<Registered> all = testDA.getAllRegistered();
			
			
			for(Registered r: all) {
			testDA.open();
			testDA.removeRegistered(r);
			testDA.close();
			}
			List<Registered> received = sut.rankingLortu();
			assertEquals(0, received.size(), 0);
	}
}