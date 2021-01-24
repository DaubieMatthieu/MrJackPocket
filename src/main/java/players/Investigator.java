package main.java.players;

import main.java.Controller;
import main.java.RandomHelper;
import main.java.tokens.ActionToken;

import java.util.Set;

public class Investigator extends Player {

    public void throwActionTokens(Set<ActionToken> actionTokens) {
        Controller.getGui().frontPane.showText("The investigator is throwing the action tokens");
        for (ActionToken actionToken : actionTokens) {
            if (RandomHelper.randomBoolean()) actionToken.flip();
        }
    }
}
