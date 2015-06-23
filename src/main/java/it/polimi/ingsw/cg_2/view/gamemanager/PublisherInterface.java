package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;

import java.util.Set;

/**
 *
 */
public interface PublisherInterface {

    void publish(BroadcastMsg message, String topic);

    void addTopic(String topic);

    void addTopic(String topic, Set<Token> clients);

    void subscribeClientToTopic(String topic, Token client);

    void subscribeClientsToTopic(String topic, Set<Token> clients);

    void removeClient(Token client);

    void removeClients(Set<Token> clients);

    void removeTopic(String topic);

}
