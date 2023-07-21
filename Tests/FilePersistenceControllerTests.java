package gr.aueb.cf.bankApp.Tests;

import gr.aueb.cf.bankApp.controllers.FileIPersistenceController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FilePersistenceControllerTests {

    String filePath = "C:\\Users\\User\\IdeaProjects\\Repetition\\test-transactions.txt";

    @AfterEach
    void tearDown() {
        File myObj = new File(filePath);
        myObj.delete();
    }

    @Test
    void testMakeDeposit() throws IOException {
        FileIPersistenceController subject = new FileIPersistenceController(filePath);
        String prefix = String.valueOf(LocalDateTime.now());

        subject.recordTransaction(prefix, "test", 10, "G123456");

        Path filename = Path.of(filePath);
        String transactions = Files.readString(filename).trim();
        assertEquals(transactions, prefix + " - test: Amount=10.0, IBAN=G123456");
    }

}