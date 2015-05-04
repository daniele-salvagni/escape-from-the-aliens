package it.polimi.ingsw.cg_2.model.deck;

public interface DecksFactory {

    DeckManager<ItemCard> createItemDeck();

    DeckManager<SectorCard> createSectorDeck();

    DeckManager<HatchCard> createHatchDeck();
}
