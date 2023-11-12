package configuration;

import java.util.Vector;

import domain.Event;


public class ExtendedIteratorEvents implements ExtendedIterator<Event> {

	private Vector<Event> events;
	private int position;
	
	public ExtendedIteratorEvents(Vector<Event> events) {
		this.events=events;
		this.position = events.size() - 1;
	}
	@Override
	public boolean hasNext() {
		return position<events.size()-1;
	}

	@Override
	public Event next() {
		if(hasNext()) {
		Event e=events.get(position);
		position++;
		return e;
		}
		return null;
		
	}

	@Override
	public Event previous() {
		if (hasPrevious()) {
            return events.get(position--);
        } else {
            return null; // Or handle the case when there are no more elements
        }
	}

	@Override
	public boolean hasPrevious() {
		return position >= 0;
	}

	@Override
	public void goFirst() {
		position=0;
		
	}

	@Override
	public void goLast() {
		position = events.size() - 1;	
	}

}
