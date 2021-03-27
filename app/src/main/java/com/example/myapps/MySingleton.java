package com.example.myapps;

import java.util.ArrayList;
import java.util.List;

public class MySingleton {
    private static final MySingleton instance = new MySingleton();
    private final ArrayList<String> mainMessages = new ArrayList<>();
    private final ArrayList<String> nextMessages = new ArrayList<>();

    private MySingleton() {
    }

    public static MySingleton getInstance() {
        return instance;
    }

    public void putMainMessage(String message) {
        mainMessages.add(message);
    }

    public List<String> getMainMessages() {
        return mainMessages;
    }

    public void putNextMessage(String message) {
        nextMessages.add(message);
    }

    public List<String> getNextMessages() {
        return nextMessages;
    }
}
