package gr2.pz2.pwsip2018.komunikator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
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
        ArrayList<String> kontaktyUzytkownika = Konwersacja.zaladujKontakty(zalogowanyUzytkownik);
        kontakty=findViewById(R.id.kontakty);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.layoutwm, R.id.wm, kontaktyUzytkownika);
        kontakty.setAdapter(adapter);
    }
}
