package com.adaptionsoft.games;

import java.util.HashMap;
import java.util.Map;

public class QuestionMap {
    public static final int MAP_SIZE = 12;
    Category[] categories = new Category[]{
            new Category("Pop"), new Category("Science"), new Category("Sports"),
            new Category("Rock"), new Category("Blues"), new Category("History")
    };
    Map<Player, Integer> playerLocation = new HashMap<>();

    private int placeToCategoryIndex(int place) {
        return place % categories.length;
    }

    public int getPlayerLocation(Player player) {
        return playerLocation.getOrDefault(player, 0);
    }

    public void movePlayer(Player player, int roll) {
        int location = (getPlayerLocation(player) + roll) % MAP_SIZE;
        playerLocation.put(player, location);
        System.out.println(player.getName()
                + "'s new location is "
                + getPlayerLocation(player));

    }

    public Category getPlayerCategory(Player player) {
        int place = getPlayerLocation(player);
        return categories[placeToCategoryIndex(place)];
    }

    void askQuestion(Player player) {
        Category category = getPlayerCategory(player);
        System.out.println("The category is " + category.getName());
        System.out.println(category.pickQuestion());
    }
}