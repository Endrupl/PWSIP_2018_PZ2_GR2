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
        private string zalogowanyUzytkownik = "uzytkownik2";//tymczasowe założenie, że zalogowany użytkownik to uzytkownik1
        private Timer odswiezacz;

        public WyborRozmowcy()
        {
            InitializeComponent();
            try
            {
                foreach (string i in Konwersacja.zaladujKontakty(zalogowanyUzytkownik))
                {
                    kontakty.Items.Add(new Konwersacja.Kontakt { login = i });
                }
            }
            catch(MySqlException)
            {
                MessageBox.Show("Błąd połączenia z serwerem. Sprawdź połączenie z Internetem.", "Błąd", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            odswiezacz = new Timer(new TimerCallback(OnOdswiezEvent), null, 0, 1000);
        }

        private void OnOdswiezEvent(object o)
        {
            try
            {
                if (Konwersacja.sprawdzCzySaNoweWiadomosci(zalogowanyUzytkownik))
                {
                    Dispatcher.Invoke(poinformujONowychWiadomosciach);
                }
                else
                {
                    foreach(Konwersacja.Kontakt i in kontakty.Items)
                    {
                        i.nieodczytaneWiadomosci = 0;
                    }
                    Dispatcher.Invoke(() => kontakty.Items.Refresh());
                }
            }
            catch (MySqlException) { }
        }

        private void poinformujONowychWiadomosciach()
        {
            Dictionary<string, int> zmiany = Konwersacja.wyswietlPowiadomieniaONowychWiadomosciach(zalogowanyUzytkownik);
            for(int i=0; i<kontakty.Items.Count; i++)
            {
                foreach (KeyValuePair<string, int> j in zmiany)
                {
                    if (((Konwersacja.Kontakt)kontakty.Items.GetItemAt(i)).login.Equals(j.Key))
                    {
                        ((Konwersacja.Kontakt)kontakty.Items.GetItemAt(i)).nieodczytaneWiadomosci = j.Value;
                        break;
                    }
                }
            }
            kontakty.Items.Refresh();
        }

        private void dodajUzytkownika_Click(object sender, RoutedEventArgs e)
        {
            foreach(Konwersacja.Kontakt i in kontakty.Items)
            {
                if(i.login.Equals(szukanyUzytkownik.Text))
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
                    kontakty.Items.Add(new Konwersacja.Kontakt { login = szukanyUzytkownik.Text });
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
            KonwersacjaOkno okienko = new KonwersacjaOkno(zalogowanyUzytkownik, ((Konwersacja.Kontakt)kontakty.SelectedItem).login);
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
                Konwersacja.usunKontakt(zalogowanyUzytkownik, ((Konwersacja.Kontakt)kontakty.SelectedItem).login);
                kontakty.Items.RemoveAt(kontakty.SelectedIndex);
            }
            catch(MySqlException)
            {
                MessageBox.Show("Błąd połączenia z serwerem. Sprawdź połączenie z Internetem.", "Błąd", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
    }
}
