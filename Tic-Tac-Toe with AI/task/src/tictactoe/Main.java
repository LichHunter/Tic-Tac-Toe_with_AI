package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Checker checker = new Checker();
        Field field = new Field(checker);

        System.out.println("Enter the cells:");
        String fieldFill = new Scanner(System.in).next();
        field.fillField(fieldFill);
        field.showField();

        int i = field.determineWhoMakeNextMove().equals("X") ? 0 : 1;
        State result = State.Continue;

        while (result == State.Continue) {
            try {
                System.out.println("Enter the coordinates: ");
                String coordinates = new Scanner(System.in).nextLine();
                field.putOnField(i % 2 == 0 ? "X" : "O", coordinates);
                field.showField();
                i++;

                result = checker.checkGameState(field.getField());
                break;
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
        } else {
            System.out.println(State.Continue.getMessage());
        }
    }
}
