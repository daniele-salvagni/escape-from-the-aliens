package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.messages.requests.actions.*;

/**
 * This factory uses the visitor pattern to create an appropriate {@link
 * Action} from an
 * {@link it.polimi.ingsw.cg_2.messages.requests.actions.ActionRequestMsg}
 * without knowing the concrete type of the message object.
 */
public interface ActionFactoryVisitor {

    /**
     * Visit an AttackRequestMsg to create an appropriate action.
     *
     * @param requestMsg the request message
     * @return the corresponding action
     */
    Action visit(AttackRequestMsg requestMsg);

    /**
     * Visit a DrawRequestMsg to create an appropriate action.
     *
     * @param requestMsg the request message
     * @return the corresponding action
     */
    Action visit(DrawRequestMsg requestMsg);

    /**
     * Visit an EscapeRequestMsg to create an appropriate action.
     *
     * @param requestMsg the request message
     * @return the corresponding action
     */
    Action visit(EscapeRequestMsg requestMsg);

    /**
     * Visit a MoveRequestMsg to create an appropriate action.
     *
     * @param requestMsg the request message
     * @return the corresponding action
     */
    Action visit(MoveRequestMsg requestMsg);

    /**
     * Visit a NoiseRequestMsg to create an appropriate action.
     *
     * @param requestMsg the request message
     * @return the corresponding action
     */
    Action visit(NoiseRequestMsg requestMsg);

    /**
     * Visit a PassRequestMsg to create an appropriate action.
     *
     * @param requestMsg the request message
     * @return the corresponding action
     */
    Action visit(PassRequestMsg requestMsg);

    /**
     * Visit a UseAdrRequestMsg to create an appropriate action.
     *
     * @param requestMsg the request message
     * @return the corresponding action
     */
    Action visit(UseAdrRequestMsg requestMsg);

    /**
     * Visit a UseAtkRequestMsg to create an appropriate action.
     *
     * @param requestMsg the request message
     * @return the corresponding action
     */
    Action visit(UseAtkRequestMsg requestMsg);

    /**
     * Visit a UseSdtRequestMsg to create an appropriate action.
     *
     * @param requestMsg the request message
     * @return the corresponding action
     */
    Action visit(UseSdtRequestMsg requestMsg);

    /**
     * Visit a UseSptRequestMsg to create an appropriate action.
     *
     * @param requestMsg the request message
     * @return the corresponding action
     */
    Action visit(UseSptRequestMsg requestMsg);

    /**
     * Visit a UseTlpRequestMsg to create an appropriate action.
     *
     * @param requestMsg the request message
     * @return the corresponding action
     */
    Action visit(UseTlpRequestMsg requestMsg);

}
