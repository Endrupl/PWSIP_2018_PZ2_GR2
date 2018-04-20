package gr2.pz2.pwsip2018.komunikator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    public void znajdzIdUzytkownikaTest()
    {
        String wynik1=null;
        String wynik2=null;
        String wynik3=null;
        try
        {
            wynik1 = Konwersacja.znajdzIdUzytkownika("uzytkownik1");
            wynik2 = Konwersacja.znajdzIdUzytkownika("uzytkownik2");
            wynik3 = Konwersacja.znajdzIdUzytkownika("uzytkownik3");
        }
        catch (Exception e){}
        assertEquals("1", wynik1);
        assertEquals("2", wynik2);
        assertEquals("3", wynik3);
    }

    @Test
    public void  znajdzUzytkownikaPoIdTest()
    {
        String wynik1=null;
        String wynik2=null;
        String wynik3=null;
        try
        {
            wynik1 = Konwersacja.znajdzUzytkownikaPoId("1");
            wynik2 = Konwersacja.znajdzUzytkownikaPoId("2");
            wynik3 = Konwersacja.znajdzUzytkownikaPoId("3");
        }
        catch (Exception e){}
        assertEquals("uzytkownik1", wynik1);
        assertEquals("uzytkownik2", wynik2);
        assertEquals("uzytkownik3", wynik3);
    }
}
