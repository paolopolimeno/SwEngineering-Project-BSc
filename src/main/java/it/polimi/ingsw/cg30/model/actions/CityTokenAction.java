package it.polimi.ingsw.cg30.model.actions;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg30.model.City;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.NobilityBonus;
import it.polimi.ingsw.cg30.model.util.Util;

public class CityTokenAction extends BonusAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3665297512846612761L;
	private int choiceLength;
	private List<String> choice;
	private List<City> selectedCities=new ArrayList<>();
	/**
	 * constructor
	 * @param choiceLength
	 * @param choice
	 */
	public CityTokenAction(int choiceLength,List<String> choice) {
		this.choiceLength=choiceLength;
		this.choice=choice;
	}
	/** gives player bonuses from selected cities
	 * calls removeBonusAction
	 * @return false if:choice length doesn't match with server one||
	 * choice contains duplicate|| one of choice not legit|| player
	 * has not built on that city|| city has a nobility bonus 
	 */
	@Override
	public Boolean actionPerformed() {
			
			CityTokenAction playerAction=(CityTokenAction)(getPlayer().getPossibleActions().get(0));
			int numberAction= playerAction.getChoiceLength();
			
			if(choiceLength!=numberAction) {
				return false;
			}
			if(choiceLength!=choice.size()){
				return false;
			}
			if(Util.containsDuplicate(choice)){
				return false;
			}
			
			for (int i = 0; i <choiceLength ; i++) {
				
				City selectedCity;
				if (!isLegitChoice(choice.get(i), getPlayer().getGame().getGameBoard().getCities().size())){
					return false;
				}
				selectedCity=getPlayer().getGame().getGameBoard().getCities().get(Integer.parseInt(choice.get(i)) - 1);
				
				if ((selectedCity).canBuildEmporium(getPlayer())){
					return false;
				}
				else if (containsNobilityBonus(selectedCity.getCityBonuses())){
					return false;
				}
				else selectedCities.add(selectedCity);
			}
			for(City city: selectedCities){
				
				for(Bonus bonus: city.getCityBonuses()){
					bonus.giveBonusToPlayer(getPlayer());
				}
			}
			removeBonusAction(this);
			return true;
	}
	/**
	 * 
	 * @return choiceLength
	 */
	public int getChoiceLength() {
		return choiceLength;
	}
	/**
	 * check if list contains nobility bonus
	 * @param list
	 * @return
	 */
	public static boolean containsNobilityBonus(List<Bonus> list){
	
		for(Bonus bonus: list){
			if (bonus.getClass()==NobilityBonus.class) {
				return  true;
			}
		}
		return false;
	}
}