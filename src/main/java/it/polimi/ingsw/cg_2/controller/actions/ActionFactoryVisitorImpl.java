package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.requests.actions.*;
import it.polimi.ingsw.cg_2.model.Game;

import java.util.List;

/**
 *
 */
public class ActionFactoryVisitorImpl implements ActionFactoryVisitor {

    public ActionFactoryVisitorImpl(Game game, List<Token> clients) {

    }

    @Override
    public Action visit(AttackRequestMsg requestMsg) {

        return null;


    }

    @Override
    public Action visit(DrawRequestMsg requestMsg) {

        return null;
    }

    @Override
    public Action visit(EscapeRequestMsg requestMsg) {

        return null;
    }

    @Override
    public Action visit(MoveRequestMsg requestMsg) {

        return null;
    }

    @Override
    public Action visit(NoiseRequestMsg requestMsg) {

        return null;
    }

    @Override
    public Action visit(PassRequestMsg requestMsg) {

        return null;
    }

    @Override
    public Action visit(UseAdrRequestMsg requestMsg) {

        return null;
    }

    @Override
    public Action visit(UseAtkRequestMsg requestMsg) {

        return null;
    }

    @Override
    public Action visit(UseSdtRequestMsg requestMsg) {

        return null;
    }

    @Override
    public Action visit(UseSptRequestMsg requestMsg) {

        return null;
    }

    @Override
    public Action visit(UseTlpRequestMsg requestMsg) {

        return null;
    }
}
