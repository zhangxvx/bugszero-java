package com.adaptionsoft.games;

public class Player {
    private final String name;
    private int goldCoin = 0;
    private boolean inPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void sendToPenaltyBox() {
        this.inPenaltyBox = true;
    }

    public void getOutOfPenaltyBox() {
        this.inPenaltyBox = false;
    }

    public int getGoldCoin() {
        return goldCoin;
    }

    void earnGoldCoin() {
        this.goldCoin = getGoldCoin() + 1;
    }
}
