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

namespace komunikator
{
    /// <summary>
    /// Interaction logic for Konwersacja.xaml
    /// </summary>
    public partial class KonwersacjaOkno : Window
    {
        Konwersacja k;

        public KonwersacjaOkno()
        {
            k = new Konwersacja("uzytkownik1");//tymczasowe założenie, że zalogowany użytkownik to uzytkownik1
            InitializeComponent();
        }

        private void wyslijPrzycisk_Click(object sender, RoutedEventArgs e)
        {
            string czasSerwera=null;
            try
            {
                czasSerwera=k.wyslijWiadomosc(wiadomoscTekst.Text, "uzytkownik2");//tymczasowe założenie, że konwersacja odbywa się z użytkownikiem uzytkownik2
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
