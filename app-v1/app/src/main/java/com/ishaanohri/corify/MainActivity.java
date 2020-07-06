package com.ishaanohri.corify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Document document;
    private String url = "https://www.google.com/search?rlz=1C5CHFA_enIN885IN885&tbm=nws&sxsrf=ALeKk03CXQVEvnVThPmDdsp5GzsK6AH94w:1583790719303&q=covid+19&spell=1&sa=X&ved=0ahUKEwjKzuOZsI7oAhWVwjgGHaOCCl8QBQgmKAA&biw=1440&bih=714&dpr=2";
    private ArrayList<String> headingArrayList = new ArrayList<>();
    private ArrayList<String> descriptionArrayList = new ArrayList<>();
    private ArrayList<String> timeSourceArrayList = new ArrayList<>();
    private ArrayList<String> imageArrayList = new ArrayList<>();
    private ArrayList<String> linkArrayList = new ArrayList<>();
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(getApplicationContext().getColor(R.color.colorAccent));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.i("INFO","Started");
        url = "https://www.google.com/search?rlz=1C5CHFA_enIN885IN885&tbm=nws&sxsrf=ALeKk03CXQVEvnVThPmDdsp5GzsK6AH94w:1583790719303&q=covid+19&spell=1&sa=X&ved=0ahUKEwjKzuOZsI7oAhWVwjgGHaOCCl8QBQgmKAA&biw=1440&bih=714&dpr=2";
        new scrapWeb().execute();
//        url = "https://www.google.com/search?q=covid+19&rlz=1C5CHFA_enIN885IN885&tbm=nws&sxsrf=ALeKk03MTDm4H0dPhTfOP-RFQfXPe62CrQ:1583836472346&ei=OG1nXs3VFLG-3LUP-fKyuAE&start=10&sa=N&ved=0ahUKEwjN0cPS2o_oAhUxH7cAHXm5DBcQ8tMDCFw&biw=885&bih=714&dpr=2";
//        new scrapWeb().execute();
    }

    public class scrapWeb extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                Log.i("INFO","Fetching Price");
                for (int i = 0 ; i < document.getElementsByClass("slp").size() ; i++)
                {
                    //Title
                    headingArrayList.add(document.getElementsByClass("l lLrAF").get(i).text());
                    Log.i("INFO",document.getElementsByClass("l lLrAF").get(i).text());
                    //Link
                    linkArrayList.add(document.getElementsByClass("l lLrAF").eachAttr("href").get(i));
                    Log.i("INFO",document.getElementsByClass("l lLrAF").eachAttr("href").get(i));
                    //Time and Source
                    timeSourceArrayList.add(document.getElementsByClass("slp").get(i).text());
                    Log.i("INFO",document.getElementsByClass("slp").get(i).text());
                    //Description
                    descriptionArrayList.add(document.getElementsByClass("st").get(i).text());
                    Log.i("INFO",document.getElementsByClass("st").get(i).text());
                    //Image
//                    imageArrayList.add(document.getElementsByClass("th BbeB2d").eachAttr("src").get(i));
//                    Log.i("INFO",document.getElementsByClass("th BbeB2d").eachAttr("src").get(i));
                }

                newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(headingArrayList,descriptionArrayList,timeSourceArrayList,imageArrayList,linkArrayList, MainActivity.this);
                recyclerView.setAdapter(newsRecyclerViewAdapter);

            }catch (Exception e)
            {
                Log.i("INFO","Price Not Recvd");
                Log.i("INFO",e.getMessage());

            }

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                document = Jsoup.connect(url).get();
                Log.i("INFO","Document Created");
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("INFO","Document Not Created");
            }

            return null;
        }
    }
}
