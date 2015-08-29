package moderate;

import common.AbstractCodeEvalTest;
import common.CodeEvalTestData;
import org.junit.Assert;
import org.junit.Test;

@CodeEvalTestData(
        testClass = DetectingCycles.class,
        input = {
                "2 0 6 3 1 6 3 1 6 3 1",
                "3 4 8 0 11 9 7 2 5 6 10 1 49 49 49 49",
                "1 2 3 1 2 3 1 2 3",
                "1 1",
                "1"
        },
        expectedOutput = {
                "6 3 1",
                "49",
                "1 2 3",
                "1",
                "1"
        }
)
public class DetectingCyclesTest extends AbstractCodeEvalTest {

    @Test
    public void testSingleCycle() {
        Assert.assertEquals("6 3 1", DetectingCycles.getCycle("2 0 6 3 1 6 3 1 6 3 1"));
    }
}