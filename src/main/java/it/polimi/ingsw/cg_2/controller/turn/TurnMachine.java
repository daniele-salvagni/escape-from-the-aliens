package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.messages.ResultMsgPair;
import it.polimi.ingsw.cg_2.messages.responses.InvalidRequestMsg;
import it.polimi.ingsw.cg_2.model.Game;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This FSM manages the turns of a game (state pattern), Actions act as
 * transitions. Action validity should be checked before execution.
 */
public class TurnMachine {

    private static final Logger LOG = Logger.getLogger(TurnMachine.class
            .getName());

    private TurnState state;
    private final Game game;

    /**
     * Create a new TurnMachine that manages a certain Game.
     *
     * @param game the Game managed by this machine
     */
    public TurnMachine(Game game) {

        this.game = game;
        state = TurnStartedState.getInstance();

    }

    /**
     * Execute an action on this FSM, the action acts like a transition and
     * will change its status.
     *
     * @param action the action to execute
     * @return a pair of public and private messages containing the result of
     * the action
     */
    public ResultMsgPair executeAction(Action action) {

        if (state.isActionValid(action, game)) {

            // The next state of this FSM, if null the status should not change
            // (this keeps the FSM much more simple!)
            TurnState nextState = state.executeAction(action, game);

            if (nextState != null) {

                setState(nextState);
                LOG.log(Level.INFO, "State changed to: " + state.getClass()
                        .getSimpleName());


            }

            // Get the ResultMsgPair subsequently yo the execution of the action
            // (must be called only after execution!)
            return action.getResult();

        } else {

            // The action is NOT valid, inform the client and don't broadcast
            // any message. We inform that the problem is with the game rules
            // (invalid sequence of actions or action parameters not allowed).
            return new ResultMsgPair(new InvalidRequestMsg("Request against game rules" +
                    "."), null);

        }

    }

    /**
     * Get the current state of the State machine.
     *
     * @return the current state
     */
    public TurnState getState() {

        return state;

    }

    /**
     * Change the current state of the State machine.
     *
     * @param state the new state
     */
    public void setState(TurnState state) {

        this.state = state;

    }

}
