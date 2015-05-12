package it.polimi.ingsw.cg_2.model.deck;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * This class models and manages a Deck of (@link Card)s (deck and discarded
 * pile). Once a deck is created it is possible to draw cards, shuffle the deck,
 * discard used cards in a discard pile, etc. When there are no more cards in a
 * deck the discard pile (if not empty) is automatically shuffled and used.
 * <p>
 * Elements of type (@link Card) can be handled polymorphically and it is
 * possible to create decks of mixed card types. In this case it does not make
 * sense to handle different decks polymorphically as they are just a data
 * structure similar to a (@link Stack) with additional behavior and different
 * decks must be used in different situations but with the <b>same behavior</b>,
 * so it is better to use a class with genetic parameters.
 *
 * @param <E> the element type
 */
public class Deck<E extends Card> {

    // Deque is better than Stack
    private final Deque<E> drawPile;
    private final List<E> discardPile;

    /**
     * Instantiates a new Deck populated with a (@link Collection) of (@link
     * Card)s. The cards are <b>not shuffled</b> when the deck is created, so
     * they will be dealt from the last to the first one.
     *
     * @param cards a (@link Collection) of cards to put inside the deck
     */
    public Deck(Collection<E> cards) {

        // We use a LinkedList as it implements both Deque and List interfaces
        // (so we can also use Collections methods).
        drawPile = new LinkedList<E>();
        drawPile.addAll(cards);
        discardPile = new ArrayList<E>();

    }

    /**
     * Draws a card by removing it from the stack. If the deck is empty it
     * checks if there are cards in the discardPile, if this empty then there
     * are no more cards and it returns null, otherwise the discardPile becomes
     * the new deck and cards are automatically shuffled.
     */
    public E drawCard() {

        if (this.isEmpty()) {
            // No more cards in the deck AND in the discardPile
            return null;
        } else {
            if (this.isDrawPileEmpty()) {
                // Move the cards from the discardPile into the empty deck.
                drawPile.addAll(discardPile);
                discardPile.clear();
                // Shuffle the deck
                this.shuffleDeck();
                // Pop a card and return it
                return drawPile.removeFirst();
            } else {
                return drawPile.removeFirst();
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
    public boolean isDrawPileEmpty() {

        return drawPile.isEmpty();

    }

    /**
     * Checks if the card manager is empty (draw pile is empty <b>and</b> the
     * discarded pile is empty).
     *
     * @return true, if the draw pile and the discarded pile is empty
     */
    public boolean isEmpty() {

        return drawPile.isEmpty() && discardPile.isEmpty();

    }

    /**
     * Shuffle the deck of Cards.
     */
    public void shuffleDeck() {

        Collections.shuffle((LinkedList<?>) drawPile);

    }

}
