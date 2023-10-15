import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Registered;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class RankingLortuBLBMTest {
	
     DataAccess dao=Mockito.mock(DataAccess.class);
     Event mockedEvent=Mockito.mock(Event.class);
     Registered mockReg=Mockito.mock(Registered.class);
     
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dao);
	
	@Test
	public void test1() {
			
		
				List<Registered> listR = new ArrayList<Registered>();
				
				String name1 = "admin";
				Registered ad1=new Registered("admin", "123", 1234,true);
			
				String name2 = "registered";
				Registered reg1 =new Registered("registered", "123", 1234);
			
				String name3 = "andrea";
				Registered reg2 = new Registered("andrea", "123", 1111);
				
				String name4 = "markel";
				Registered reg3 = new Registered("markel", "123", 1111);
				
				String name5 = "mikel";
				Registered reg4 = new Registered("mikel", "123", 1111);
				
				listR.add(reg1);
				listR.add(reg4);
				listR.add(reg3);
				listR.add(reg2);
					
				Mockito.doReturn(listR).when(dao).rankingLortu();
					
				List<Registered> r = sut.rankingLortu();
				assertEquals(r, listR);
					
			}
				
	@Test
	public void test2() {
		
			List<Registered> listR = new ArrayList<Registered>();
			Mockito.doReturn(listR).when(dao).rankingLortu();
					
			List<Registered> r = sut.rankingLortu();
			assertEquals(r, listR);
					
				}
}