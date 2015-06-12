package it.polimi.ingsw.cg_2.controller.actions;

public interface ActionCreator {

    Action createAction(ActionFactoryVisitor visitor);

}
