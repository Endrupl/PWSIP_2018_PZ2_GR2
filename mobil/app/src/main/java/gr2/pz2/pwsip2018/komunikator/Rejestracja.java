package gr2.pz2.pwsip2018.komunikator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci.Konwersacja;

public class Rejestracja extends AppCompatActivity {

    public EditText nazwauser;
    public  EditText email;
    public EditText password;
    public  String pass;
    private String wynik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);
        getSupportActionBar().hide();
    }

    public void onClickZarejestruj(View view) throws SQLException {
        nazwauser=findViewById(R.id.editText5);
        email=findViewById(R.id.editText4);
        password=findViewById(R.id.editText3);
        String wynik=  Konwersacja.znajdzIdUzytkownika(nazwauser.getText().toString());
        String wynik2=  Konwersacja.znajdzEmailUzytkownika(nazwauser.getText().toString());
        try{
            if(wynik!=null || wynik2!=null)
            {
                Toast.makeText(getApplicationContext(), "Podany użytkownik lub email istnieje.", Toast.LENGTH_SHORT).show();
            }else{
                Konwersacja.dodajUzytkownika(nazwauser.getText().toString(),email.getText().toString(),password.getText().toString());
                Toast.makeText(getApplicationContext(), "Pomyślnie zarejestrowano.", Toast.LENGTH_SHORT).show();
            }

        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "Wystąpił nieoczekiwany błąd. Spróbuj ponownie", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickLogowanie(View view) {
        Intent i=new Intent(Rejestracja.this,Logowanie.class);
        startActivity(i);
    }
}
