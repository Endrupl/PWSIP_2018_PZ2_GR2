package gr2.pz2.pwsip2018.komunikator.wysylanieWiadomosci;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gr2.pz2.pwsip2018.komunikator.R;

public class WiadomoscAdapter extends BaseAdapter
{
    private ArrayList<Konwersacja.Wiadomosc> wiadomosci;
    private LayoutInflater li;

    public WiadomoscAdapter(Context kontekst, ArrayList<Konwersacja.Wiadomosc> wiadomosci)
    {
        li = LayoutInflater.from(kontekst);
        this.wiadomosci = wiadomosci;
    }

    @Override
    public int getCount()
    {
        return wiadomosci.size();
    }

    @Override
    public Object getItem(int position) {
        return wiadomosci.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class WidokiKontener
    {
        TextView login;
        TextView czas;
        TextView tresc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WidokiKontener widoki = null;
        if(convertView == null)
        {
            widoki = new WidokiKontener();
            convertView = li.inflate(R.layout.wiadomosc, null);
            widoki.login = (TextView) convertView.findViewById(R.id.login);
            widoki.czas = (TextView) convertView.findViewById(R.id.czas);
            widoki.tresc = (TextView) convertView.findViewById(R.id.tresc);
            convertView.setTag(widoki);
        }
        else
        {
            widoki = (WidokiKontener) convertView.getTag();
        }
        widoki.login.setText(wiadomosci.get(position).uzytkownik);
        widoki.czas.setText(wiadomosci.get(position).data);
        widoki.tresc.setText(wiadomosci.get(position).tresc);
        return convertView;
    }
}
