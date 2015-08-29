package common;

import org.junit.*;

import java.io.*;
import java.lang.reflect.Method;

public abstract class AbstractCodeEvalTest {

    private static final long SEK = 1000;
    private static final long MAX_EXEC_TIME = 10 * SEK;
    private static final long MB = 1024 * 1024;
    private static final long MAX_MEMORY_USAGE = 20 * MB;

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private PrintStream sout;

    private File inputFile;

    private CodeEvalTest testData;

    @Before
    public void setUp() throws IOException {
        setUpTestData();
        setUpTmpFile();
        setUpTmpFileContent();
        setUpSystemOut();
    }

    @Test
    public void testMain() throws Exception {
        Assume.assumeTrue("No input in @CodeEvalTest", testData.input().length > 0);
        Assume.assumeTrue("No expectedOutput in @CodeEvalTest", testData.expectedOutput().length > 0);

        Method method = testData.testClass().getDeclaredMethod("main", String[].class);

        long execStart = System.currentTimeMillis();

        method.invoke(null, getArgs());

        long execEnd = System.currentTimeMillis();
        long usedMemory = getUsedMemory();

        sout.println("Exec time [s]: " + (execEnd - execStart) / SEK);
        sout.println("Mem used [MB]: " + usedMemory / MB);

        Assert.assertTrue("Execution was too long", execEnd - execStart < MAX_EXEC_TIME);
        Assert.assertTrue("Memory usage too big", usedMemory < MAX_MEMORY_USAGE);
        Assert.assertArrayEquals(testData.expectedOutput(), getActualOutput());
    }

    @After
    public void cleanUp() {
        if (inputFile != null) {
            inputFile.delete();
        }
        System.setOut(sout);
    }

    private void setUpTestData() {
        if (this.getClass().isAnnotationPresent(CodeEvalTest.class)) {
            testData = this.getClass().getAnnotation(CodeEvalTest.class);
        } else {
            throw new RuntimeException("Test class need to be annotated with CodeEvalTest.class");
        }
    }

    private void setUpTmpFile() throws IOException {
        inputFile = File.createTempFile("codeeval", ".tmp");
    }

    private void setUpTmpFileContent() throws IOException {
        try (
            PrintWriter writer = new PrintWriter(inputFile, "UTF-8")
        ){
            String[] input = testData.input();
            int i = 0;
            for (; i < input.length - 1; i++) {
                writer.println(input[i]);
            }
            writer.print(input[i]);
        }
    }

    private void setUpSystemOut() {
        sout = System.out;
        System.setOut(new PrintStream(outStream));
    }

    private Object getArgs() {
        return new String[] {inputFile.getAbsolutePath()};
    }

    private String[] getActualOutput() {
        return outStream.toString().split("\r\n");
    }

    public long getUsedMemory() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
}
