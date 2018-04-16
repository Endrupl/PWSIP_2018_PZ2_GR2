package gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Konwersacja
{
    public String login;
    public String adresat;
    private static final String DANE_BAZY ="jdbc:mysql://192.168.1.16:3306/komunikator";//ze względu na brak serwera trzeba za każdym razem zmienić IP na IP komputera z bazą danych
    private static final String UZYTKOWNIK_BAZY ="root";
    private static final String HASLO_BAZY ="";

    public Konwersacja(String login, String adresat)
    {
        this.login = login;
        this.adresat = adresat;
    }

    private static void przygotujDoPolaczeniaZBaza()
    {
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e)
        {

        }
    }

    public static String znajdzIdUzytkownika(String login)
    {
        przygotujDoPolaczeniaZBaza();
        String id = null;
        Connection polaczenie;
        Statement st;
        try
        {
            polaczenie= DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
            st=polaczenie.createStatement();
            ResultSet wynik=st.executeQuery("select idUzytkownika from uzytkownicy where login='" + login + "'");
            while (wynik.next())
            {
                id=wynik.getString("idUzytkownika");
            }
        }
        catch(Exception e)
        {

        }
        return id;
    }

    public static String znajdzUzytkownikaPoId(String id)
    {
        przygotujDoPolaczeniaZBaza();
        String uzytkownik=null;
        try
        {
            Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
            Statement st=polaczenie.createStatement();
            ResultSet wynik=st.executeQuery("select login from uzytkownicy where idUzytkownika='" + id + "'");
            while (wynik.next())
            {
                uzytkownik=wynik.getString("login");
            }
        }
        catch (Exception e)
        {

        }
        return uzytkownik;
    }

    public static ArrayList<String> zaladujKontakty(String login)
    {
        przygotujDoPolaczeniaZBaza();
        ArrayList<String> kontakty = new ArrayList<String>();
        String id = znajdzIdUzytkownika(login);
        try
        {
            Connection polaczenie = DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
            Statement st=polaczenie.createStatement();
            ResultSet wynik=st.executeQuery("select idUzytkownika1, idUzytkownika2 from kontakty where idUzytkownika1=" + id + " or idUzytkownika2=" + id);
            while(wynik.next())
            {
                if(wynik.getString("idUzytkownika1").equals(id))
                {
                    kontakty.add(znajdzUzytkownikaPoId(wynik.getString("idUzytkownika2")));
                }
                else
                {
                    kontakty.add(znajdzUzytkownikaPoId(wynik.getString("idUzytkownika1")));
                }
            }
        }
        catch (Exception e)
        {
            ArrayList<String> f=new ArrayList<String>();
            f.add(e.getMessage());
            return f;
        }
        return kontakty;
    }
}
