package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.controller.turn.DrawnDeceptionAndItemState;
import it.polimi.ingsw.cg_2.controller.turn.TurnMachine;
import it.polimi.ingsw.cg_2.controller.turn.TurnState;
import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.requests.actions.*;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.player.Player;
import it.polimi.ingsw.cg_2.utils.exception.InvalidMsgException;

import java.util.ArrayList;
import java.util.List;

/**
 * This Action factory uses a visitor pattern to create actions from an ActionRequest
 * message without having to check with "instanceof" the concrete type of the object.
 */
public class ActionFactoryVisitorImpl implements ActionFactoryVisitor {

    private final Game game;
    private final List<Token> clients;
    private final TurnMachine turnMachine;

    /**
     * Create a new ActionFactoryVisitorImpl.
     *
     * @param game        the game where to execute actions
     * @param clients     the ordered list of clients (same order as game players)
     * @param turnMachine the turn machine that manages the turns of this game
     */
    public ActionFactoryVisitorImpl(Game game, List<Token> clients, TurnMachine
            turnMachine) {

        this.game = game;
        this.clients = new ArrayList<>(clients);
        this.turnMachine = turnMachine;

    }

    /**
     * Get a player of the game model from the client private token.
     *
     * @param token the client private token
     * @return the corresponding player of the game model
     */
    private Player getPlayerByToken(Token token) {

        if (!clients.contains(token)) {
            throw new InvalidMsgException("Invalid Token");
        }

        int playerNumber = clients.indexOf(token);

        return game.getPlayers().get(playerNumber);

    }

    /**
     * Get the Zone sector from a message string encoded like COL:ROW
     *
     * @param coordStr the coordinate in string format
     * @return the corresponding sector
     */
    private Sector getSectorFromString(String coordStr) {

        // The format s ensured by the message constructor
        String split[] = coordStr.split(":");

        CubicCoordinate coord = CubicCoordinate.createFromOddQ(Integer.parseInt
                (split[0]), Integer.parseInt(split[1]));

        Sector sector = game.getZone().getSector(coord);

        if (sector == null) {
            throw new InvalidMsgException("Sector does not exist.");
        }

        return sector;

    }

    @Override
    public Action visit(AttackRequestMsg requestMsg) {

        return new AttackAction(game, getPlayerByToken(requestMsg.getToken()));

    }

    @Override
    public Action visit(DrawRequestMsg requestMsg) {

        return new DrawAction(game, getPlayerByToken(requestMsg.getToken()));

    }

    @Override
    public Action visit(EscapeRequestMsg requestMsg) {

        return new EscapeAction(game, getPlayerByToken(requestMsg.getToken()));

    }

    @Override
    public Action visit(MoveRequestMsg requestMsg) {

        String coordStr = requestMsg.getCoordinate();
        String split[] = coordStr.split(":");

        CubicCoordinate coord = CubicCoordinate.createFromOddQ(Integer.parseInt
                (split[0]), Integer.parseInt(split[1]));

        Sector sector = game.getZone().getSector(coord);

        if (sector == null) {
            throw new InvalidMsgException("Sector does not exist.");
        }

        return new MoveAction(game, getPlayerByToken(requestMsg.getToken()),
                getSectorFromString(requestMsg.getCoordinate()));

    }

    @Override
    public Action visit(NoiseRequestMsg requestMsg) {

        boolean item = (turnMachine.getState() == DrawnDeceptionAndItemState
                .getInstance());

        return new NoiseAction(game, getPlayerByToken(requestMsg.getToken()),
                getSectorFromString(requestMsg.getCoordinate()), item);

    }

    @Override
    public Action visit(PassRequestMsg requestMsg) {

        return new PassAction(game, getPlayerByToken(requestMsg.getToken()));

    }

    @Override
    public Action visit(UseAdrRequestMsg requestMsg) {

        return new UseAdrItemAction(game, getPlayerByToken(requestMsg.getToken()));

    }

    @Override
    public Action visit(UseAtkRequestMsg requestMsg) {

        return new UseAtkItemAction(game, getPlayerByToken(requestMsg.getToken()));

    }

    @Override
    public Action visit(UseSdtRequestMsg requestMsg) {

        return new UseSdtItemAction(game, getPlayerByToken(requestMsg.getToken()));

    }

    @Override
    public Action visit(UseSptRequestMsg requestMsg) {

        return new UseSptItemAction(game, getPlayerByToken(requestMsg.getToken()),
                getSectorFromString(requestMsg.getCoordinate()));

    }

    @Override
    public Action visit(UseTlpRequestMsg requestMsg) {

        return new UseTlpItemAction(game, getPlayerByToken(requestMsg.getToken()));

    }

}
