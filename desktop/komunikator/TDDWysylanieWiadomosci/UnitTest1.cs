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
            Konwersacja k = new Konwersacja(null, null);
            string wynik1 = k.znajdzIdUzytkownika("uzytkownik1");
            string wynik2 = k.znajdzIdUzytkownika("uzytkownik2");
            string wynik3 = k.znajdzIdUzytkownika("uzytkownik3");
            Assert.AreEqual("1", wynik1);
            Assert.AreEqual("2", wynik2);
            Assert.AreEqual("3", wynik3);
        }

        [TestMethod]
        public void znajdzUzytkownikaPoIdtest()
        {
            Konwersacja k = new Konwersacja(null, null);
            string wynik1 = k.znajdzUzytkownikaPoId("1");
            string wynik2 = k.znajdzUzytkownikaPoId("2");
            string wynik3 = k.znajdzUzytkownikaPoId("3");
            Assert.AreEqual("uzytkownik1", wynik1);
            Assert.AreEqual("uzytkownik2", wynik2);
            Assert.AreEqual("uzytkownik3", wynik3);
        }

        [TestMethod]
        public void wyslijWiadomoscTest()
        {
            Konwersacja k = new Konwersacja("uzytkownik1", "uzytkownik2");
            k.wyslijWiadomosc("TEST");
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

        [TestMethod]
        public void wyslijWiadomoscTest2()
        {
            Konwersacja k = new Konwersacja("uzytkownik1", "uzytkownik2");
            string wynik=k.wyslijWiadomosc("TEST");
            MySqlConnection polaczenie = new MySqlConnection("Server=localhost; database=komunikator; UID=root; password=");
            polaczenie.Open();
            MySqlCommand zapytanie = polaczenie.CreateCommand();
            zapytanie.CommandText = "select data from wiadomosci where idWysylajacego=1 order by idWiadomosci desc limit 1";
            string wynikZapytania = null;
            MySqlDataReader odczytZapytania = zapytanie.ExecuteReader();
            while (odczytZapytania.Read())
            {
                wynikZapytania = odczytZapytania["data"].ToString();
            }
            Assert.AreEqual(wynikZapytania, wynik);
        }
    }
}
