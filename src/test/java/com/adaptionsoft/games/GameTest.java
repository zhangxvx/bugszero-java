package com.adaptionsoft.games;

import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

public class GameTest {

	@Test
	public void itsLockedDown() throws Exception {
        Random randomizer = new Random(123455);
        IntStream.range(1,15).forEach(i -> GameRunner.playGame(randomizer));
	}
}
