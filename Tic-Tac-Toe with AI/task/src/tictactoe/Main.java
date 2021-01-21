package tictactoe;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Checker checker = new Checker();
        Field field = new Field(checker);
        AI ai = new AI(checker);

        game(checker, field, ai, Difficulty.EASY);
    }

    private static void game(Checker checker, Field field, AI ai, Difficulty difficulty) {
        field.fillField("_________");
        field.showField();

        int i = 0;
        State result = State.Continue;

        while (result == State.Continue) {
            try {
                if (i % 2 == 0) {
                    System.out.println("Enter the coordinates: ");
                    String coordinates = new Scanner(System.in).nextLine();
                    field.putOnField("X", coordinates);
                } else {
                    System.out.println("Making move level \"" + difficulty.name().toLowerCase(Locale.ROOT) + "\"");
                    while (true) {
                        try {
                            String coordinates = ai.makeMove(difficulty, field.getField());
                            field.putOnField("O", coordinates);
                            break;
                        } catch (IllegalArgumentException e) {
                            continue;
                        }
                    }
                }
                field.showField();
                i++;

                result = checker.checkGameState(field.getField());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        if (result == State.X) {
            System.out.println(State.X.getMessage());
        } else if (result == State.O) {
            System.out.println(State.O.getMessage());
        } else if (result == State.Draw) {
            System.out.println(State.Draw.getMessage());
        }
    }
}
