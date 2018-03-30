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

        public KonwersacjaOkno(string loginZalogowanego, string loginAdresata)
        {
            k = new Konwersacja(loginZalogowanego, loginAdresata);
            InitializeComponent();
            try
            {
                List<Konwersacja.Wiadomosc> wiadomosci=k.wczytajWiadomosci();
                foreach(Konwersacja.Wiadomosc i in wiadomosci)
                {
                    czat.Items.Add(new Konwersacja.Wiadomosc { tresc = i.tresc, data = i.data, uzytkownik = i.uzytkownik });
                }
            }
            catch(MySqlException)
            {
                MessageBox.Show("Błąd połączenia z serwerem. Sprawdź połączenie z Internetem.", "Błąd", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            odswiezacz = new Timer(new TimerCallback(OnOdswiezEvent), k, 1000, 1000);
        }

        private void OnOdswiezEvent(object o)
        {
            try
            {
                if (((Konwersacja)o).sprawdzCzySaNoweWiadomosci())
                {
                    Dispatcher.Invoke(dodajNoweWiadomosci);
                }
            }
            catch(MySqlException)
            {
                
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
                czat.Items.Add(new Konwersacja.Wiadomosc { tresc = wiadomoscTekst.Text, data = czasSerwera, uzytkownik = k.login });
            }
            catch(MySqlException)
            {
                MessageBox.Show("Błąd połączenia z serwerem. Sprawdź połączenie z Internetem.", "Błąd", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            
            wiadomoscTekst.Text = "";
        }

        private void wiadomoscTekst_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Enter)
            {
                wyslijPrzycisk_Click(sender, e);
            }
        }

        private void powrot_Click(object sender, RoutedEventArgs e)
        {
            WyborRozmowcy oknoPowrot = new WyborRozmowcy();
            oknoPowrot.Show();
        }
    }
}
