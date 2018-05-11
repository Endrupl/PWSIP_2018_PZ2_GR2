package gr2.pz2.pwsip2018.komunikator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci.Konwersacja;

public class Logowanie extends AppCompatActivity {

    public EditText nazwauzytkownika;
    public  EditText haslo;
    public String nazwauser;
    public  String pass;
    private String wynik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);
        getSupportActionBar().hide();
    }

    public void onClickZaloguj(View view) throws SQLException {
        nazwauzytkownika=findViewById(R.id.editText2);
        haslo=findViewById(R.id.editText);

        nazwauser=nazwauzytkownika.getText().toString();
        pass=haslo.getText().toString();
        //  Toast.makeText(getApplicationContext(),nazwauser,Toast.LENGTH_SHORT).show();


        wynik=  Konwersacja.znajdzLoginIHaslo(nazwauser,pass);
        if(wynik !=null)
        {
            Intent i=new Intent(Logowanie.this,WyborRozmowcy.class);
            i.putExtra("Username",nazwauser);
            startActivity(i);
        }else
        {
            Toast.makeText(getApplicationContext(),"Dane nie poprawne",Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickZarejestruj(View view) {
        Intent i=new Intent(Logowanie.this,Rejestracja.class);
        startActivity(i);
    }
}
