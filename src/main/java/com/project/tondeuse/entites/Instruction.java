package com.project.tondeuse.entites;

import java.util.ArrayList;
import java.util.List;

public enum Instruction {
    LEFT('G'),
    RIGHT('D'),
    ADVANCE('A');

    private final char code;

    Instruction(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static Instruction fromCode(char code) {
        for (Instruction instruction : Instruction.values()) {
            if (instruction.code == code) {
                return instruction;
            }
        }
        throw new IllegalArgumentException("Invalid instruction code: " + code);
    }

    public static List<Instruction> fromString(String instructions) {
        List<Instruction> instructionList = new ArrayList<>();
        for (char code : instructions.toCharArray()) {
            instructionList.add(fromCode(code));
        }
        return instructionList;
    }

    public void execute(Tondeuse tondeuse) {
        switch (this) {
            case LEFT:
                tondeuse.turnLeft();
                break;
            case RIGHT:
                tondeuse.turnRight();
                break;
            case ADVANCE:
                tondeuse.advance();
                break;
            default:
                throw new IllegalArgumentException("Invalid instruction: " + this);
        }
    }
}
