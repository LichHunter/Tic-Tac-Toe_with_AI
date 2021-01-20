import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;


class Test {
    String input;
    String result;
    String state;
    String additionalContains;

    Test(String input, String result, String state) {
        this(input, result, state, null);
    }

    Test(String input, String result, String state, String additionalContains) {
        this.input = input;
        this.result = result;
        this.state = state;
        this.additionalContains = additionalContains;
    }
}


public class TicTacToeTest extends StageTest<String> {

    Test[] tests = {
        new Test("_XXOO_OX_\n1 1", "XXXOO_OX_", "X wins"),
        new Test("_XXOO_OX_\n1 3\n1 1", "XXXOO_OX_", "X wins", "This cell is occupied! Choose another one!"),
        new Test("_XXOO_OX_\n1 4\n1 1", "XXXOO_OX_", "X wins", "Coordinates should be from 1 to 3!"),
        new Test("_XXOO_OX_\none\n1 1", "XXXOO_OX_", "X wins", "You should enter numbers!"),
        new Test("_XXOO_OX_\none three\n1 1", "XXXOO_OX_", "X wins", "You should enter numbers!"),
        new Test("_XXOO_OX_\n2 3", "_XXOOXOX_", "Game not finished"),
        new Test("_XXOO_OX_\n3 3", "_XXOO_OXX", "Game not finished"),
        new Test("XX_XOXOO_\n1 3", "XXOXOXOO_", "O wins"),
        new Test("XX_XOXOO_\n3 3", "XX_XOXOOO", "O wins"),
        new Test("_XO_OX___\n1 1", "XXO_OX___", "Game not finished"),
        new Test("_XO_OX___\n2 1", "_XOXOX___", "Game not finished"),
        new Test("_XO_OX___\n3 1", "_XO_OXX__", "Game not finished"),
        new Test("_XO_OX___\n3 2", "_XO_OX_X_", "Game not finished"),
        new Test("_XO_OX___\n3 3", "_XO_OX__X", "Game not finished"),
        new Test("_XO_OX__X\n1 1", "OXO_OX__X", "Game not finished"),
        new Test("_XO_OX__X\n2 1", "_XOOOX__X", "Game not finished"),
        new Test("_XO_OX__X\n3 1", "_XO_OXO_X", "O wins"),
        new Test("_XO_OX__X\n3 2", "_XO_OX_OX", "Game not finished"),
        new Test("XO_OXOX__\n1 3", "XOXOXOX__", "X wins"),
        new Test("XO_OXOX__\n3 2", "XO_OXOXX_", "Game not finished"),
        new Test("XO_OXOX__\n3 3", "XO_OXOX_X", "X wins"),
    };


    @DynamicTest(data = "tests")
    CheckResult testGridOutput(Test testCase) {

        TestedProgram program = new TestedProgram();
        program.start();

        String output = program.execute(testCase.input);

        Grid grid = Grid.fromLine(testCase.input.split("\n")[0]);
        Grid outputGrid = Grid.fromOutput(output, 1);

        if (!grid.equals(outputGrid)) {
            return CheckResult.wrong("The printed grid is not equal to the input grid!\n" +
                "Correct grid:\n\n" + grid);
        }

        if (testCase.additionalContains != null && !output.contains(testCase.additionalContains)) {
            return CheckResult.wrong("\"" + testCase.additionalContains + "\" expected in the output!");
        }

        Grid resultGrid = Grid.fromOutput(output, 2);
        Grid correctResultGrid = Grid.fromLine(testCase.result);

        if (!resultGrid.equals(correctResultGrid)) {
            return CheckResult.wrong("The printed result grid is not correct!\n" +
                "Correct result grid:\n\n" + correctResultGrid);
        }

        if (!output.contains(testCase.state)) {
            return CheckResult.wrong("The game result is wrong. Expected result \"X win\".");
        }

        return CheckResult.correct();
    }
}
