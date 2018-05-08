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

public class WyborRozmowcy extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private String zalogowanyUzytkownik = "uzytkownik1";//tymczasowe założenie, że zalogowany użytkownik to uzytkownik1
    private String idzalogowanegouzytkownika = "";
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
            zaladujkontakty();
            android.widget.Spinner spinnerStatus = (android.widget.Spinner)findViewById(R.id.status);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.statusy, android.R.layout.simple_spinner_item);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerStatus.setAdapter(adapter);
    idzalogowanegouzytkownika=Konwersacja.znajdzIdUzytkownika(zalogowanyUzytkownik);
    Konwersacja.Uzytkownik danezalogowanegouzytkownika=Konwersacja.znajdzDaneUzytkownikaPoId(idzalogowanegouzytkownika);
    spinnerStatus.setSelection(adapter.getPosition(danezalogowanegouzytkownika.status));
    spinnerStatus.setOnItemSelectedListener(this);

            odswiezacz=new Handler();
            dzialanie=new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        zaladujkontakty();
                        if (Konwersacja.sprawdzCzySaNoweWiadomosci(zalogowanyUzytkownik))
                        {
                            poinformujONowychWiadomosciach();
                        }
                        else
                        {
                            odswiezKontaktyIWyzerujNoweWiadomosci();
                        }
                    }
                    catch (SQLException e) { }
                    catch (Exception e){}
                    odswiezacz.postDelayed(this, 1000);
                }
            };
            odswiezacz.postDelayed(dzialanie, 0);
        }
        catch (SQLException e)
        {
            Toast.makeText(getApplicationContext(),"Błąd połączenia z serwerem",Toast.LENGTH_SHORT).show();
        }
        catch(NullPointerException e)
        {
            Toast.makeText(getApplicationContext(),"Błąd połączenia z serwerem",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
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
                String idUzytkownika=Konwersacja.znajdzIdUzytkownika(szukanyUzytkownik.getText().toString());
                Konwersacja.Uzytkownik uzytkownik=Konwersacja.znajdzDaneUzytkownikaPoId(idUzytkownika);
                kontaktyUzytkownika.add(new Konwersacja.Kontakt(uzytkownik.id, uzytkownik.status));
                kontakty.setAdapter(adapter);
                szukanyUzytkownik.setText("");
            }
        }
        catch (SQLException e)
        {
            Toast.makeText(getApplicationContext(),"Błąd połączenia z serwerem",Toast.LENGTH_SHORT).show();
        }
    }

    private void zaladujkontakty()
    {
        try {
            kontaktyUzytkownika = Konwersacja.zaladujKontakty(zalogowanyUzytkownik);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try
        {
            kontakty = findViewById(R.id.kontakty);
            adapter = new ArrayAdapter<Konwersacja.Kontakt>(this, R.layout.layoutwm, R.id.wm, kontaktyUzytkownika);
            kontakty.setAdapter(adapter);
            kontakty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pozycja, long id) {
                    android.widget.Spinner spinnerStatus = (android.widget.Spinner) findViewById(R.id.status);
                    Konwersacja.Kontakt wybranyKontakt = ((Konwersacja.Kontakt) kontakty.getItemAtPosition(pozycja));
                    if (spinnerStatus.getSelectedItem().toString().compareTo("niedostępny") != 0) {
                        Intent i = new Intent(WyborRozmowcy.this, KonwersacjaOkno.class);
                        i.putExtra("uzytkownik", zalogowanyUzytkownik);
                        i.putExtra("adresat", wybranyKontakt.login);
                        startActivity(i);
                    }
                }
            });
        }
        catch (NullPointerException e)
        {
            Toast.makeText(getApplicationContext(),"Błąd połączenia z serwerem",Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"Błąd połączenia z serwerem",Toast.LENGTH_SHORT).show();
        }
    }

    private void poinformujONowychWiadomosciach() throws SQLException
    {
        HashMap<String, Integer> zmiany = Konwersacja.wyswietlPowiadomieniaONowychWiadomosciach(zalogowanyUzytkownik);
        for(int i=0; i<kontaktyUzytkownika.size(); i++)
        {
            if(zmiany.get(kontaktyUzytkownika.get(i).login)!=null)
            {
                kontaktyUzytkownika.get(i).nieodczytaneWiadomosci=zmiany.get(kontaktyUzytkownika.get(i).login).intValue();
            }
            else
            {
                kontaktyUzytkownika.get(i).nieodczytaneWiadomosci=0;
            }
        }
        kontakty.setAdapter(adapter);
    }

    private void odswiezKontaktyIWyzerujNoweWiadomosci()
    {
        for(int i=0; i<kontaktyUzytkownika.size(); i++)
        {
            kontaktyUzytkownika.get(i).nieodczytaneWiadomosci=0;
        }
        kontakty.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        try {
            Konwersacja.zapiszStatusUzytkownika(idzalogowanegouzytkownika, adapterView.getItemAtPosition(i).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
