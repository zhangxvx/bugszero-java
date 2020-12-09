package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.List;

public class Players {
    List<Player> players = new ArrayList<Player>();
    int currentPlayerIndex = 0;

    int getCurrentPlayerGoldCoin() {
        return getCurrentPlayer().getGoldCoin();
    }

    boolean isCurrentPlayerInPenaltyBox() {
        return getCurrentPlayer().isInPenaltyBox();
    }

    String getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }

    Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    void toNextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
    }

    public void add(String playerName) {
        this.players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }
}