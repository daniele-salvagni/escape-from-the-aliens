package it.polimi.ingsw.cg_2.controller;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.controller.turn.TurnMachine;
import it.polimi.ingsw.cg_2.messages.ResultMsgPair;
import it.polimi.ingsw.cg_2.messages.requests.actions.ActionRequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.InvalidRequestMsg;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.utils.exception.InvalidMsgException;

/**
 *
 */
public class GameController {

    Game game;
    TurnMachine turnMachine;

    ActionFactoryVisitor actionFactory;

    /**
     * Handle a request from the client, a Message is passed to a Factory that
     * uses the Visitor pattern to create an appropriate atomic Action (command
     * pattern), then the action is passed to a FSM (state pattern) to be
     * executed.
     *
     * @param request the request message from the client
     * @return the result of the execution of the message (response & broadcast)
     */
    public ResultMsgPair handleRequest(ActionRequestMsg request) {

        Action action;

        try {

            // Create the appropriate action using the Visitor Pattern
            action = request.createAction(actionFactory);

            // Execute the action and return a result message pair (private
            // response + public broadcast)
            return turnMachine.executeAction(action);

        } catch (InvalidMsgException e) {

            // Notify the client that the message is not valid
            return new ResultMsgPair(new InvalidRequestMsg("INVALID"), null);

        }


    }

}
