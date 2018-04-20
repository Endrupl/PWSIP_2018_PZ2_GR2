package gr2.pz2.pwsip2018.komunikator;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci.Konwersacja;

public class WyborRozmowcy extends AppCompatActivity
{
    private String zalogowanyUzytkownik = "uzytkownik1";//tymczasowe założenie, że zalogowany użytkownik to uzytkownik1
    private ListView kontakty;
    private ArrayList<Konwersacja.Kontakt> kontaktyUzytkownika;
    private EditText szukanyUzytkownik;
    private ArrayAdapter<Konwersacja.Kontakt> adapter;
    private Handler odswiezacz;
    private Runnable dzialanie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor_rozmowcy);
        szukanyUzytkownik=findViewById(R.id.szukanyUzytkownik);
        try
        {
            kontaktyUzytkownika = Konwersacja.zaladujKontakty(zalogowanyUzytkownik);
            kontakty=findViewById(R.id.kontakty);
            adapter = new ArrayAdapter<Konwersacja.Kontakt>(this, R.layout.layoutwm, R.id.wm, kontaktyUzytkownika);
            kontakty.setAdapter(adapter);
            kontakty.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pozycja, long id)
                {
                    String kontakt = ((Konwersacja.Kontakt) kontakty.getItemAtPosition(pozycja)).login;
                    Intent i=new Intent(WyborRozmowcy.this, KonwersacjaOkno.class);
                    i.putExtra("uzytkownik", zalogowanyUzytkownik);
                    i.putExtra("adresat", kontakt);
                    startActivity(i);
                }
            });
            odswiezacz=new Handler();
            dzialanie=new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        if (Konwersacja.sprawdzCzySaNoweWiadomosci(zalogowanyUzytkownik))
                        {
                            poinformujONowychWiadomosciach();
                        }
                        else
                        {
                            //odswiezKontaktyIWyzerujNoweWiadomosci();
                        }
                    }
                    catch (SQLException e) { }
                    odswiezacz.postDelayed(this, 1000);
                }
            };
        }
        catch (SQLException e)
        {
            Toast.makeText(getApplicationContext(),"Błąd połączenia z serwerem",Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickDodajUzytkownika(View v)
    {
        for(int i=0; i<kontaktyUzytkownika.size(); i++)
        {
            if(kontaktyUzytkownika.get(i).login.equals(szukanyUzytkownik.getText().toString()))
            {
                Toast.makeText(getApplicationContext(),"Użytkownik jest już dodany do kontaktów",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        try
        {
            if(!Konwersacja.znajdzUzytkownika(szukanyUzytkownik.getText().toString()))
            {
                Toast.makeText(getApplicationContext(),"Użytkownik o podanym nicku nie istnieje",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Konwersacja.dodajKontakt(zalogowanyUzytkownik, szukanyUzytkownik.getText().toString());
                kontaktyUzytkownika.add(new Konwersacja.Kontakt(szukanyUzytkownik.getText().toString()));
                kontakty.setAdapter(adapter);
                szukanyUzytkownik.setText("");
            }
        }
        catch (SQLException e)
        {
            Toast.makeText(getApplicationContext(),"Błąd połączenia z serwerem",Toast.LENGTH_SHORT).show();
        }
    }

    private void poinformujONowychWiadomosciach() throws SQLException
    {
        HashMap<String, Integer> zmiany = Konwersacja.wyswietlPowiadomieniaONowychWiadomosciach(zalogowanyUzytkownik);
        for(int i=0; i<kontaktyUzytkownika.size(); i++)
        {
            for(int j=0; j<zmiany.size(); j++)
            {
                //if(((Konwersacja.Kontakt)kontakty.getItemAtPosition(i)).login.equals(zmiany.))
            }
            //foreach (KeyValuePair<string, int> j in zmiany)
            //{
            //    if (((Konwersacja.Kontakt)kontakty.Items.GetItemAt(i)).login.Equals(j.Key))
            //    {
            //        ((Konwersacja.Kontakt)kontakty.Items.GetItemAt(i)).nieodczytaneWiadomosci = j.Value;
            //        break;
            //    }
            //    ((Konwersacja.Kontakt)kontakty.Items.GetItemAt(i)).nieodczytaneWiadomosci = 0;
            //}
        }
        //kontakty.Items.Refresh();
    }
}
