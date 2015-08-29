package easy;

import common.AbstractCodeEvalTest;
import common.CodeEvalTestData;

@CodeEvalTestData(
        testClass = ReverseWords.class,
        input = {
                "Hello World",
                "Hello CodeEval"
        },
        expectedOutput = {
                "World Hello",
                "CodeEval Hello"
        }
)
public class ReverseWordsTest extends AbstractCodeEvalTest {

}