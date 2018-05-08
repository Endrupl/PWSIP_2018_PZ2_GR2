using Microsoft.VisualStudio.TestTools.UnitTesting;
using komunikator.wysylanieWiadomosci;
using MySql.Data.MySqlClient;
using komunikator;

namespace TDDWysylanieWiadomosci
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void znajdzIdUzytkownikaTest()
        {
            string wynik1 = Konwersacja.znajdzIdUzytkownika("uzytkownik1");
            string wynik2 = Konwersacja.znajdzIdUzytkownika("uzytkownik2");
            string wynik3 = Konwersacja.znajdzIdUzytkownika("uzytkownik3");
            Assert.AreEqual("1", wynik1);
            Assert.AreEqual("2", wynik2);
            Assert.AreEqual("3", wynik3);
        }

        [TestMethod]
        public void znajdzUzytkownikaPoIdTest()
        {
            string wynik1 = Konwersacja.znajdzUzytkownikaPoId("1");
            string wynik2 = Konwersacja.znajdzUzytkownikaPoId("2");
            string wynik3 = Konwersacja.znajdzUzytkownikaPoId("3");
            Assert.AreEqual("uzytkownik1", wynik1);
            Assert.AreEqual("uzytkownik2", wynik2);
            Assert.AreEqual("uzytkownik3", wynik3);
        }

        [TestMethod]
        public void sprawdzCzySaNoweWiadomosciTest()
        {
            Konwersacja k1 = new Konwersacja("uzytkownik1", "uzytkownik2");
            k1.wyslijWiadomosc("TEST");
            Konwersacja k2 = new Konwersacja("uzytkownik2", "uzytkownik1");
            bool wynik = k2.sprawdzCzySaNoweWiadomosci();
            Assert.AreEqual(true, wynik);
        }

        [TestMethod]
        public void sprawdzCzySaNoweWiadomosciTest2()
        {
            Konwersacja k1 = new Konwersacja("uzytkownik1", "uzytkownik2");
            k1.wyslijWiadomosc("TEST");
            bool wynik = Konwersacja.sprawdzCzySaNoweWiadomosci("uzytkownik2");
            Assert.AreEqual(true, wynik);
        }

        [TestMethod]
        public void znajdzUzytkownikaTest()
        {
            bool wynik1 = Konwersacja.znajdzUzytkownika("uzytkownik1");
            bool wynik2 = Konwersacja.znajdzUzytkownika("uzytkownik2");
            bool wynik3 = Konwersacja.znajdzUzytkownika("uzytkownik3");
            bool wynik4 = Konwersacja.znajdzUzytkownika("dgfdgsfgs");
            Assert.AreEqual(true, wynik1);
            Assert.AreEqual(true, wynik2);
            Assert.AreEqual(true, wynik3);
            Assert.AreEqual(false, wynik4);
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

        [TestMethod]
        public void znajdzDaneUzytkownikaPoIdTest()
        {
            Uzytkownik u1 = Konwersacja.znajdzDaneUzytkownikaPoId(1);
            Assert.IsNotNull(u1);
            Assert.AreEqual(1, u1.idUzytkownika);
            Assert.AreEqual("uzytkownik1", u1.login);
        }

        [TestMethod]
        public void zapiszStatusUzytkownikaTest()
        {
            
            Konwersacja.zapiszStatusUzytkownika(1, "dostępny");
            Uzytkownik u1 = Konwersacja.znajdzDaneUzytkownikaPoId(1);
            Assert.IsNotNull(u1);
            Assert.AreEqual("dostępny", u1.status);

            Konwersacja.zapiszStatusUzytkownika(1, "niedostępny");
            u1 = Konwersacja.znajdzDaneUzytkownikaPoId(1);
            Assert.IsNotNull(u1);
            Assert.AreEqual("niedostępny", u1.status);
        }
    }
}
