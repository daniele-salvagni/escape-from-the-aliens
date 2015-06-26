package it.polimi.ingsw.cg_2.view.gamemanager;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;
import it.polimi.ingsw.cg_2.view.commons.SubscriberInterface;

import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This broker manages the client subscriptions to topics. Trough the PublisherInterface
 * it is possible to manage topics and publish messages, the BrokerInterface is used to
 * allow the clients to remotely register to the broker.
 */
public class Broker implements BrokerInterface, PublisherInterface {

    private static final Logger LOG = Logger.getLogger(Broker.class.getName());

    private final Set<String> topics;
    private final Multimap<String, Token> subscriptions;
    private final Map<Token, SubscriberInterface> subscribers;

    /**
     * Create a new Broker.
     */
    public Broker() {

        subscriptions = HashMultimap.create();
        subscribers = new HashMap<>();
        topics = new HashSet<>();

    }

    @Override
    public synchronized void subscribe(SubscriberInterface subscriber, Token token) {

        if (subscriber == null) {
            throw new IllegalArgumentException("SubscriberInterface must not be null.");
        } else if (token == null) {
            throw new IllegalArgumentException("Token must not be null.");
        }

        if (subscribers.values().contains(subscriber)) {
            return; // Already in subscribers
        } else if (subscribers.keySet().contains(token)) {
            throw new IllegalArgumentException("Subscriber is already connected to the " +
                    "broker with another interface.");
        }

        subscribers.put(token, subscriber);

    }

    @Override
    public synchronized void unsubscribe(SubscriberInterface subscriber) {

        // If it was already removed nothing happens
        boolean unsubscribed = subscribers.values().remove(subscriber);

        if (unsubscribed) {
            LOG.log(Level.INFO, "Client unsubscribed.");
        }

    }

    @Override
    public synchronized void publish(BroadcastMsg message, String topic) {

        if (message == null) {
            throw new IllegalArgumentException("Message must not be null.");
        } else if (!topic.contains(topic)) {
            throw new IllegalArgumentException("Invalid topic.");
        }

        // Find the clients subscribed to a topic
        Collection<Token> subscribedToTopic = subscriptions.get(topic);

        for (Token token : subscribedToTopic) {

            // Get the subscriber interface to communicate with the client (if any)
            SubscriberInterface subscriber = subscribers.get(token);

            if (subscriber == null) {
                LOG.log(Level.INFO, "Found a client not connected to the broker.");
                continue; // Don't send anything
            }

            try {
                // Try to dispatch the message
                subscriber.dispatchMessage(message);
            } catch (RemoteException e) {
                LOG.log(Level.INFO, "Error while dispatching message to subscriber.", e);
                unsubscribe(subscriber);
            }

        }

    }

    @Override
    public synchronized void addTopic(String topic) {

        if (topics.contains(topic)) {
            throw new IllegalArgumentException("Topic already present.");
        }

        topics.add(topic);

    }

    @Override
    public void addTopic(String topic, Set<Token> clients) {

        addTopic(topic);
        subscribeClientsToTopic(topic, clients);

    }

    @Override
    public void subscribeClientToTopic(String topic, Token client) {

        if (!topics.contains(topic)) {
            throw new IllegalArgumentException("Invalid topic.");
        } else if (client == null) {
            throw new IllegalArgumentException("Client must not be null");
        }

        subscriptions.put(topic, client);

    }

    @Override
    public void subscribeClientsToTopic(String topic, Set<Token> clients) {

        if (!topics.contains(topic)) {
            throw new IllegalArgumentException("Invalid topic(does not exist).");
        } else if (clients == null) {
            throw new IllegalArgumentException("Clients must not be null");
        }

        subscriptions.putAll(topic, clients);

    }

    @Override
    public void unsubscribeClientFromTopic(String topic, Token client) {

        if (!topics.contains(topic)) {
            throw new IllegalArgumentException("Invalid topic (does not exist).");
        } else if (client == null) {
            throw new IllegalArgumentException("Client must not be null");
        }

        subscriptions.remove(topic, client);

    }

    @Override
    public void removeClient(Token client) {

        if (client == null) {
            throw new IllegalArgumentException("Client must not be null");
        }

        subscriptions.values().remove(client);

    }

    @Override
    public void removeClients(Set<Token> clients) {

        if (clients == null) {
            throw new IllegalArgumentException("Client must not be null");
        }

        subscriptions.values().removeAll(clients);

    }

    @Override
    public void removeTopic(String topic) {

        if (!topics.contains(topic)) {
            throw new IllegalArgumentException("Invalid topic.");
        }

        subscriptions.removeAll(topic);
        topics.remove(topic);

    }

}
