
package com.adaptionsoft.games;

import java.util.Random;


public class GameRunner {

    public static void main(String[] args) {
        Random rand = new Random();
        playGame(rand);
    }

    public static void playGame(Random rand) {
        Game game = newGameWithSamplePlayers();
        boolean notAWinner;
        do {
            notAWinner = game.playARound(new Dice(rand));
        } while (notAWinner);
    }

    private static Game newGameWithSamplePlayers() {
        Players players = new Players();
        players.add("Chet");
        players.add("Pat");
        players.add("Sue");
        return new Game(players);
    }
}
