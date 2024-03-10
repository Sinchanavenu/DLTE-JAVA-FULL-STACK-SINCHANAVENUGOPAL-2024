package basic.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    MyBank myBank;
    static List<Loan> loans= new ArrayList<>();
    @Before
    public void setUp() {
        myBank = new LoanFinal();
    }
    @BeforeClass
    public static void initialize(){
        loans.add(new Loan(97358L,50000L,"2/24/2024","closed","Ninadha",7022916867L));
        loans.add(new Loan(12356L,25000L,"1/20/2024","open","Sinchana",7338296738L));
    }

    @Test
    public void testData(){
        assertEquals("Ninadha",loans.get(0).getBorrowerName());
    }
    @Test
    public void testIsEmptyList(){
        assertNotNull(loans);
    }

    @Test
    public void testCheckOpenLoan(){
        assertEquals("open",loans.get(1).getLoanStatus());
    }

    @Test(timeout=3000)
    public void testTimeout() throws InterruptedException {
        Thread.sleep(90);
        assertTrue(loans.size()>0);
    }

    @Test
    public void testCheckClosedLoans() {
        assertEquals("closed",loans.get(0).getLoanStatus());
    }

}
