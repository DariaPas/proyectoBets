package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.AbstractTableModel;

import businessLogic.BLFacade;
import gui.MainGUI;

public class UserAdapter extends AbstractTableModel {
	
	private Registered user;
	private BLFacade businessLogic = MainGUI.getBusinessLogic();
   // private List<YourDataType> data; // Replace YourDataType with the actual type of your data
	private List <ApustuAnitza> betslist = new ArrayList<ApustuAnitza>();
	private List <Question> questions = new ArrayList<>();
	private List <Event> events = new ArrayList<>();
	private String[] columnName = {"Event", "Cuestion", "Event Date", "Bet"};
	//private Vector<Apustua> apustua = new Vector<Apustua>();
	private boolean finished;
	//ERAIKITZAILEA
	public UserAdapter(Registered user) {
		this.user = user;
		getBets();
		getQuestEvents();
		// Initialize 'data' with the appropriate data from the user
	}
	
	public void getQuestEvents() {
		
		
		for(ApustuAnitza bet : betslist) {
			Vector<Apustua> apustua = bet.getApustuak();
			for(Apustua a : apustua) {
				Quote q = a.getKuota();
				Question quest = q.getQuestion();
				Event e = quest.getEvent();
				questions.add(quest);
				events.add(e);
				System.out.println("Question: "+ quest.getQuestion() + " Event: " + e.getDescription());
				System.out.println("Event date: " + e.getEventDate());
			}
			
		}
		System.out.println(events.size());
		for(Event ev: events) {
			System.out.println(ev.getDescription());
			System.out.println(ev.getEventDate());
		}
	}
	
	public void getBets() {
		
		for(ApustuAnitza ap : businessLogic.findApustuAnitza(user)){
			finished=false;
			if(ap.getEgoera().equals("jokoan")) {
				for(Apustua a: ap.getApustuak()) {
					if(new Date().compareTo(businessLogic.findEventFromApustua(a).getEventDate())>=0) {
						finished=true;
					}
				}	
				if(!finished) {
					betslist.add(ap);
				}
			}
		}
	}
	public int getColumnCount() {
		return columnName.length; 
	}
	public String getColumnName(int col) {
		return columnName[col];
	}
	public int getRowCount() { 
		return betslist.size();
		
	}
	public Object getValueAt(int row, int col) { 
		
		//YourDataType rowData = data.get(rowIndex);
        
        // Provide data for each column
	
		System.out.println(col);
		
        switch (col) {
            case 0:
            	return events.get(row).getDescription();
            case 1:
                return questions.get(row).getQuestion();
            case 2:
            	return events.get(row).getEventDate();
            case 3:
            	return betslist.get(row).getBalioa();
            default:
            	return null;
        }
		
	}

 };