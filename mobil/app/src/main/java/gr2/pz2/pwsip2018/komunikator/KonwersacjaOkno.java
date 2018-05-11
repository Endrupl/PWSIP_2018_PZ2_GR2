package gr2.pz2.pwsip2018.komunikator;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.sql.SQLException;
import java.util.ArrayList;
import gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci.Konwersacja;
import gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci.WiadomoscAdapter;

public class KonwersacjaOkno extends AppCompatActivity {

    private Konwersacja k;
    private ArrayList<Konwersacja.Wiadomosc> wczytaneWiadomosci;
    private WiadomoscAdapter adapter;
    private Handler odswiezacz;
    private Runnable dzialanie;
    private ListView czat;
    private EditText wiadomoscTekst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konwersacja_okno);
        getSupportActionBar().hide();
        k=new Konwersacja(getIntent().getStringExtra("uzytkownik"), getIntent().getStringExtra("adresat"));
        czat=findViewById(R.id.czat);
        wiadomoscTekst=findViewById(R.id.wiadomoscTekst);
        odswiezacz=new Handler();
        dzialanie=new Runnable()
        {
            @Override
            public void run()
            {
                dodajNoweWiadomosci();
                odswiezacz.postDelayed(this, 1000);
            }
        };
        try
        {
            wczytaneWiadomosci=k.wczytajWiadomosci();
            adapter = new WiadomoscAdapter(this, wczytaneWiadomosci);
            czat.setAdapter(adapter);
            czat.setSelection(adapter.getCount()-1);
            odswiezacz.postDelayed(dzialanie, 1000);
        }
        catch (SQLException e)
        {
            Toast.makeText(getApplicationContext(),"Błąd połączenia z serwerem",Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickWyslij(View v)
    {
        String czasSerwera;
        try
        {
            czasSerwera=k.wyslijWiadomosc(wiadomoscTekst.getText().toString());
            wczytaneWiadomosci.add(new Konwersacja.Wiadomosc(wiadomoscTekst.getText().toString(), k.login, czasSerwera));
            czat.setSelection(adapter.getCount()-1);
        }
        catch(SQLException e)
        {
            Toast.makeText(getApplicationContext(),"Błąd połączenia z serwerem",Toast.LENGTH_SHORT).show();
        }
        wiadomoscTekst.setText("");
    }

    private void dodajNoweWiadomosci()
    {
        try
        {
            ArrayList<Konwersacja.Wiadomosc> wiadomosci = k.odswiezKonwersacje();
            int obecnaLiczbaWiadomosci=wczytaneWiadomosci.size();
            for(int i=0; i<wiadomosci.size(); i++)
            {
                wczytaneWiadomosci.add(wiadomosci.get(i));
            }
            if(obecnaLiczbaWiadomosci!=wczytaneWiadomosci.size())
            {
                czat.setSelection(wczytaneWiadomosci.size()-1);
            }
        }
        catch(SQLException e) { }
    }

    @Override
    public void onBackPressed()
    {
        odswiezacz.removeCallbacks(dzialanie);
        super.onBackPressed();
        //startActivity(new Intent(KonwersacjaOkno.this, WyborRozmowcy.class));
    }

    public void onClickWiecej(View v)
    {
        try
        {
            ArrayList<Konwersacja.Wiadomosc> wiadomosci = k.zaladujWczesniejszeWiadomosci();
            ArrayList<Konwersacja.Wiadomosc> nowaLista=new ArrayList<Konwersacja.Wiadomosc>();
            nowaLista.addAll(wiadomosci);
            nowaLista.addAll(wczytaneWiadomosci);
            wczytaneWiadomosci=nowaLista;
            czat.setAdapter(new WiadomoscAdapter(this, wczytaneWiadomosci));
        }
        catch(SQLException e)
        {
            Toast.makeText(getApplicationContext(),"Błąd połączenia z serwerem",Toast.LENGTH_SHORT).show();
        }
    }
}
