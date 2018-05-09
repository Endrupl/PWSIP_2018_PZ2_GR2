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
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Net;
using Microsoft.Win32;
using System.Collections.Specialized;
using MySql.Data.MySqlClient;

namespace Registration
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private const string DANE_BAZY = "Server=localhost; database=komunikator; UID=root; password=; CharSet=utf8";
        //private OpenFileDialog FileChooser;
        WebClient client = new WebClient();
        public MainWindow()
        {
            InitializeComponent();
        }

        /* private void UploadImage_Click(object sender, RoutedEventArgs e)
         {
             FileChooser = new OpenFileDialog();
             FileChooser.Title = "Wybierz swój Awatar ";
             FileChooser.Filter = "Image Files (*.bmp,*.png,*.jpg,*.jpeg)|*.bmp;*.png;*.jpg;*.jpeg";
             if (FileChooser.ShowDialog().Value)
             {
                 UserImage.Text = FileChooser.FileName;
             }
         }*/

        private void SubmitUser_Click(object sender, RoutedEventArgs e)
        {
            NameValueCollection UserInfo = new NameValueCollection();
            UserInfo.Add("login", login.Text);
            UserInfo.Add("Email", Email.Text);
            UserInfo.Add("haslo", haslo.Password);
            //UserInfo.Add("UserImage", UserImage.Text);
            //byte[] InsertUser = client.UploadValues("http://localhost/rejestracja/InsertUser.php", "POST", UserInfo);
            using (MySqlConnection polaczenie = new MySqlConnection(DANE_BAZY))
            {
                polaczenie.Open();
                MySqlCommand zapytanie = polaczenie.CreateCommand();
                zapytanie.CommandText = "INSERT INTO uzytkownicy (idUzytkownika,login,Email,haslo) VALUES (NULL,'" + login.Text + "','" + Email.Text + "','" + haslo.Password + "') ";
                zapytanie.ExecuteReader();
                /*while (wynik.Read())
                {
                    id = wynik["idUzytkownika"].ToString();
                }
                wynik.Close();
                wynik.Dispose();
                zapytanie.Dispose();*/
            }


            /* client.Headers.Add("Content-Type", "binary/octet-stream");
             byte[] InsertUserImage = client.UploadFile("http://localhost/rejestracja/InsertUser.php", FileChooser.FileName);*/
        }
    }
}
