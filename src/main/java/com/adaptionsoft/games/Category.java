package com.adaptionsoft.games;

import java.util.LinkedList;

public class Category {
    private final String name;
    private final LinkedList<String> questions = new LinkedList<>();

    public Category(String name) {
        this.name = name;
        initQuestions();
    }

    public String getName() {
        return name;
    }

    void initQuestions() {
        for (int i = 0; i < 50; i++) {
            questions.addLast(getName() + " Question " + i);
        }
    }

    String pickQuestion() {
        return questions.removeFirst();
    }
}
