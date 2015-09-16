package moderate;

import common.AbstractCodeEvalTest;
import common.CodeEvalTestData;


@CodeEvalTestData(
        testClass = RoboAndRobitta.class,
        input = {
                "2x2 | 2 2",
                "2x2 | 2 1",
                "2x2 | 1 1",
                "3x2 | 2 1",
                "4x4 | 3 3",
                "4x4 | 4 3",
                "4x4 | 2 2",
        },
        expectedOutput = {
                "2",
                "3",
                "4",
                "5",
                "14",
                "5",
                "16"
        }
)
public class RoboAndRobittaTest extends AbstractCodeEvalTest {

}