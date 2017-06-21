package model.util;

import org.junit.Test;

import it.polimi.ingsw.cg30.controller.Controller;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.util.Observable;
import it.polimi.ingsw.cg30.model.util.Observer;


public class ObservableTest {

	
	
	@Test
	
	public void testRegisterObserver(){
		Observer<Action> o= new Controller(null);
		Observable<Action> ob= new Observable<>();
		ob.registerObserver(o);
		
	}@Test
	
	public void testNotifyObserver(){
		Observer<Action> o= new Tempo();
		Observable<Action> ob= new Observable<>();
		ob.registerObserver(o);
		
	}
@Test
	
	public void testUnregisterObserver(){
		Observer<Action> o= new Controller(null);
		Observable<Action> ob= new Observable<>();
		ob.registerObserver(o);
		ob.unregisterObserver(o);
		
		
	}
	
	
	private class Tempo implements Observer<Action>
	{

		@Override
		public void update(Action change) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
}
