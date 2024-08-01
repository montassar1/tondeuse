package com.project.tondeuse.entites;

import java.util.Map;

public class Tondeuse {
    private int x;
    private int y;
    private char orientation;
    private final Lawn lawn;
    private final String instructions;

    private static final Map<Character, char[]> directions = Map.of(
            'N', new char[]{'W', 'E'},
            'E', new char[]{'N', 'S'},
            'S', new char[]{'E', 'W'},
            'W', new char[]{'S', 'N'}
    );

    public Tondeuse(int x, int y, char orientation, Lawn lawn, String instructions) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.lawn = lawn;
        this.instructions = instructions;
    }

    public void executeInstructions() {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'G':
                    turnLeft();
                    break;
                case 'D':
                    turnRight();
                    break;
                case 'A':
                    advance();
                    break;
            }
        }
    }

    void turnLeft() {
        orientation = directions.get(orientation)[0];
    }

    void turnRight() {
        orientation = directions.get(orientation)[1];
    }

    void advance() {
        switch (orientation) {
            case 'N':
                if (y < lawn.getHeight()) y++;
                break;
            case 'E':
                if (x < lawn.getWidth()) x++;
                break;
            case 'S':
                if (y > 0) y--;
                break;
            case 'W':
                if (x > 0) x--;
                break;
        }
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation;
    }
}
