package model.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.util.Util;

public class UtilTest {

	
	
	@Test
	
	public void printTest(){
		Util.print("ciao");
	}
	
	
	
	@Test
	
	public void printLineTest(){
		Util.printf("ciao",new Assistant());
	}
	
	
	@Test
	
	public void isLegitChoiceTest(){
		assertTrue(Util.isLegitChoice("3", 4));
	}

	@Test
	
	public void exceptionPrint(){
		Util.exception(new NullPointerException());
	}
	@Test
	
	public void exceptionPrint2(){
		Util.exception(new NullPointerException(),"NPE");
	}
	@Test
	
	public void isNotLegitChoiceTest(){
		assertFalse(Util.isLegitChoice("5", 4));
	}
	
	
}
