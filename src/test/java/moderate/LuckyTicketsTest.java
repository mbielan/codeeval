package moderate;

import common.AbstractCodeEvalTest;
import common.CodeEvalTestData;

@CodeEvalTestData(
        testClass = LuckyTickets.class,
        input = {
                "4",
                "6",
                "8"
        },
        expectedOutput = {
                "670",
                "55252",
                "4816030"
        }
)
public class LuckyTicketsTest extends AbstractCodeEvalTest {

}