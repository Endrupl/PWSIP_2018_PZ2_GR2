﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data;
using MySql.Data.MySqlClient;

namespace komunikator
{
    namespace wysylanieWiadomosci
    {
        class Konwersacja
        {
            private int wczytaneWiadomosci;
            public string login;
            public string adresat;
            private const string daneBazy = "Server=localhost; database=komunikator; UID=root; password=";

            public Konwersacja(string login, string adresat)
            {
                wczytaneWiadomosci = 0;
                this.login = login;
                this.adresat = adresat;
            }

            public string znajdzIdUzytkownika(string login)
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

            public string znajdzUzytkownikaPoId(string id)
            {
                string uzytkownik = null;
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
                string czasWiadomosci = null;
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand polecenie = polaczenie.CreateCommand();
                    polecenie.CommandText = "insert into wiadomosci values (null, " + znajdzIdUzytkownika(login) + ", " + znajdzIdUzytkownika(adresat) +
                        ", '" + tresc + "', now())";
                    polecenie.ExecuteReader().Close();
                    MySqlCommand czasSerwera = polaczenie.CreateCommand();
                    czasSerwera.CommandText = "select data from wiadomosci where idWysylajacego=" + znajdzIdUzytkownika(login) + " order by idWiadomosci desc limit 1";
                    MySqlDataReader wynik = czasSerwera.ExecuteReader();
                    while (wynik.Read())
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
                    polecenie.CommandText = "SELECT idWysylajacego, tresc, data from wiadomosci where (idWysylajacego=" + znajdzIdUzytkownika(login) + " and idAdresata="
                        + znajdzIdUzytkownika(adresat) + ") or (idWysylajacego=" + znajdzIdUzytkownika(adresat) + " and idAdresata=" + znajdzIdUzytkownika(login) + ") order by data";
                    MySqlDataReader wynik = polecenie.ExecuteReader();
                    while (wynik.Read())
                    {
                        wiadomosci.Add(new Wiadomosc
                        {
                            uzytkownik = znajdzUzytkownikaPoId(wynik["idWysylajacego"].ToString()),
                            data = wynik["data"].ToString(),
                            tresc = wynik["tresc"].ToString()
                        });
                    }
                }
                return wiadomosci;
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
