package gr2.pz2.pwsip2018.komunikator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import java.sql.SQLException;
import gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci.Konwersacja;
import gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci.WiadomoscAdapter;

public class KonwersacjaOkno extends AppCompatActivity {

    private Konwersacja k;
    private ListView czat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konwersacja_okno);
        k=new Konwersacja(getIntent().getStringExtra("uzytkownik"), getIntent().getStringExtra("adresat"));
        czat=findViewById(R.id.czat);
        try
        {
            WiadomoscAdapter adapter = new WiadomoscAdapter(this, k.wczytajWiadomosci());
            czat.setAdapter(adapter);
        }
        catch (SQLException e)
        {
            //while (true)
            //{
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            //}
            // /Toast.makeText(getApplicationContext(),"Błąd połączenia z serwerem",Toast.LENGTH_SHORT).show();
        }
    }
}
