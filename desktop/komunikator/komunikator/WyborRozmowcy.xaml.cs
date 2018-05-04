using System.Collections.Generic;
using System.Windows;
using System.Windows.Input;
using MySql.Data.MySqlClient;
using komunikator.wysylanieWiadomosci;
using System.Threading;
using System.Threading.Tasks;

namespace komunikator
{
    /// <summary>
    /// Interaction logic for WyborRozmowcy.xaml
    /// </summary>
    public partial class WyborRozmowcy : Window
    {
        private string zalogowanyUzytkownik = "uzytkownik1";//tymczasowe założenie, że zalogowany użytkownik to uzytkownik1
        private int idzalogowanegouzytkownika = 0;
        private Timer odswiezacz;

        public WyborRozmowcy()
        {
            InitializeComponent();
            statusUzytkownika.ItemsSource = new List<string>()
            {
                "dostępny","zajęty","niewidoczny","niedostępny"
            };
            try
            {
                zaladujListeKontaktow();
                idzalogowanegouzytkownika = int.Parse(Konwersacja.znajdzIdUzytkownika(zalogowanyUzytkownik));
                Uzytkownik danezalogowanegouzytkownika = Konwersacja.znajdzDaneUzytkownikaPoId(idzalogowanegouzytkownika);
                statusUzytkownika.SelectedValue = danezalogowanegouzytkownika.status;
                otworz.IsEnabled = statusUzytkownika.SelectedValue.ToString() != "niedostępny";
            }
            catch(MySqlException ex) 
            {
                MessageBox.Show("Błąd połączenia z serwerem. Sprawdź połączenie z Internetem.", "Błąd", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            odswiezacz = new Timer(new TimerCallback(OnOdswiezEvent), null, 0, 1000);
        }

        private void OnOdswiezEvent(object o)
        {
            try
            {
                Dispatcher.Invoke(zaladujListeKontaktow);

                if (Konwersacja.sprawdzCzySaNoweWiadomosci(zalogowanyUzytkownik))
                {
                    Dispatcher.Invoke(poinformujONowychWiadomosciach);
                }
                else
                {
                    Dispatcher.Invoke(odswiezKontaktyIWyzerujNoweWiadomosci);
                }
            }
            catch (MySqlException) { }
            catch (TaskCanceledException) { }
        }

        private void zaladujListeKontaktow()
        {
            Konwersacja.Kontakt zaznaczonyKontakt = kontakty.SelectedItem as Konwersacja.Kontakt;
            kontakty.Items.Clear();
            foreach (Konwersacja.Kontakt i in Konwersacja.zaladujKontakty(zalogowanyUzytkownik))
            {
                kontakty.Items.Add(i);
            }
            if (zaznaczonyKontakt != null)
            {
                foreach (Konwersacja.Kontakt i in kontakty.Items)
                {
                    if (i.login == zaznaczonyKontakt.login)
                    {
                        kontakty.SelectedItem = i;
                    }
                }
            }
        }

        private void odswiezKontaktyIWyzerujNoweWiadomosci()
        {
            foreach (Konwersacja.Kontakt i in kontakty.Items)
            {
                i.nieodczytaneWiadomosci = 0;
            }
            kontakty.Items.Refresh();

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
                    ((Konwersacja.Kontakt)kontakty.Items.GetItemAt(i)).nieodczytaneWiadomosci = 0;
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

        private void statusUzytkownika_SelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            if(statusUzytkownika.SelectedValue!=null)
            {
                Konwersacja.zapiszStatusUzytkownika(idzalogowanegouzytkownika, statusUzytkownika.SelectedValue.ToString());
                otworz.IsEnabled = statusUzytkownika.SelectedValue.ToString() != "niedostępny";
            }
            
        }
    }
}
