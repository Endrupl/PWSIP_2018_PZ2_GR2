using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using komunikator.wysylanieWiadomosci;
using MySql.Data.MySqlClient;

namespace TDDWysylanieWiadomosci
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void znajdzIdUzytkownikaTest()
        {
            Konwersacja k = new Konwersacja(null);
            string wynik1 = k.znajdzIdUzytkownika("uzytkownik1");
            string wynik2 = k.znajdzIdUzytkownika("uzytkownik2");
            string wynik3 = k.znajdzIdUzytkownika("uzytkownik3");
            Assert.AreEqual("1", wynik1);
            Assert.AreEqual("2", wynik2);
            Assert.AreEqual("3", wynik3);
        }

        [TestMethod]
        public void wyslijWiadomoscTest()
        {
            Konwersacja k = new Konwersacja("uzytkownik1");
            k.wyslijWiadomosc("TEST", "uzytkownik2");
            MySqlConnection polaczenie = new MySqlConnection("Server=localhost; database=komunikator; UID=root; password=");
            polaczenie.Open();
            MySqlCommand zapytanie = polaczenie.CreateCommand();
            zapytanie.CommandText = "select * from wiadomosci order by idWiadomosci desc limit 1";
            string wynikTresc=null;
            MySqlDataReader wynik = zapytanie.ExecuteReader();
            while (wynik.Read())
            {
                wynikTresc = wynik["tresc"].ToString();
            }
            wynik.Close();
            polaczenie.Close();
            Assert.AreEqual("TEST", wynikTresc);
        }
    }
}
