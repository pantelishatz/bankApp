package gr.aueb.cf.bankApp.Tests;
import gr.aueb.cf.bankApp.controllers.IMakeDepositController;
import gr.aueb.cf.bankApp.mocks.MockIPersistenceController;
import gr.aueb.cf.bankApp.model.Account;
import gr.aueb.cf.bankApp.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MakeDepositTests {

    MockIPersistenceController persistenceController = new MockIPersistenceController();

    @Test
    void testMakeDepositAmountInsufficient() {
        IMakeDepositController subject = new IMakeDepositController(persistenceController);
        User user = new User("Test", "Test", "000000");
        Account account = new Account("GR000000", user, 0);

        subject.makeDeposit(account, -100.0);

        assertNull(persistenceController.prefix);
        assertNull(persistenceController.transactionType);
        assertNull(persistenceController.amount);
        assertNull(persistenceController.iban);
    }

    @Test
    void testMakeDepositAmountSufficient() {
        IMakeDepositController subject = new IMakeDepositController(persistenceController);
        User user = new User("Test", "Test", "000000");
        Account account = new Account("GR000000", user, 1000);

        subject.makeDeposit(account, 100.0);

        assertNotNull(persistenceController.prefix);
        assertEquals(persistenceController.transactionType.get(), "Deposit");
        assertEquals(persistenceController.amount.get(), 100.0);
        assertEquals(persistenceController.iban.get(), "GR000000");
    }


}
