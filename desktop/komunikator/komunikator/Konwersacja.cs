using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data;
using MySql.Data.MySqlClient;
using System.Windows;

namespace komunikator
{
    namespace wysylanieWiadomosci
    {
        class Konwersacja
        {
            public string login;
            public string adresat;
            private const string daneBazy= "Server=localhost; database=komunikator; UID=root; password=";

            public Konwersacja(string login, string adresat)
            {
                this.login = login;
                this.adresat = adresat;
            }

            private static string znajdzIdUzytkownika(string login)
            {
                string id = null;
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand zapytanie = polaczenie.CreateCommand();
                    zapytanie.CommandText = "select idUzytkownika from uzytkownicy where login='" + login + "'";
                    MySqlDataReader wynik = zapytanie.ExecuteReader();
                    while (wynik.Read())
                    {
                        id = wynik["idUzytkownika"].ToString();
                    }
                    wynik.Close();
                    wynik.Dispose();
                    zapytanie.Dispose();
                }
                return id;
            }

            private static string znajdzUzytkownikaPoId(string id)
            {
                string uzytkownik=null;
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand zapytanie = polaczenie.CreateCommand();
                    zapytanie.CommandText = "select login from uzytkownicy where idUzytkownika='" + id + "'";
                    MySqlDataReader wynik = zapytanie.ExecuteReader();
                    while (wynik.Read())
                    {
                        uzytkownik = wynik["login"].ToString();
                    }
                    wynik.Close();
                    wynik.Dispose();
                    zapytanie.Dispose();
                }
                return uzytkownik;
            }

            public string wyslijWiadomosc(string tresc)
            {
                string czasWiadomosci=null;
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand polecenie = polaczenie.CreateCommand();
                    polecenie.CommandText = "insert into wiadomosci values (null, " + znajdzIdUzytkownika(login) + ", " + znajdzIdUzytkownika(adresat) +
                        ", '" + tresc + "', now(), 0)";
                    polecenie.ExecuteReader().Close();
                    MySqlCommand czasSerwera = polaczenie.CreateCommand();
                    czasSerwera.CommandText = "select data from wiadomosci where idWysylajacego=" + znajdzIdUzytkownika(login) + " order by idWiadomosci desc limit 1";
                    MySqlDataReader wynik = czasSerwera.ExecuteReader();
                    while(wynik.Read())
                    {
                        czasWiadomosci = wynik["data"].ToString();
                    }
                }
                return czasWiadomosci;  
            }

            public List<Wiadomosc> wczytajWiadomosci()
            {
                List<Wiadomosc> wiadomosci = new List<Wiadomosc>(); 
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand polecenie = polaczenie.CreateCommand();
                    polecenie.CommandText = "SELECT idWysylajacego, tresc, data from wiadomosci where (idWysylajacego="+znajdzIdUzytkownika(login)+" and idAdresata="
                        +znajdzIdUzytkownika(adresat)+") or (idWysylajacego="+znajdzIdUzytkownika(adresat)+" and idAdresata="+znajdzIdUzytkownika(login)
                        +") order by idWiadomosci";
                    MySqlDataReader wynik = polecenie.ExecuteReader();
                    while(wynik.Read())
                    {
                        wiadomosci.Add(new Wiadomosc { uzytkownik = znajdzUzytkownikaPoId(wynik["idWysylajacego"].ToString()), data = wynik["data"].ToString(),
                            tresc = wynik["tresc"].ToString() });
                    }
                    wynik.Close();
                    MySqlCommand polecenieZmianyStatusuWiadomosci = polaczenie.CreateCommand();
                    polecenieZmianyStatusuWiadomosci.CommandText = "update wiadomosci set wyswietlona=1 where idWysylajacego=" + znajdzIdUzytkownika(adresat) 
                        + " and idAdresata="+ znajdzIdUzytkownika(login);
                    polecenieZmianyStatusuWiadomosci.ExecuteReader();
                }
                return wiadomosci;
            }

            public bool sprawdzCzySaNoweWiadomosci()
            {
                string wynikZapytania=null;
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand zapytanie = polaczenie.CreateCommand();
                    zapytanie.CommandText = "select count(*) from wiadomosci where idAdresata=" + znajdzIdUzytkownika(login) + " and wyswietlona=0";
                    MySqlDataReader wynik = zapytanie.ExecuteReader();
                    while(wynik.Read())
                    {
                        wynikZapytania = wynik["count(*)"].ToString();
                    }
                }
                return !wynikZapytania.Equals("0");
            }

            public static bool sprawdzCzySaNoweWiadomosci(string login)
            {
                string wynikZapytania = null;
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand zapytanie = polaczenie.CreateCommand();
                    zapytanie.CommandText = "select count(*) from wiadomosci where idAdresata=" + znajdzIdUzytkownika(login) + " and wyswietlona=0";
                    MySqlDataReader wynik = zapytanie.ExecuteReader();
                    while (wynik.Read())
                    {
                        wynikZapytania = wynik["count(*)"].ToString();
                    }
                }
                return !wynikZapytania.Equals("0");
            }

            public List<Wiadomosc> odswiezKonwersacje()
            {
                List<Wiadomosc> wiadomosci = new List<Wiadomosc>();
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand zapytanie = polaczenie.CreateCommand();
                    zapytanie.CommandText = "select tresc, data from wiadomosci where idAdresata=" + znajdzIdUzytkownika(login) + " and wyswietlona=0 and idWysylajacego="
                        + znajdzIdUzytkownika(adresat) + " order by idWiadomosci";
                    MySqlDataReader wynik = zapytanie.ExecuteReader();
                    while(wynik.Read())
                    {
                        wiadomosci.Add(new Wiadomosc
                        {
                            uzytkownik = adresat,
                            data = wynik["data"].ToString(),
                            tresc = wynik["tresc"].ToString()
                        });
                    }
                    wynik.Close();
                    MySqlCommand polecenie = polaczenie.CreateCommand();
                    polecenie.CommandText = "update wiadomosci set wyswietlona=1 where idAdresata=" + znajdzIdUzytkownika(login) + " and wyswietlona=0 and idWysylajacego="
                        + znajdzIdUzytkownika(adresat);
                    polecenie.ExecuteReader();
                }
                return wiadomosci;
            }

            public static bool znajdzUzytkownika(string szukanyLogin)
            {
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand zapytanie = polaczenie.CreateCommand();
                    zapytanie.CommandText = "select count(*) from uzytkownicy where login='" + szukanyLogin + "'";
                    MySqlDataReader wynik = zapytanie.ExecuteReader();
                    while(wynik.Read())
                    {
                        if(wynik["count(*)"].ToString().Equals("0"))
                        {
                            return false;
                        }
                        else
                        {
                            return true;
                        }
                    }
                }
                return false;
            }

            public static void dodajKontakt(string login1, string login2)
            {
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand polecenie = polaczenie.CreateCommand();
                    polecenie.CommandText = "insert into kontakty values(null, " + znajdzIdUzytkownika(login1) + ", " + znajdzIdUzytkownika(login2) + ")";
                    polecenie.ExecuteReader();
                }
            }

            public static List<string> zaladujKontakty(string login)
            {
                List<string> kontakty = new List<string>();
                string id = znajdzIdUzytkownika(login);
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand zapytanie = polaczenie.CreateCommand();
                    zapytanie.CommandText = "select idUzytkownika1, idUzytkownika2 from kontakty where idUzytkownika1=" + id + " or idUzytkownika2=" + id;
                    MySqlDataReader wynik = zapytanie.ExecuteReader();
                    while(wynik.Read())
                    {
                        if(wynik["idUzytkownika1"].ToString().Equals(id))
                        {
                            kontakty.Add(znajdzUzytkownikaPoId(wynik["idUzytkownika2"].ToString()));
                        }
                        else
                        {
                            kontakty.Add(znajdzUzytkownikaPoId(wynik["idUzytkownika1"].ToString()));
                        }
                    }
                }
                return kontakty;
            }

            public static void usunKontakt(string login1, string login2)
            {
                string id1 = znajdzIdUzytkownika(login1);
                string id2 = znajdzIdUzytkownika(login2);
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand polecenie = polaczenie.CreateCommand();
                    polecenie.CommandText = "delete from kontakty where (idUzytkownika1=" + id1 + " and idUzytkownika2=" + id2 + ") or (idUzytkownika1=" + id2
                        + " and idUzytkownika2=" + id1 + ")";
                    polecenie.ExecuteReader();
                }
            }

            public static Dictionary<string, int> wyswietlPowiadomieniaONowychWiadomosciach(string login)
            {
                string id = znajdzIdUzytkownika(login);
                Dictionary<string, int> nieodczytaneWiadomosci = new Dictionary<string, int>();
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand zapytanie = polaczenie.CreateCommand();
                    zapytanie.CommandText = "select distinct(w1.idWysylajacego), (SELECT count(*) from wiadomosci w2 where w2.idWysylajacego = w1.idWysylajacego" 
                        + "and w2.wyswietlona = 0) as niewyswietlone from wiadomosci w1 where w1.idAdresata = " + id + " and w1.wyswietlona = 0";
                    MySqlDataReader wynik = zapytanie.ExecuteReader();
                    while(wynik.Read())
                    {
                        nieodczytaneWiadomosci.Add(znajdzUzytkownikaPoId(wynik["idWysylajacego"].ToString()), int.Parse(wynik["niewyswietlona"].ToString()));
                    }
                    return nieodczytaneWiadomosci;
                }
            }

            public class Wiadomosc
            {
                public string tresc { get; set; }
                public string uzytkownik { get; set; }
                public string data { get; set; }
            }
        }
    }
    
}
