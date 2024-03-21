package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.example.dao.FetchAccount;
import org.example.dao.SOAPService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AppTest 
{
    @Mock
    private SOAPService soapService;

    @Mock
    private FetchAccount fetchAccount;

    @Before
    public void setUp() {
        when(soapService.getSOAPServicePort()).thenReturn(fetchAccount);
    }

    @Test
    public void testCreateUser() {
        // Mocking user input
        String username = "sinchana";
        String password = "sinch";
        String dateOfBirth = "2002-06-20";
        String address = "Mulki";
        String email = "sinch@gmail.com";
        long phoneNumber = 9886697394L;

        // Mocking service response
        doNothing().when(fetchAccount).create(username, password, dateOfBirth, address, email, phoneNumber);

        // Verifying service method invocation
        verify(fetchAccount).create(username, password, dateOfBirth, address, email, phoneNumber);
    }

    @Test
    public void testFindUser() {
        // Mocking user input
        String username = "sinchana";

        // Mocking service response
        List<FetchAccount> accounts = new ArrayList<>();
        FetchAccount account = new FetchAccount();
        account.setuserName(username);
        accounts.add(account);
        when(fetchAccount.findUser(username)).thenReturn(fetchAccount);
        when(fetchAccount.getuserDetailsList()).thenReturn(accounts);

        // Verifying service method invocation and output
        verify(fetchAccount).findUser(username);
        assertEquals("Username: " + username + " Password: null", getConsoleOutput());
    }

    @Test
    public void update() {
        // Mocking user input
        String username = "sinchana";

        // Mocking service response
        List<FetchAccount> accounts = new ArrayList<>();
        FetchAccount account = new FetchAccount();
        account.setuserName(username);
        accounts.add(account);
        when(fetchAccount.findUser(username)).thenReturn(fetchAccount);
        when(fetchAccount.getuserDetailsList()).thenReturn(accounts);

        // Verifying service method invocation and output
        verify(fetchAccount).findUser(username);
    }
    @Test
    public void testUpdateUser() {
        // Mocking user input
        String username = "testUser";

        // Mocking service response for finding user
        List<FetchAccount> accounts = new ArrayList<>();
        FetchAccount account = new FetchAccount();
        account.setuserName(username);
        accounts.add(account);
        when(fetchAccount.findUser(username)).thenReturn(fetchAccount);
        when(fetchAccount.getuserDetailsList()).thenReturn(accounts);

        // Mocking service response for update process
        String newPassword = "sinch";
        String newAddress = "Mulki";
        String newEmail = "sinch@gmail.com";
        Long newPhoneNumber = 9876543210L;

        verify(fetchAccount).findUser(username);
        verify(fetchAccount).update(username, newPassword, new Date, newAddress, newEmail, newPhoneNumber);
    }


    // Helper method
    private String getConsoleOutput() {
        return null;
    }

}
