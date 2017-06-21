package model.util;

import org.junit.Test;

import it.polimi.ingsw.cg30.model.util.Settings;

public class SettingsTest {

	@Test
	
	public void testSetBonusAmountIron(){
		
		Settings s= new Settings();
		s.setIronBonusAmount(1);
		
	}
	
	@Test

	public void testSetBonusAmountGold(){
		Settings s= new Settings();
		s.setGoldBonusAmount(1);
	}

	@Test

	public void testSetBonusAmountSilver(){
		Settings s= new Settings();
		s.setSilverBonusAmount(1);
	}
	@Test

	public void testSetBonusAmountBronze(){
		Settings s= new Settings();
		s.setBronzeBonusAmount(1);
	}
	@Test

	public void testSetBonusAmountRegion(){
		Settings s= new Settings();
		s.setRegionBonusAmount(1);
	}
	
	@Test

	public void testSetFirstKingBonusAmount(){
	
		Settings s= new Settings();
		s.setFirstKingBonusAmount(1);
		
	}
	
	@Test

	public void testSetSecondKingBonusAmount(){
	
		Settings s= new Settings();
		s.setSecondKingBonusAmount(1);
		
	}
	
	
	@Test

	public void testSetThirdKingBonusAmount(){
	
		Settings s= new Settings();
		s.setThirdKingBonusAmount(1);
		
	}
	@Test

	public void testSetFourthKingBonusAmount(){
	
		Settings s= new Settings();
		s.setFourthKingBonusAmount(1);
		
	}
	
	@Test

	public void testSetFifthKingBonusAmount(){
	
		Settings s= new Settings();
		s.setFifthKingBonusAmount(1);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
