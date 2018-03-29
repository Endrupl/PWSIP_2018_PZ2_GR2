using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using komunikator.wysylanieWiadomosci;
using MySql.Data.MySqlClient;
using System.Threading;

namespace komunikator
{
    /// <summary>
    /// Interaction logic for Konwersacja.xaml
    /// </summary>
    public partial class KonwersacjaOkno : Window
    {
        private Konwersacja k;
        private Timer odswiezacz;

        public KonwersacjaOkno()
        {
            k = new Konwersacja("uzytkownik1", "uzytkownik2");//tymczasowe założenie, że zalogowany użytkownik to uzytkownik1 i konwersacja odbywa się z użytkownikiem uzytkownik2
            InitializeComponent();
            List<Konwersacja.Wiadomosc> wiadomosci=k.wczytajWiadomosci();
            foreach(Konwersacja.Wiadomosc i in wiadomosci)
            {
                czat.Items.Add(new Konwersacja.Wiadomosc { tresc = i.tresc, data = i.data, uzytkownik = i.uzytkownik });
            }
            odswiezacz = new Timer(new TimerCallback(OnOdswiezEvent), k, 1000, 1000);
        }

        private void OnOdswiezEvent(object o)
        {
            if (((Konwersacja)o).sprawdzCzySaNoweWiadomosci())
            {
                Dispatcher.Invoke(dodajNoweWiadomosci);
            }
        }

        private void dodajNoweWiadomosci()
        {
            List<Konwersacja.Wiadomosc> wiadomosci = k.odswiezKonwersacje();
            foreach (Konwersacja.Wiadomosc i in wiadomosci)
            {
                czat.Items.Add(new Konwersacja.Wiadomosc { tresc = i.tresc, data = i.data, uzytkownik = i.uzytkownik });
            }
        }

        private void wyslijPrzycisk_Click(object sender, RoutedEventArgs e)
        {
            string czasSerwera=null;
            try
            {
                czasSerwera=k.wyslijWiadomosc(wiadomoscTekst.Text);
            }
            catch(MySqlException)
            {
                MessageBox.Show("Błąd połączenia z serwerem. Sprawdź połączenie z Internetem.", "Błąd", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            czat.Items.Add(new Konwersacja.Wiadomosc { tresc = wiadomoscTekst.Text, data = czasSerwera, uzytkownik = k.login });
            wiadomoscTekst.Text = "";
        }

        private void wiadomoscTekst_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Enter)
            {
                wyslijPrzycisk_Click(sender, e);
            }
        }
    }
}
