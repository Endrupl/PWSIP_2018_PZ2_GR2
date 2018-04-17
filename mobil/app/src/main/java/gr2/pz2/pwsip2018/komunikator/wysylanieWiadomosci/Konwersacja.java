package gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci;

import android.os.StrictMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static String znajdzIdUzytkownika(String login) throws SQLException
    {
        przygotujDoPolaczeniaZBaza();
        String id = null;
        Connection polaczenie;
        Statement st;
        polaczenie= DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        st=polaczenie.createStatement();
        ResultSet wynik=st.executeQuery("select idUzytkownika from uzytkownicy where login='" + login + "'");
        while (wynik.next())
        {
            id=wynik.getString("idUzytkownika");
        }
        return id;
    }

    public static String znajdzUzytkownikaPoId(String id) throws SQLException
    {
        przygotujDoPolaczeniaZBaza();
        String uzytkownik=null;
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        ResultSet wynik=st.executeQuery("select login from uzytkownicy where idUzytkownika='" + id + "'");
        while (wynik.next())
        {
            uzytkownik=wynik.getString("login");
        }
        return uzytkownik;
    }

    public static ArrayList<String> zaladujKontakty(String login) throws SQLException
    {
        przygotujDoPolaczeniaZBaza();
        ArrayList<String> kontakty = new ArrayList<String>();
        String id = znajdzIdUzytkownika(login);
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
        return kontakty;
    }

    public ArrayList<Wiadomosc> wczytajWiadomosci() throws SQLException
    {
        ArrayList<Wiadomosc> wiadomosci = new ArrayList<Wiadomosc>();
        przygotujDoPolaczeniaZBaza();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        ResultSet wynik=st.executeQuery("SELECT idWysylajacego, tresc, data from wiadomosci where (idWysylajacego="+znajdzIdUzytkownika(login)+" and idAdresata="
                +znajdzIdUzytkownika(adresat)+") or (idWysylajacego="+znajdzIdUzytkownika(adresat)+" and idAdresata="+znajdzIdUzytkownika(login)+") order by idWiadomosci");
        while(wynik.next())
        {
            wiadomosci.add(new Wiadomosc(wynik.getString("tresc"), znajdzUzytkownikaPoId(wynik.getString("idWysylajacego")), wynik.getString("data")));
        }
        st.executeQuery("update wiadomosci set wyswietlona=1 where idWysylajacego=" + znajdzIdUzytkownika(adresat) + " and idAdresata="+ znajdzIdUzytkownika(login));
        //zmienStatusWiadomosci();
        //ResultSet polecenieZmianyStatusuWiadomosci=st.executeQuery("update wiadomosci set wyswietlona=1 where idWysylajacego=" + znajdzIdUzytkownika(adresat) + " and idAdresata="
        //        + znajdzIdUzytkownika(login));
        //using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
        //{
        //    polaczenie.Open();
        //    MySqlCommand polecenie = polaczenie.CreateCommand();
        //    polecenie.CommandText = "SELECT idWysylajacego, tresc, data from wiadomosci where (idWysylajacego="+znajdzIdUzytkownika(login)+" and idAdresata="
        //            +znajdzIdUzytkownika(adresat)+") or (idWysylajacego="+znajdzIdUzytkownika(adresat)+" and idAdresata="+znajdzIdUzytkownika(login)
        //            +") order by idWiadomosci";
        //    MySqlDataReader wynik = polecenie.ExecuteReader();
        //    while(wynik.Read())
        //    {
        //        wiadomosci.Add(new Wiadomosc { uzytkownik = znajdzUzytkownikaPoId(wynik["idWysylajacego"].ToString()), data = wynik["data"].ToString(),
        //            tresc = wynik["tresc"].ToString() });
        //    }
        //    wynik.Close();
        //    MySqlCommand polecenieZmianyStatusuWiadomosci = polaczenie.CreateCommand();
        //    polecenieZmianyStatusuWiadomosci.CommandText = "update wiadomosci set wyswietlona=1 where idWysylajacego=" + znajdzIdUzytkownika(adresat)
        //            + " and idAdresata="+ znajdzIdUzytkownika(login);
        //    polecenieZmianyStatusuWiadomosci.ExecuteReader();
        //}
        return wiadomosci;
    }

    public static class Wiadomosc
    {
        public String tresc;
        public String uzytkownik;
        public String data;

        public Wiadomosc(String tresc, String uzytkownik, String data)
        {
            this.tresc=tresc;
            this.uzytkownik=uzytkownik;
            this.data=data;
        }
    }
}
