package easy;

import common.AbstractCodeEvalTest;
import common.CodeEvalTestData;

@CodeEvalTestData(
        testClass = FizzBuzz.class,
        input = {
                "3 5 10",
                "2 7 15"
        },
        expectedOutput = {
                "1 2 F 4 B F 7 8 F B",
                "1 F 3 F 5 F B F 9 F 11 F 13 FB 15"
        }
)
public class FizzBuzzTest extends AbstractCodeEvalTest {

}