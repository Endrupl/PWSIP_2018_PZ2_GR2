package gr2.pz2.pwsip2018.komunikator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.sql.SQLException;
import gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci.Konwersacja;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("gr2.pz2.pwsip2018.komunikator", appContext.getPackageName());
    }

    @Test
    public void znajdzIdUzytkownikaTest() throws SQLException
    {
        String wynik1 = Konwersacja.znajdzIdUzytkownika("uzytkownik1");
        String wynik2 = Konwersacja.znajdzIdUzytkownika("uzytkownik2");
        String wynik3 = Konwersacja.znajdzIdUzytkownika("uzytkownik3");
        assertEquals("1", wynik1);
        assertEquals("2", wynik2);
        assertEquals("3", wynik3);
    }

    @Test
    public void  znajdzUzytkownikaPoIdTest() throws SQLException
    {
        String wynik1 = Konwersacja.znajdzUzytkownikaPoId("1");
        String wynik2 = Konwersacja.znajdzUzytkownikaPoId("2");
        String wynik3 = Konwersacja.znajdzUzytkownikaPoId("3");
        assertEquals("uzytkownik1", wynik1);
        assertEquals("uzytkownik2", wynik2);
        assertEquals("uzytkownik3", wynik3);
    }

    @Test
    public void znajdzUzytkownikaTest() throws SQLException
    {
        Boolean wynik1 = Konwersacja.znajdzUzytkownika("uzytkownik1");
        Boolean wynik2 = Konwersacja.znajdzUzytkownika("uzytkownik2");
        Boolean wynik3 = Konwersacja.znajdzUzytkownika("uzytkownik3");
        Boolean wynik4 = Konwersacja.znajdzUzytkownika("dgfdgsfgs");
        assertEquals(true, wynik1);
        assertEquals(true, wynik2);
        assertEquals(true, wynik3);
        assertEquals(false, wynik4);
    }

    @Test
    public void sprawdzCzySaNoweWiadomosciTest() throws SQLException
    {
        Konwersacja k1 = new Konwersacja("uzytkownik1", "uzytkownik2");
        k1.wyslijWiadomosc("TEST");
        Boolean wynik = Konwersacja.sprawdzCzySaNoweWiadomosci("uzytkownik2");
        assertEquals(true, wynik);
    }
}
