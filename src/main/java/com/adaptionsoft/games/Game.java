package com.adaptionsoft.games;

public class Game {
    public static final int GOLD_COIN_TO_WIN = 6;
    final Players players;
    private final QuestionMap questionMap;

    public Game(Players players) {
        this.players = players;
        questionMap = new QuestionMap();
    }

    private void checkPenaltyBoxState(int roll) {
        if (players.isCurrentPlayerInPenaltyBox()) {
            if (canOutPenaltyBox(roll)) {
                players.getCurrentPlayer().getOutOfPenaltyBox();
                System.out.println(players.getCurrentPlayerName() + " is getting out of the penalty box");
            } else {
                System.out.println(players.getCurrentPlayerName() + " is not getting out of the penalty box");
            }
        }
    }

    private boolean canOutPenaltyBox(int roll) {
        return roll % 2 != 0;
    }

    public boolean playARound(Dice dice) {
        int roll = dice.roll();
        System.out.println(players.getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);
        checkPenaltyBoxState(roll);
        boolean isGameRunning = true;
        if (!players.isCurrentPlayerInPenaltyBox()) {
            questionMap.movePlayer(players.getCurrentPlayer(), roll);
            questionMap.askQuestion(players.getCurrentPlayer());
            isGameRunning = answerQuestion(dice);
        }
        players.toNextPlayer();
        return isGameRunning;
    }

    private boolean answerQuestion(Dice dice) {
        if (dice.chanceToWrongAnswer()) {
            answerWrong();
            return true;
        }
        return answerCorrectly();
    }

    private boolean answerCorrectly() {
        System.out.println("Answer was correct!!!!");
        players.getCurrentPlayer().earnGoldCoin();
        System.out.println(players.getCurrentPlayerName()
                + " now has "
                + players.getCurrentPlayerGoldCoin()
                + " Gold Coins.");
        return players.getCurrentPlayerGoldCoin() < GOLD_COIN_TO_WIN;
    }

    private void answerWrong() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.getCurrentPlayerName() + " was sent to the penalty box");
        players.getCurrentPlayer().sendToPenaltyBox();
    }
}
