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
            k.wyslijWiadomosc(wiadomoscTekst.Text, "uzytkownik2");//tymczasowe założenie, że konwersacja odbywa się z użytkownikiem uzytkownik2
            wiadomoscTekst.Text = "";
        }
    }
}
