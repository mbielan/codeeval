package moderate;

import common.AbstractCodeEvalTest;
import common.CodeEvalTestData;

@CodeEvalTestData(
        testClass = RemoveCharacters.class,
        input = {
                "how are you, abc",
                "hello world, def"
        },
        expectedOutput = {
                "how re you",
                "hllo worl"
        }
)
public class RemoveCharactersTest extends AbstractCodeEvalTest {

}