﻿using System;
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
using System.Data;

namespace komunikator
{
    /// <summary>
    /// Interaction logic for Logowanie.xaml
    /// </summary>
    public partial class Logowanie : Window
    {
        
        MySqlConnection con = new MySqlConnection(@"Data Source=localhost;port=3306;Initial Catalog=komunikator;User Id=root;password=''");
        int lbrekordow = 0;

        public Logowanie()
        {
            InitializeComponent();
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            con.Open();
            MySqlCommand cmd = con.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = "SELECT login,haslo FROM uzytkownicy WHERE login='" + textBox.Text + "' and haslo='" + passwordBox.Password.ToString() + "'";
            cmd.ExecuteNonQuery();

            DataTable dt = new DataTable();
            MySqlDataAdapter da = new MySqlDataAdapter(cmd);
            da.Fill(dt);
            lbrekordow = Convert.ToInt32(dt.Rows.Count.ToString());

            if (lbrekordow == 0)
            {
                label.Content = "Nieprawidlowy login lub haslo !";
            }
            else
            {

                this.Hide();
                WyborRozmowcy wr = new WyborRozmowcy();
                wr.Show();
            }

            con.Close();
        }

        private void textBox_TextChanged(object sender, TextChangedEventArgs e)
        {

        }
    }
}
