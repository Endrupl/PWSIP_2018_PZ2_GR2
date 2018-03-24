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
            private MySqlConnection polaczenie;

            public Konwersacja()
            {
                polaczZBaza();
                wczytaneWiadomosci = 0;
            }

            private void polaczZBaza()
            {
                polaczenie = new MySqlConnection("Server=localhost; database=komunikator; UID=root; password=");
                polaczenie.Open();
            }

            private string znajdzIdUzytkownika(string login)
            {
                MySqlCommand zapytanie = polaczenie.CreateCommand();
                zapytanie.CommandText = "select idUzytkownika from uzytkownicy where login='" + login + "'";
                MySqlDataReader wynik = zapytanie.ExecuteReader();
                string id = null;
                while (wynik.Read())
                {
                    id = wynik["idUzytkownika"].ToString();
                }
                wynik.Close();
                return id;
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
