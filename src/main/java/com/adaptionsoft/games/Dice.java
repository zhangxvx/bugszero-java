package com.adaptionsoft.games;

import java.util.Random;

public class Dice {
    private Random random;

    public Dice(Random random) {
        this.random = random;
    }

    int roll() {
        return random.nextInt(5) + 1;
    }

    boolean chanceToWrongAnswer() {
        return random.nextInt(9) == 7;
    }
}
