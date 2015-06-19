package it.polimi.ingsw.cg_2.controller.actions;

/**
 *
 */
public abstract class UseItemAction extends Action {



    @Override
    public boolean isValid() {

        // Using the majority of items is aways valid, if not true we will
        // override this method in subclasses
        return true;

    }

}
