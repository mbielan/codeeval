package easy;

import common.AbstractCodeEvalTest;
import common.CodeEvalTestData;

@CodeEvalTestData(
        testClass = StringMask.class,
        input = {
                "hello 11001",
                "world 10000",
                "cba 111"
        },
        expectedOutput = {
                "HEllO",
                "World",
                "CBA"
        }
)
public class StringMaskTest extends AbstractCodeEvalTest {

}