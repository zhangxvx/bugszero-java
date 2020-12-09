package com.adaptionsoft.games;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionMapTest {
    @Test
    public void testCategoryDsitribution() {
        QuestionMap questionMap = new QuestionMap();
        Player play = new Player("player");
        List<String> names = Arrays.asList("Science", "Sports", "Rock", "Blues", "History", "Pop");
        for (String name : names) {
            questionMap.movePlayer(play, 1);
            assertEquals(name, questionMap.getPlayerCategory(play).getName());
        }
    }
}