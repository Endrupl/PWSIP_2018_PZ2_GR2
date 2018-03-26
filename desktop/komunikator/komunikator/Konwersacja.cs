using System;
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
            private string login;
            private const string daneBazy= "Server=localhost; database=komunikator; UID=root; password=";

            public Konwersacja(string login)
            {
                wczytaneWiadomosci = 0;
                this.login = login;
            }

            private string znajdzIdUzytkownika(string login)
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

            public void wyslijWiadomosc(string tresc, string adresat)
            {
                using (MySqlConnection polaczenie = new MySqlConnection(daneBazy))
                {
                    polaczenie.Open();
                    MySqlCommand polecenie = polaczenie.CreateCommand();
                    polecenie.CommandText = "insert into wiadomosci values (null, " + znajdzIdUzytkownika(login) + ", " + znajdzIdUzytkownika(adresat) +
                        ", '" + tresc + "', now())";
                    polecenie.ExecuteReader();
                }  
            }

            public string[] wczytajWiadomosci(string uzytkownikWysylajacy, string adresat)
            {

                return null;
            }

            private class Wiadomosc
            {
                string tresc;
                DateTime data;
                TimeSpan godzina;
            }
        }
    }
    
}
