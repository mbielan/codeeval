package moderate;

import common.AbstractCodeEvalTest;
import common.CodeEvalTestData;

@CodeEvalTestData(
        testClass = PredictTheNumber.class,
        input = {
                "0",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "101",
                "25684"
        },
        expectedOutput = {
                "0",
                "1",
                "1",
                "2",
                "1",
                "2",
                "2",
                "0",
                "1",
                "1",
                "0"
        }
)
public class PredictTheNumberTest extends AbstractCodeEvalTest {

}