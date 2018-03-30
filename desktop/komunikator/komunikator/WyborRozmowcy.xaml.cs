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
using MySql.Data.MySqlClient;
using komunikator.wysylanieWiadomosci;
using System.Threading;

namespace komunikator
{
    /// <summary>
    /// Interaction logic for WyborRozmowcy.xaml
    /// </summary>
    public partial class WyborRozmowcy : Window
    {
        private string zalogowanyUzytkownik = "uzytkownik1";//tymczasowe założenie, że zalogowany użytkownik to uzytkownik1
        private Timer odswiezacz;

        public WyborRozmowcy()
        {
            InitializeComponent();
            try
            {
                foreach (string i in Konwersacja.zaladujKontakty(zalogowanyUzytkownik))
                {
                    kontakty.Items.Add(i);
                }
            }
            catch(MySqlException)
            {
                MessageBox.Show("Błąd połączenia z serwerem. Sprawdź połączenie z Internetem.", "Błąd", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            odswiezacz = new Timer(new TimerCallback(OnOdswiezEvent), null, 5000, 5000);
        }

        private void OnOdswiezEvent(object o)
        {
            try
            {
                if(Konwersacja.sprawdzCzySaNoweWiadomosci(zalogowanyUzytkownik))
                {
                    
                }
            }
            catch (MySqlException) { }
        }

        private void poinformujONowychWiadomosciach()
        {

        }

        private void dodajUzytkownika_Click(object sender, RoutedEventArgs e)
        {
            foreach(string i in kontakty.Items)
            {
                if(i.Equals(szukanyUzytkownik.Text))
                {
                    MessageBox.Show("Użytkownik jest już dodany do kontaktów.", "Użytkownik jest już dodany do kontaktów", MessageBoxButton.OK, MessageBoxImage.Asterisk);
                    return;
                }
            }
            try
            {
                if (!Konwersacja.znajdzUzytkownika(szukanyUzytkownik.Text))
                {
                    MessageBox.Show("Użytkownik o podanym nicku nie istnieje. Sprawdź podany nick.", "Nie znaleziono użytkownika", MessageBoxButton.OK,
                                    MessageBoxImage.Exclamation);
                }
                else
                {
                    Konwersacja.dodajKontakt(zalogowanyUzytkownik, szukanyUzytkownik.Text);
                    kontakty.Items.Add(szukanyUzytkownik.Text);
                    szukanyUzytkownik.Text = "";
                }
            }
            catch(MySqlException)
            {
                MessageBox.Show("Błąd połączenia z serwerem. Sprawdź połączenie z Internetem.", "Błąd", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            
        }

        private void szukanyUzytkownik_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Enter)
            {
                dodajUzytkownika_Click(sender, e);
            }
        }

        private void otworz_Click(object sender, RoutedEventArgs e)
        {
            if (kontakty.SelectedIndex == -1)
            {
                MessageBox.Show("Wybierz użytkownika, z którym chcesz prowadzić konwersację.", "Wybierz użytkownika, z którym chcesz prowadzić konwersację",
                    MessageBoxButton.OK, MessageBoxImage.Exclamation);
                return;
            }
            KonwersacjaOkno okienko = new KonwersacjaOkno(zalogowanyUzytkownik, kontakty.SelectedItem.ToString());
            okienko.Show();
        }

        private void usun_Click(object sender, RoutedEventArgs e)
        {
            if (kontakty.SelectedIndex == -1)
            {
                MessageBox.Show("Wybierz kontakt, który chcesz usunąć.", "Wybierz kontakt, który chcesz usunąć", MessageBoxButton.OK, MessageBoxImage.Exclamation);
                return;
            }
            try
            {
                Konwersacja.usunKontakt(zalogowanyUzytkownik, kontakty.SelectedItem.ToString());
                kontakty.Items.RemoveAt(kontakty.SelectedIndex);
            }
            catch(MySqlException)
            {
                MessageBox.Show("Błąd połączenia z serwerem. Sprawdź połączenie z Internetem.", "Błąd", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
    }
}
