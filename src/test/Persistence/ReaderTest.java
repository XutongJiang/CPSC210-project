package Persistence;

import org.junit.jupiter.api.Test;
import persistence.Reader;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    Reader testReader = new Reader();

    @Test
    void testIOException() {
        try {
            Reader.readNecessities(new File("./path/does/not/exist/testAccount.txt"));
            fail();
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReadValidFile() {
        try {
            Reader.readNecessities(new File("./data/testNecessities.txt"));
        } catch (IOException e) {
            fail("exception should not be thrown since the path is valid.");
        }
    }
}

