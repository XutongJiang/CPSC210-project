package Persistence;

import model.Necessities;
import model.Necessity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//test Writer
public class WriterTest {

    private static final String TEST_FILE = "./data/testNecessities.txt";
    private Writer testWriter;
    private Necessity tn;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        tn = new Necessity(2,1,"test", 1.0, 10.0);
    }

    @Test
    void testWriteNecessities() {
        // save necessities list to file
        testWriter.write(tn);
        testWriter.close();

        // now read them back in and verify that the necessities have the expected values
        try {
            Necessities necessities = Reader.readNecessities(new File(TEST_FILE));
            Necessity tn = necessities.returnGivenNecessity("test");
            assertEquals(1, tn.getId());
            assertEquals("test", tn.getName());
            assertEquals(10.0, tn.getAmount());
            assertEquals(1.0, tn.getDailyUsage());

            // verify that ID of next account created is 3 (checks that nextAccountId was restored)
            Necessity next = new Necessity("test2", 2.0, 15.0);
            assertEquals(2, next.getId());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }


}
