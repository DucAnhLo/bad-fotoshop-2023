import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EditorTest {
    private final InputStream defaultStdin = System.in;
    private final PrintStream defaultStdout = System.out;
    @AfterAll
    public void cleanUpIO() {
        System.setIn(defaultStdin);
        System.setOut(defaultStdout);
    }
    @Test
    public void shouldDisplayHelpOnHelpCommandEntered() throws
            UnsupportedEncodingException {
        String input = "help\nquit\n";
        ByteArrayInputStream in = new
                ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        Editor instance = new Editor("", "", "", "");
        instance.set();
        instance.edit();
        String output = out.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("You are using Fotoshop."));
    }

    @Test
    public void shouldExitOnQuitCommandEntered() throws
            UnsupportedEncodingException {
        String input = "quit\n";
        ByteArrayInputStream in = new
                ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        Editor instance = new Editor("", "", "", "");
        instance.set();
        instance.edit();
        String output = out.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Thank you for using Fotoshop.  Good bye."));
    }

    @Test
    public void shouldMonochromeOnMonoCommandEntered() throws
            UnsupportedEncodingException {
        String input = "mono input.jpg\nlook input.jpg";
        ByteArrayInputStream in = new
                ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        Editor instance = new Editor("", "", "", "");
        instance.set();
        instance.edit();
        String output = out.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Filters applied: mono"));
    }

    @Test
    public void shouldOpenOnOpenCommandEntered() throws
            UnsupportedEncodingException {
        String input = "open input.jpg";
        ByteArrayInputStream in = new
                ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        Editor instance = new Editor("", "", "", "");
        instance.set();
        instance.edit();
        String output = out.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Loaded "));
    }

}