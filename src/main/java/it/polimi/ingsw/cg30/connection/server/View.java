package it.polimi.ingsw.cg30.connection.server;

import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.change.Change;
import it.polimi.ingsw.cg30.model.util.Observable;
import it.polimi.ingsw.cg30.model.util.Observer;

public abstract class View extends Observable<Action> implements Observer<Change>, MessageInterface {

}