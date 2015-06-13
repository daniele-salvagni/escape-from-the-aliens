package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.messages.ResultMsgPair;
import it.polimi.ingsw.cg_2.messages.responses.InvalidRequestMsg;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This FSM manages the turns of a game (state pattern), Actions act as
 * transitions. Action validity should be checked before execution.
 */
public class TurnMachine {

    TurnState currentState;
    Game game;

    /**
     * Execute an action on this FSM, the action acts like a transition and
     * will
     * change its status.
     *
     * @param action the action to execute
     * @return a pair of public and private messages containing the result of
     * the action
     */
    public ResultMsgPair executeAction(Action action) {

        if (currentState.isActionValid(action, game)) {

            // The next state of this FSM, if null the status should not change
            // (this keeps the FSM much more simple!)
            TurnState nextState = currentState.executeAction(action, game);

            if (nextState != null) {

                currentState = nextState;

            }

            // Get the ResultMsgPair subsequently yo the execution of the action
            // (must be called only after execution!)
            return action.getResult();

        } else {

            // The action is invalid, don't broadcast any message
            return new ResultMsgPair(new InvalidRequestMsg(), null);

        }

    }

}
