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

    protected CodeEvalTestData testData;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() throws IOException {
        setUpTestData();
        if (isTestMainExecuted()) {
            setUpInputFile();
            setUpInputFileContent();
            setUpSystemOut();
        }
    }

    @Test
    public void testMain() throws Exception {
        //When
        Method method = testData.testClass().getDeclaredMethod("main", String[].class);

        long execStart = System.currentTimeMillis();

        //Then
        method.invoke(null, getArgs());

        //Verify
        long execTime = System.currentTimeMillis() - execStart;
        long usedMemory = getUsedMemory();

        log(execTime, usedMemory);

        Assert.assertTrue("Execution was too long", execTime < MAX_EXEC_TIME);
        Assert.assertTrue("Memory usage too big", usedMemory < MAX_MEMORY_USAGE);
        Assert.assertArrayEquals(testData.expectedOutput(), getActualOutput());
    }

    private void log(long execTime, long usedMemory) {
        sout.println("Exec time [s]: " + execTime / SEK);
        sout.println("Mem used [MB]: " + usedMemory / MB);
    }

    @After
    public void cleanUp() {
        if (isTestMainExecuted()) {
            if (isTempFile()) {
                inputFile.delete();
            }
            System.setOut(sout);
        }
    }

    private boolean isTempFile() {
        return inputFile != null && !isInputFileProvided();
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

    private void setUpInputFile() throws IOException {
        Assume.assumeTrue("No input/inputFile in @CodeEvalTestData",
                testData.input().length > 0 || isInputFileProvided());

        if (isInputFileProvided()) {
            inputFile = getInputFileFromResources(testData.inputFile());
        } else {
            inputFile = File.createTempFile("codeeval", ".tmp");
        }
    }

    private File getInputFileFromResources(String file) {
        return new File(getClass().getClassLoader().getResource(file).getFile());
    }

    private void setUpInputFileContent() throws IOException {
        if (isInputFileProvided()) {
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

    private boolean isInputFileProvided() {
        return !testData.inputFile().isEmpty();
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
