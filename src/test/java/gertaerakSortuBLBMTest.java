import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.util.Date;

import javax.jws.WebMethod;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import dataAccess.DataAccessInterface;
import domain.Event;
import domain.Team;
import exceptions.EventFinished;

public class gertaerakSortuBLBMTest {

	DataAccess dao= Mockito.mock(DataAccess.class);
	BLFacadeImplementation sut = new BLFacadeImplementation(dao);
	
	@SuppressWarnings("deprecation")
	@Test //La fecha ha pasado
	public void test0(){
			
		//Crear parametros de gertaerakSortu
		String desc= "Athletico-Athletic";
		Date d= new Date(122, 10,1);
		String sport="Futbol";
		
		//Configurar mock
		Mockito.doReturn(false).when(dao).gertaerakSortu(desc, d, sport);
		
		//Resultado del test(verificar que sale una excepción)
		assertThrows(EventFinished.class, ()->  
		sut.gertaerakSortu(desc, d, sport));
						
	}


	@SuppressWarnings("deprecation")
	@Test //La fecha esta bien
	public void test1(){
		try {		
			
		String desc= "Athletico-Athletic";
		Date d= new Date(123, 10,1);
		String sport="Futbol";
		
		
		Mockito.doReturn(false).when(dao).gertaerakSortu(desc,d,sport);
		
			boolean result = sut.gertaerakSortu(desc, d, sport);
			assertFalse(result);
		} catch (EventFinished e) {
			e.printStackTrace();
		}
		
	}

}


