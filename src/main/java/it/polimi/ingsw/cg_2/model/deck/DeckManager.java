package it.polimi.ingsw.cg_2.model.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * This class models and manages a Deck of (@link Card)s (deck and discarded
 * pile). Once a deck is created it is possible to draw cards, shuffle the deck,
 * discard used cards in a discard pile, etc. When there are no more cards in a
 * deck the discard pile (if not empty) is automatically shuffled and used.
 * <p>
 * Elements of type (@link Card) can be handled polymorphically and it is
 * possible to create decks of mixed card types. In this case it does not make
 * sense to handle different decks polymorphically as they are just a data
 * structure similar to a (@link Stack) with additional behavior, so it is
 * better to use a class with genetic parameters.
 *
 * @param <E> the element type
 */
public class DeckManager<E extends Card> {

    /** The cards deck. */
    private Stack<E> deck;

    /** The discard pile. */
    private ArrayList<E> discardPile;

    /**
     * Instantiates a new deck manager.
     *
     * @param deck the deck contained in the new initialized DeckManage
     */
    private DeckManager(Stack<E> deck) {
        this.deck = deck; // TODO: To Factory Method
    }

    /**
     * Draws a card by removing it from the stack. If the deck is empty it
     * checks if there are cards in the discardPile, if this empty then there
     * are no more cards and it returns null, otherwise the discardPile becomes
     * the new deck and cards are shuffled.
     */
    public E drawCard() {
        if (this.isEmpty()) {
            // No more cards in the deck AND in the discardPile
            return null;
        } else {
            if (this.isDeckEmpty()) {
                // Move the cards from the discardPile into the empty deck.
                deck.addAll(discardPile);
                discardPile.clear();
                // Shuffle the deck
                this.shuffleDeck();
                // Pop a card and return it
                return deck.pop();
            } else {
                return deck.pop();
            }
        }
    }

    /**
     * Adds a card to the discardPile.
     *
     * @param card the card to discard
     */
    public void discardCard(E card) {
        discardPile.add(card);
    }

    /**
     * Checks if the deck is empty.
     *
     * @return true, if the deck empty
     */
    public boolean isDeckEmpty() {
        return deck.isEmpty();
    }

    /**
     * Checks if the card manager is empty (deck is empty <b>and</b> the
     * discarded pile is empty).
     *
     * @return true, if the deck and the discarded pile is empty
     */
    public boolean isEmpty() {
        return deck.isEmpty() && discardPile.isEmpty();
    }

    /**
     * Shuffle the deck of Cards.
     */
    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

}
