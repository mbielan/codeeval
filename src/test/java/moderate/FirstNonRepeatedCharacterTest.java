package moderate;

import common.AbstractCodeEvalTest;
import common.CodeEvalTestData;

@CodeEvalTestData(
        testClass = FirstNonRepeatedCharacter.class,
        input = {
                "yellow",
                "tooth",
                "a",
                "toorotrf"
        },
        expectedOutput = {
                "y",
                "h",
                "a",
                "f"
        }
)
public class FirstNonRepeatedCharacterTest extends AbstractCodeEvalTest {

}