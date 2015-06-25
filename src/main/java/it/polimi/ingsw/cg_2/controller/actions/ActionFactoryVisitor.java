package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.messages.requests.actions.*;

/**
 * This factory uses the visitor pattern to create an appropriate {@link
 * Action} from an
 * {@link it.polimi.ingsw.cg_2.messages.requests.actions.ActionRequestMsg}
 * without knowing the concrete type of the message object.
 */
public interface ActionFactoryVisitor {

    Action visit(AttackRequestMsg requestMsg);

    Action visit(DrawRequestMsg requestMsg);

    Action visit(EscapeRequestMsg requestMsg);

    Action visit(MoveRequestMsg requestMsg);

    Action visit(NoiseRequestMsg requestMsg);

    Action visit(PassRequestMsg requestMsg);

    Action visit(UseAdrRequestMsg requestMsg);

    Action visit(UseAtkRequestMsg requestMsg);

    Action visit(UseSdtRequestMsg requestMsg);

    Action visit(UseSptRequestMsg requestMsg);

    Action visit(UseTlpRequestMsg requestMsg);

}
