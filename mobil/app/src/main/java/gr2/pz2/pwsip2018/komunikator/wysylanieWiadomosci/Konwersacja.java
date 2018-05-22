package gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci;

import android.os.StrictMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Konwersacja
{
    public String login;
    public String adresat;
    private static final String DANE_BAZY ="jdbc:mysql://192.168.43.185:3306/komunikator?useUnicode=yes&characterEncoding=utf-8";//ze względu na brak serwera trzeba za każdym razem zmienić IP na IP komputera z bazą danych
    private static final String UZYTKOWNIK_BAZY ="root";
    private static final String HASLO_BAZY ="";
    private int liczbaWszystkichWiadomosciNaPoczatku;
    private int zaladowaneWiadomosci;
    public String login1;
    public String login2;

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

    public static String znajdzLoginIHaslo(String login,String haslo) throws SQLException
    {
        przygotujDoPolaczeniaZBaza();
        String nazwaUser = null;
        Connection polaczenie;
        Statement st;
        polaczenie= DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        st=polaczenie.createStatement();
        ResultSet wynik=st.executeQuery("select login from uzytkownicy where login='" + login + "' and haslo='" + haslo + "'");
        while (wynik.next())
        {
            nazwaUser=wynik.getString("login");
        }
        return nazwaUser;
    }

    public static String znajdzEmailUzytkownika(String login) throws SQLException
    {
        przygotujDoPolaczeniaZBaza();
        String id = null;
        Connection polaczenie;
        Statement st;
        polaczenie= DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        st=polaczenie.createStatement();
        ResultSet wynik=st.executeQuery("select Email from uzytkownicy where login='" + login + "'");
        while (wynik.next())
        {
            id=wynik.getString("Email");
        }
        return id;
    }

    public static void dodajUzytkownika(String nazwauser, String email, String password) throws SQLException
    {
        przygotujDoPolaczeniaZBaza();
        Connection polaczenie;
        Statement st;
        polaczenie= DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        st=polaczenie.createStatement();
        st.executeUpdate("insert into uzytkownicy values (null,'" + nazwauser + "','dostępny','" + email + "','" + password + "')");

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

    public static Uzytkownik znajdzDaneUzytkownikaPoId(String id) throws SQLException
    {
        przygotujDoPolaczeniaZBaza();
        Uzytkownik uzytkownik=null;
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        ResultSet wynik=st.executeQuery("select login, status from uzytkownicy where idUzytkownika='" + id + "'");
        while (wynik.next())
        {
            uzytkownik=new Uzytkownik(id, wynik.getString("login"),wynik.getString("status"));
        }
        return uzytkownik;
    }

    public static ArrayList<Kontakt> zaladujKontakty(String login) throws SQLException
    {
        przygotujDoPolaczeniaZBaza();
        ArrayList<Kontakt> kontakty = new ArrayList<Kontakt>();
        String id = znajdzIdUzytkownika(login);
        Connection polaczenie = DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        ResultSet wynik=st.executeQuery("select idUzytkownika1, idUzytkownika2 from kontakty where idUzytkownika1=" + id + " or idUzytkownika2=" + id);
        while(wynik.next())
        {
            Uzytkownik uzytkownik=null;
            if(wynik.getString("idUzytkownika1").equals(id))
            {
                uzytkownik=znajdzDaneUzytkownikaPoId(wynik.getString("idUzytkownika2"));
            }
            else
            {
                uzytkownik=znajdzDaneUzytkownikaPoId(wynik.getString("idUzytkownika1"));
            }
            kontakty.add(new Kontakt(uzytkownik.login, !"niewidoczny".equals(uzytkownik.status) ? uzytkownik.status : "niedostępny"));
        }
        return kontakty;
    }

    public ArrayList<Wiadomosc> wczytajWiadomosci() throws SQLException
    {
        ArrayList<Wiadomosc> wiadomosci = new ArrayList<Wiadomosc>();
        int liczbaWiadomosci=0;
        przygotujDoPolaczeniaZBaza();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        ResultSet wynik=st.executeQuery("SELECT count(*) from wiadomosci where (idWysylajacego=" + znajdzIdUzytkownika(login) + " and idAdresata="+ znajdzIdUzytkownika(adresat)
                + ") or (idWysylajacego=" + znajdzIdUzytkownika(adresat) + " and idAdresata=" + znajdzIdUzytkownika(login)+ ") order by idWiadomosci");
        while(wynik.next())
        {
            liczbaWiadomosci = Integer.parseInt(wynik.getString("count(*)"));
        }
        liczbaWszystkichWiadomosciNaPoczatku = liczbaWiadomosci;
        if(liczbaWszystkichWiadomosciNaPoczatku>10) {
            wynik = st.executeQuery("SELECT idWysylajacego, tresc, data from wiadomosci where (idWysylajacego=" + znajdzIdUzytkownika(login) + " and idAdresata="
                    + znajdzIdUzytkownika(adresat) + ") or (idWysylajacego=" + znajdzIdUzytkownika(adresat) + " and idAdresata=" + znajdzIdUzytkownika(login)
                    + ") order by idWiadomosci limit 10 offset " + (liczbaWiadomosci - 10));
        }
        else
        {
            wynik = st.executeQuery("SELECT idWysylajacego, tresc, data from wiadomosci where (idWysylajacego=" + znajdzIdUzytkownika(login) + " and idAdresata="
                    + znajdzIdUzytkownika(adresat) + ") or (idWysylajacego=" + znajdzIdUzytkownika(adresat) + " and idAdresata=" + znajdzIdUzytkownika(login)
                    + ") order by idWiadomosci");
        }
        if(liczbaWszystkichWiadomosciNaPoczatku>10)
        {
            zaladowaneWiadomosci = 10;
        }
        else
        {
            zaladowaneWiadomosci=liczbaWszystkichWiadomosciNaPoczatku;
        }
        while(wynik.next())
        {
            wiadomosci.add(new Wiadomosc(wynik.getString("tresc"), znajdzUzytkownikaPoId(wynik.getString("idWysylajacego")), wynik.getString("data")));
        }
        st.executeUpdate("update wiadomosci set wyswietlona=1 where idWysylajacego=" + znajdzIdUzytkownika(adresat) + " and idAdresata="+ znajdzIdUzytkownika(login));
        return wiadomosci;
    }

    public String wyslijWiadomosc(String tresc) throws SQLException
    {
        String czasWiadomosci=null;
        przygotujDoPolaczeniaZBaza();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        st.executeUpdate("insert into wiadomosci values (null, " + znajdzIdUzytkownika(login) + ", " + znajdzIdUzytkownika(adresat) + ", '" + tresc + "', now(), 0)");
        ResultSet czasSerwera=st.executeQuery("select data from wiadomosci where idWysylajacego=" + znajdzIdUzytkownika(login) + " order by idWiadomosci desc limit 1");
        while (czasSerwera.next())
        {
            czasWiadomosci=czasSerwera.getString("data");
        }
        return czasWiadomosci;
    }

    public static void zapiszStatusUzytkownika(String idUzytkownika, String status) throws SQLException
    {
        String czasWiadomosci=null;
        przygotujDoPolaczeniaZBaza();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        st.executeUpdate("update uzytkownicy set status='"+status+"' where idUzytkownika="+idUzytkownika);


    }

    public ArrayList<Wiadomosc> odswiezKonwersacje() throws SQLException
    {
        ArrayList<Wiadomosc> wiadomosci = new ArrayList<Wiadomosc>();
        przygotujDoPolaczeniaZBaza();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        ResultSet wynikZero=st.executeQuery("select count(*) from wiadomosci where idAdresata=" + znajdzIdUzytkownika(login) + " and wyswietlona=0 and idWysylajacego="
                + znajdzIdUzytkownika(adresat) + " order by idWiadomosci");
        while(wynikZero.next())
        {
            if(wynikZero.getString("count(*)").equals("0"))
            {
                return wiadomosci;
            }
        }
        ResultSet wynik=st.executeQuery("select idWiadomosci, tresc, data from wiadomosci where idAdresata=" + znajdzIdUzytkownika(login) + " and wyswietlona=0 and idWysylajacego="
                + znajdzIdUzytkownika(adresat) + " order by idWiadomosci");
        String najwiekszeId=null;
        while (wynik.next())
        {
            wiadomosci.add(new Wiadomosc(wynik.getString("tresc"), adresat, wynik.getString("data")));
            najwiekszeId=wynik.getString("idWiadomosci");
        }
        st.executeUpdate("update wiadomosci set wyswietlona=1 where idAdresata=" + znajdzIdUzytkownika(login) + " and wyswietlona=0 and idWysylajacego=" + znajdzIdUzytkownika(adresat)+" and idWiadomosci<="+najwiekszeId);
        return wiadomosci;
    }

    public static Boolean znajdzUzytkownika(String szukanyLogin) throws SQLException
    {
        przygotujDoPolaczeniaZBaza();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        ResultSet wynik=st.executeQuery("select count(*) from uzytkownicy where login='" + szukanyLogin + "'");
        while(wynik.next())
        {
            if (wynik.getString("count(*)").equals("0"))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        return false;
    }

    public static void dodajKontakt(String login1, String login2) throws SQLException
    {
        przygotujDoPolaczeniaZBaza();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        st.executeUpdate("insert into kontakty values(null, " + znajdzIdUzytkownika(login1) + ", " + znajdzIdUzytkownika(login2) + ", 'nie')");
    }

    public static void zablokujKontakt(String login1, String login2) throws SQLException {

        przygotujDoPolaczeniaZBaza();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        st.executeUpdate("update kontakty set zablokowany = 'tak' where (idUzytkownika1=" + znajdzIdUzytkownika(login1) + " and idUzytkownika2=" + znajdzIdUzytkownika(login2) + ") or (idUzytkownika1=" + znajdzIdUzytkownika(login2)
                + " and idUzytkownika2=" + znajdzIdUzytkownika(login1) + ")");
    }

    public static void odblokujKontakt(String login1, String login2) throws SQLException {

        przygotujDoPolaczeniaZBaza();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        st.executeUpdate("update kontakty set zablokowany = 'nie' where (idUzytkownika1=" + znajdzIdUzytkownika(login1) + " and idUzytkownika2=" + znajdzIdUzytkownika(login2) + ") or (idUzytkownika1=" + znajdzIdUzytkownika(login2)
                + " and idUzytkownika2=" + znajdzIdUzytkownika(login1) + ")");
    }

    public static String czyZablokowany(String login1, String login2) throws SQLException {
        String zablokowany = null;

        przygotujDoPolaczeniaZBaza();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        ResultSet wynikZapytania=st.executeQuery("select zablokowany from kontakty where (idUzytkownika1=" + znajdzIdUzytkownika(login1) + " and idUzytkownika2=" + znajdzIdUzytkownika(login2)+ ") or (idUzytkownika1=" + znajdzIdUzytkownika(login2)
                + " and idUzytkownika2=" + znajdzIdUzytkownika(login1) + ")");
        while (wynikZapytania.next())
        {
            zablokowany = wynikZapytania.getString("zablokowany");
        }

        return zablokowany;
    }

    public static Boolean sprawdzCzySaNoweWiadomosci(String login) throws SQLException
    {
        String wynikZapytania=null;
        przygotujDoPolaczeniaZBaza();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        ResultSet wynik=st.executeQuery("select count(*) from wiadomosci where idAdresata=" + znajdzIdUzytkownika(login) + " and wyswietlona=0");
        while (wynik.next())
        {
            wynikZapytania=wynik.getString("count(*)");
        }
        return !wynikZapytania.equals("0");
    }

    public static HashMap<String, Integer> wyswietlPowiadomieniaONowychWiadomosciach(String login) throws SQLException
    {
        String id = znajdzIdUzytkownika(login);
        HashMap<String, Integer> nieodczytaneWiadomosci = new HashMap<String, Integer>();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        ResultSet wynik=st.executeQuery("select distinct(w1.idWysylajacego), (SELECT count(*) from wiadomosci w2 where w2.idWysylajacego = w1.idWysylajacego"
                + " and w2.wyswietlona = 0 and w2.idAdresata=" + id + ") as niewyswietlone from wiadomosci w1 where w1.idAdresata = " + id + " and w1.wyswietlona = 0");
        while (wynik.next())
        {
            nieodczytaneWiadomosci.put(znajdzUzytkownikaPoId(wynik.getString("idWysylajacego")), Integer.valueOf(Integer.parseInt(wynik.getString("niewyswietlone"))));
        }
        return nieodczytaneWiadomosci;
    }

    public ArrayList<Wiadomosc> zaladujWczesniejszeWiadomosci() throws SQLException
    {
        ArrayList<Wiadomosc> wiadomosci = new ArrayList<Wiadomosc>();
        przygotujDoPolaczeniaZBaza();
        Connection polaczenie=DriverManager.getConnection(DANE_BAZY, UZYTKOWNIK_BAZY, HASLO_BAZY);
        Statement st=polaczenie.createStatement();
        ResultSet wynik;
        if(zaladowaneWiadomosci+10<liczbaWszystkichWiadomosciNaPoczatku) {
            zaladowaneWiadomosci += 10;
            wynik = st.executeQuery("SELECT idWysylajacego, tresc, data from wiadomosci where (idWysylajacego=" + znajdzIdUzytkownika(login) + " and idAdresata="
                    + znajdzIdUzytkownika(adresat) + ") or (idWysylajacego=" + znajdzIdUzytkownika(adresat) + " and idAdresata=" + znajdzIdUzytkownika(login)
                    + ") order by idWiadomosci limit 10 offset " + (liczbaWszystkichWiadomosciNaPoczatku - zaladowaneWiadomosci));
        }
        else
        {
            int liczbaNajstarszychWiadomosci=liczbaWszystkichWiadomosciNaPoczatku-zaladowaneWiadomosci;
            zaladowaneWiadomosci=liczbaWszystkichWiadomosciNaPoczatku;
            wynik = st.executeQuery("SELECT idWysylajacego, tresc, data from wiadomosci where (idWysylajacego=" + znajdzIdUzytkownika(login) + " and idAdresata="
                    + znajdzIdUzytkownika(adresat) + ") or (idWysylajacego=" + znajdzIdUzytkownika(adresat) + " and idAdresata=" + znajdzIdUzytkownika(login)
                    + ") order by idWiadomosci limit "+liczbaNajstarszychWiadomosci+" offset 0");
        }
        while (wynik.next())
        {
            wiadomosci.add(new Wiadomosc(wynik.getString("tresc"), znajdzUzytkownikaPoId(wynik.getString("idWysylajacego")), wynik.getString("data")));
        }
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

    public static class Kontakt
    {
        public String login;
        public int nieodczytaneWiadomosci;
        public String status;

        public Kontakt(String login, String status)
        {
            this.login=login;
            this.status=status;
        }

        @Override
        public String toString()
        {
            String kontaktInfo = login;
            if (nieodczytaneWiadomosci > 0)
            {
                kontaktInfo = kontaktInfo + " (" + nieodczytaneWiadomosci + ")";
            }
            kontaktInfo = kontaktInfo + " " + status;
            return kontaktInfo;
        }
    }
    public static class Uzytkownik
    {
        public String id;
        public String login;
        public String status;
        public Uzytkownik(String id, String login, String status)
        {
            this.id=id;
            this.login=login;
            this.status=status;
        }
    }
}
