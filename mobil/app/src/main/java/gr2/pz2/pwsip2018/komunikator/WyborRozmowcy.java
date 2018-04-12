package gr2.pz2.pwsip2018.komunikator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci.Konwersacja;

public class WyborRozmowcy extends AppCompatActivity
{
    private String zalogowanyUzytkownik = "uzytkownik1";//tymczasowe założenie, że zalogowany użytkownik to uzytkownik1
    private ListView kontakty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor_rozmowcy);
        List<String> kontaktyUzytkownika = Konwersacja.zaladujKontakty(zalogowanyUzytkownik);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1, kontaktyUzytkownika);
        kontakty.setAdapter(adapter);
    }
}
