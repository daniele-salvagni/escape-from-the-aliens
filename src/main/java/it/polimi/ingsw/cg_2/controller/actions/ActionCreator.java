package it.polimi.ingsw.cg_2.controller.actions;

/**
 * This interface must be implemented by every request message that involves
 * creating a new action to be performed on the game on the server side. This
 * allows an action factory to create the appropriate action from a message
 * without knowing the exact type of the message by using a visitor pattern.
 */
public interface ActionCreator {

    /**
     * This method is needed for the action factory to create the correct
     * corresponding action to a certain message by using a visitor pattern. It
     * must be implemented by each subclass to return themselves.
     *
     * @param visitor the visitor class, a Factory of Actions
     * @return the server action relative to the message
     */
    Action createAction(ActionFactoryVisitor visitor);

}
