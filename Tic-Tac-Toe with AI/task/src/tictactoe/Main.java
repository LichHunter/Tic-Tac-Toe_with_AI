package tictactoe;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Checker checker = new Checker();
        Field field = new Field(checker);
        AI ai = new AI(checker);

        menu(checker, field, ai);
    }

    private static void menu(Checker checker, Field field, AI ai) {
        String input;

        while (true) {
            try {
                System.out.println("Input command: ");
                input = new Scanner(System.in).nextLine();
                checker.checkParameters(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        if ("start".equals(input.split("\\s+")[0])) {
            game(checker, field, ai, input.split("\\s+")[1], input.split("\\s+")[2]);
        } else {
            System.exit(0);
        }
    }

    private static void game(Checker checker, Field field, AI ai, String firstPlayer, String secondPlayer) {
        field.fillField("_________");
        field.showField();

        int i = 0;
        State result = State.Continue;

        while (result == State.Continue) {
            try {
                if (i % 2 == 0) {
                    if ("user".equals(firstPlayer)) {
                        System.out.println("Enter the coordinates: ");
                        String coordinates = new Scanner(System.in).nextLine();
                        field.putOnField("X", coordinates);
                    } else {
                        System.out.println("Making move level \"" + firstPlayer + "\"");
                        while (true) {
                            try {
                                String coordinates = ai.makeMove(
                                        Difficulty.valueOf(firstPlayer.toUpperCase(Locale.ROOT)), field.getField());
                                field.putOnField("X", coordinates);
                                break;
                            } catch (IllegalArgumentException ignored) {
                            }
                        }
                    }
                } else {
                    if ("user".equals(secondPlayer)) {
                        System.out.println("Enter the coordinates: ");
                        String coordinates = new Scanner(System.in).nextLine();
                        field.putOnField("O", coordinates);
                    } else {
                        System.out.println("Making move level \"" + secondPlayer + "\"");
                        while (true) {
                            try {
                                String coordinates = ai.makeMove(
                                        Difficulty.valueOf(secondPlayer.toUpperCase(Locale.ROOT)), field.getField());
                                field.putOnField("O", coordinates);
                                break;
                            } catch (IllegalArgumentException ignored) {
                            }
                        }
                    }
                }

                field.showField();
                result = checker.checkGameState(field.getField());

                i++;
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
