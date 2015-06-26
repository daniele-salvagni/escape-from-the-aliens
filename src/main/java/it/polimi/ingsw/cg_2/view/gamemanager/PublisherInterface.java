package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;

import java.util.Set;

/**
 * This is the interface for a publisher component that
 */
public interface PublisherInterface {

    /**
     * Publish a message to all the clients subscribed to a specific topic.
     *
     * @param message the message to publish
     * @param topic   the topic of the message
     */
    void publish(BroadcastMsg message, String topic);

    /**
     * Add a new topic to the publisher component.
     *
     * @param topic the name of the new topic
     * @throws IllegalArgumentException if the topic did already exist
     */
    void addTopic(String topic);

    /**
     * Ass a new topic to the publisher topic and automatically subscribe a set of
     * clients to it.
     *
     * @param topic   the name of the new topic
     * @param clients the clients to automatically subscribe to the topic
     * @throws IllegalArgumentException if the topic did already exist or clients are
     *                                  invalid
     */
    void addTopic(String topic, Set<Token> clients);

    /**
     * Subscribe a client to a specific topics.
     *
     * @param topic  the name of the topic
     * @param client the client to subscribe
     */
    void subscribeClientToTopic(String topic, Token client);

    /**
     * Subscribe a set of clients to a specific topic.
     *
     * @param topic   the name of the topic
     * @param clients the clients to subscribe
     */
    void subscribeClientsToTopic(String topic, Set<Token> clients);

    /**
     * Unsubscribe a client from a specific topic.
     *
     * @param topic  the name of the topic
     * @param client the client to unsubscribe
     */
    void unsubscribeClientFromTopic(String topic, Token client);

    /**
     * Remove a client from the publisher component and unsubscribe him from all the
     * topics.
     *
     * @param client the client to remove
     */
    void removeClient(Token client);

    /**
     * Remove a set of client to the publisher component and unsubscribe them from all
     * the topics.
     *
     * @param clients the clients to remove
     */
    void removeClients(Set<Token> clients);

    /**
     * Remove a topic from the publisher component.
     *
     * @param topic the topic to remove
     * @throws IllegalArgumentException if the component did not exist
     */
    void removeTopic(String topic);

}
