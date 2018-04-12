package gr2.pz2.pwsip2018.komunikator;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci.Konwersacja;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void znajdzIdUzytkownikaTest()
    {
        String wynik1= Konwersacja.znajdzIdUzytkownika("uzytkownik1");
        String wynik2= Konwersacja.znajdzIdUzytkownika("uzytkownik2");
        String wynik3= Konwersacja.znajdzIdUzytkownika("uzytkownik3");
        assertEquals("1", wynik1);
        assertEquals("2", wynik2);
        assertEquals("3", wynik3);
    }

    @Test
    public void  znajdzUzytkownikaPoIdTest()
    {
        String wynik1=Konwersacja.znajdzUzytkownikaPoId("1");
        String wynik2=Konwersacja.znajdzUzytkownikaPoId("2");
        String wynik3=Konwersacja.znajdzUzytkownikaPoId("3");
        assertEquals("uzytkownik1", wynik1);
        assertEquals("uzytkownik2", wynik2);
        assertEquals("uzytkownik3", wynik3);
    }
}