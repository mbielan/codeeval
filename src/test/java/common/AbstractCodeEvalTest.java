package common;

import org.junit.*;
import org.junit.rules.TestName;

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

    private CodeEvalTestData testData;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() throws IOException {
        if (isTestMainExecuted()) {
            setUpTestData();
            setUpTmpFile();
            setUpTmpFileContent();
            setUpSystemOut();
        }
    }

    @Test
    public void testMain() throws Exception {
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
        if (isTestMainExecuted()) {
            if (inputFile != null) {
                inputFile.delete();
            }
            System.setOut(sout);
        }
    }

    private boolean isTestMainExecuted() {
        return testName.getMethodName().equals("testMain");
    }

    private void setUpTestData() {
        if (this.getClass().isAnnotationPresent(CodeEvalTestData.class)) {
            testData = this.getClass().getAnnotation(CodeEvalTestData.class);
        } else {
            throw new RuntimeException("Test class need to be annotated with CodeEvalTestData.class");
        }
    }

    private void setUpTmpFile() throws IOException {
        Assume.assumeTrue("No input/inputFile in @CodeEvalTestData",
                testData.input().length > 0 || !testData.inputFile().isEmpty());

        if (!testData.inputFile().isEmpty()) {
            inputFile = new File(getClass().getClassLoader().getResource(testData.inputFile()).getFile());
        } else {
            inputFile = File.createTempFile("codeeval", ".tmp");
        }
    }

    private void setUpTmpFileContent() throws IOException {
        if (!testData.inputFile().isEmpty()) {
            return;
        }

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

    private long getUsedMemory() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
}
