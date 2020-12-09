package com.adaptionsoft.games;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class GameTest {
    public static final String BASELINE_FILE = "src/test/resources/baseline.txt";

    @Test
    public void safeNetTest() throws Exception {
        ByteArrayOutputStream out = catchSystemOut();
        Random randomizer = new Random(123455);
        IntStream.range(1, 15).forEach(i -> GameRunner.playGame(randomizer));
        assertEquals(new String(Files.readAllBytes(Paths.get(BASELINE_FILE))), out.toString());
    }

    private ByteArrayOutputStream catchSystemOut() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }

    @Test
    public void addPlayerBugTest() {
        Players players = new Players();
        players.add("1");
        players.add("2");
        players.add("3");
        players.add("4");
        players.add("5");
        players.add("6");
        Game game = new Game(players);
    }

    @Test
    public void should_earn_gold_coin_to_correct_player() {
        ByteArrayOutputStream out = catchSystemOut();
        Players players = new Players();
        players.add("A");
        players.add("B");
        Game game = new Game(players);
        for (int i = 0; i < 5; i++) {
            playARound(game, 1, true); //A move 1,A in Penalty Box
            playARound(game, 2, false); //B move 1,B in Penalty Box
        }
        playARound(game, 1, false); //A move 1,A in Penalty Box
        playARound(game, 2, false); //B move 1,B in Penalty Box
        boolean isGameRunning = playARound(game, 1, true); //A earn gold coin, and become winner
        String expect = "A was added\r\n" +
                "They are player number 1\r\n" +
                "B was added\r\n" +
                "They are player number 2\r\n" +
                "A is the current player\r\n" +
                "They have rolled a 1\r\n" +
                "A's new location is 1\r\n" +
                "The category is Science\r\n" +
                "Science Question 0\r\n" +
                "Answer was correct!!!!\r\n" +
                "A now has 1 Gold Coins.\r\n" +
                "B is the current player\r\n" +
                "They have rolled a 2\r\n" +
                "B's new location is 2\r\n" +
                "The category is Sports\r\n" +
                "Sports Question 0\r\n" +
                "Question was incorrectly answered\r\n" +
                "B was sent to the penalty box\r\n" +
                "A is the current player\r\n" +
                "They have rolled a 1\r\n" +
                "A's new location is 2\r\n" +
                "The category is Sports\r\n" +
                "Sports Question 1\r\n" +
                "Answer was correct!!!!\r\n" +
                "A now has 2 Gold Coins.\r\n" +
                "B is the current player\r\n" +
                "They have rolled a 2\r\n" +
                "B is not getting out of the penalty box\r\n" +
                "A is the current player\r\n" +
                "They have rolled a 1\r\n" +
                "A's new location is 3\r\n" +
                "The category is Rock\r\n" +
                "Rock Question 0\r\n" +
                "Answer was correct!!!!\r\n" +
                "A now has 3 Gold Coins.\r\n" +
                "B is the current player\r\n" +
                "They have rolled a 2\r\n" +
                "B is not getting out of the penalty box\r\n" +
                "A is the current player\r\n" +
                "They have rolled a 1\r\n" +
                "A's new location is 4\r\n" +
                "The category is Blues\r\n" +
                "Blues Question 0\r\n" +
                "Answer was correct!!!!\r\n" +
                "A now has 4 Gold Coins.\r\n" +
                "B is the current player\r\n" +
                "They have rolled a 2\r\n" +
                "B is not getting out of the penalty box\r\n" +
                "A is the current player\r\n" +
                "They have rolled a 1\r\n" +
                "A's new location is 5\r\n" +
                "The category is History\r\n" +
                "History Question 0\r\n" +
                "Answer was correct!!!!\r\n" +
                "A now has 5 Gold Coins.\r\n" +
                "B is the current player\r\n" +
                "They have rolled a 2\r\n" +
                "B is not getting out of the penalty box\r\n" +
                "A is the current player\r\n" +
                "They have rolled a 1\r\n" +
                "A's new location is 6\r\n" +
                "The category is Pop\r\n" +
                "Pop Question 0\r\n" +
                "Question was incorrectly answered\r\n" +
                "A was sent to the penalty box\r\n" +
                "B is the current player\r\n" +
                "They have rolled a 2\r\n" +
                "B is not getting out of the penalty box\r\n" +
                "A is the current player\r\n" +
                "They have rolled a 1\r\n" +
                "A is getting out of the penalty box\r\n" +
                "A's new location is 7\r\n" +
                "The category is Science\r\n" +
                "Science Question 1\r\n" +
                "Answer was correct!!!!\r\n" +
                "A now has 6 Gold Coins.\r\n";
        assertEquals(expect, out.toString());
        assertEquals(false, isGameRunning);
    }

    private boolean playARound(Game game, final int step, boolean isCorrect) {
        Random rand = new Random(1) {
            @Override
            public int nextInt(int bound) {
                if (bound == 5) {
                    return step - 1;
                }
                if (bound == 9) {
                    if (!isCorrect) {
                        return 7;
                    }
                }
                return 0;
            }
        };
        return game.playARound(new Dice(rand));
    }

    @Test
    public void should_not_show_penalty_info_get_out() {
        ByteArrayOutputStream out = catchSystemOut();
        Players players = new Players();
        players.add("A");
        players.add("B");
        Game game = new Game(players);
        playARound(game, 1, false); //A move 1,A in Penalty Box
        playARound(game, 2, true); //B move 1,B earn 1 gold coin
        playARound(game, 1, true); //A move 1,A earn 1 gold coin
        playARound(game, 2, true); //B move 1,B earn 1 gold coin
        playARound(game, 2, true); //A should move 2,A earn 1 gold coin
        String expected = "A was added\r\n" +
                "They are player number 1\r\n" +
                "B was added\r\n" +
                "They are player number 2\r\n" +
                "A is the current player\r\n" +
                "They have rolled a 1\r\n" +
                "A's new location is 1\r\n" +
                "The category is Science\r\n" +
                "Science Question 0\r\n" +
                "Question was incorrectly answered\r\n" +
                "A was sent to the penalty box\r\n" +
                "B is the current player\r\n" +
                "They have rolled a 2\r\n" +
                "B's new location is 2\r\n" +
                "The category is Sports\r\n" +
                "Sports Question 0\r\n" +
                "Answer was correct!!!!\r\n" +
                "B now has 1 Gold Coins.\r\n" +
                "A is the current player\r\n" +
                "They have rolled a 1\r\n" +
                "A is getting out of the penalty box\r\n" +
                "A's new location is 2\r\n" +
                "The category is Sports\r\n" +
                "Sports Question 1\r\n" +
                "Answer was correct!!!!\r\n" +
                "A now has 1 Gold Coins.\r\n" +
                "B is the current player\r\n" +
                "They have rolled a 2\r\n" +
                "B's new location is 4\r\n" +
                "The category is Blues\r\n" +
                "Blues Question 0\r\n" +
                "Answer was correct!!!!\r\n" +
                "B now has 2 Gold Coins.\r\n" +
                "A is the current player\r\n" +
                "They have rolled a 2\r\n" +
                "A's new location is 4\r\n" +
                "The category is Blues\r\n" +
                "Blues Question 1\r\n" +
                "Answer was correct!!!!\r\n" +
                "A now has 2 Gold Coins.\r\n";
        assertEquals(expected, out.toString());
    }
}
