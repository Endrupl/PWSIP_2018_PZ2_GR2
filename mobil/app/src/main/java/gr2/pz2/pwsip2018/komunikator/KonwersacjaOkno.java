package gr2.pz2.pwsip2018.komunikator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci.Konwersacja;

public class KonwersacjaOkno extends AppCompatActivity {

    private Konwersacja k;
    private ListView czat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konwersacja_okno);
        k=new Konwersacja(getIntent().getStringExtra("uzytkownik"), getIntent().getStringExtra("adresat"));
        czat=findViewById(R.id.czat);
        ArrayList<String> al1=new ArrayList<String>();
        ArrayList<String> al2=new ArrayList<String>();
        ArrayList<String> al3=new ArrayList<String>();
        al1.add("qwe");
        al1.add("rty");
        al1.add("uio");
        al2.add("pas");
        al2.add("dfg");
        al2.add("hjk");
        al3.add("lzx");
        al3.add("cvb");
        al3.add("nmq");
        ArrayAdapter<String> adapterWiadomosci = new ArrayAdapter<String>(this, R.layout.wiadomosc, R.id.tresc, al1);
        ArrayAdapter<String> adapterLoginy = new ArrayAdapter<String>(this, R.layout.wiadomosc, R.id.login, al2);
        ArrayAdapter<String> adapterCzas = new ArrayAdapter<String>(this, R.layout.wiadomosc, R.id.czas, al3);
        czat.setAdapter(adapterWiadomosci);
        czat.setAdapter(adapterLoginy);
        czat.setAdapter(adapterCzas);
    }
}
