using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using komunikator.wysylanieWiadomosci;

namespace TDDWysylanieWiadomosci
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void TestMethod1()
        {
            Konwersacja k = new Konwersacja();
            string wynik1 = k.znajdzIdUzytkownika("uzytkownik1");
            string wynik2 = k.znajdzIdUzytkownika("uzytkownik2");
            string wynik3 = k.znajdzIdUzytkownika("uzytkownik3");
            Assert.AreEqual("1", wynik1);
            Assert.AreEqual("2", wynik2);
            Assert.AreEqual("3", wynik3);
        }
    }
}
