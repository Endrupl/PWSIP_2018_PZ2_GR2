package gr2.pz2.pwsip2018.komunikator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.sql.SQLException;
import java.util.ArrayList;
import gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci.Konwersacja;

public class WyborRozmowcy extends AppCompatActivity
{
    private String zalogowanyUzytkownik = "uzytkownik1";//tymczasowe założenie, że zalogowany użytkownik to uzytkownik1
    private ListView kontakty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor_rozmowcy);
        ArrayAdapter<String> adapter;
        try
        {
            ArrayList<String> kontaktyUzytkownika = Konwersacja.zaladujKontakty(zalogowanyUzytkownik);
            kontakty=findViewById(R.id.kontakty);
            adapter = new ArrayAdapter<String>(this, R.layout.layoutwm, R.id.wm, kontaktyUzytkownika);
            kontakty.setAdapter(adapter);
            kontakty.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pozycja, long id)
                {
                    String kontakt = (String) kontakty.getItemAtPosition(pozycja);
                    Intent i=new Intent(WyborRozmowcy.this, KonwersacjaOkno.class);
                    i.putExtra("uzytkownik", zalogowanyUzytkownik);
                    i.putExtra("adresat", kontakt);
                    startActivity(i);
                }
            });
        }
        catch (SQLException e)
        {
            Toast.makeText(getApplicationContext(),"Błąd połączenia z serwerem",Toast.LENGTH_SHORT).show();
        }
    }
}
