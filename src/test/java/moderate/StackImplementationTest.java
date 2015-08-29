package moderate;

import common.AbstractCodeEvalTest;
import common.CodeEvalTestData;

@CodeEvalTestData(
        testClass = StackImplementation.class,
        input = {
                "1 2 3 4",
                "10 -2 3 4"
        },
        expectedOutput = {
                "4 2",
                "4 -2"
        }
)
public class StackImplementationTest extends AbstractCodeEvalTest {

}