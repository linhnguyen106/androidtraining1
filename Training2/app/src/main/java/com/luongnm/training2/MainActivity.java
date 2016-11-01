package com.luongnm.training2;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luongnm.training2.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    private BookAdapter adapter;
    private ListView listView;
    private Context context;
private List<Book> books;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        books = new ArrayList<Book>();
        listView = (ListView) findViewById(R.id.lv_books);
        String url = "https://raw.githubusercontent.com/linhnguyen106/androidtraining1/master/books.json";
        loadData(url);

    }

    private void loadData(final String url) {
        AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String json = "";
                try {
                    URL url = new URL(strings[0]);
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.connect();
                    InputStream inStream = urlConnection.getInputStream();
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    json = stringBuilder.toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return json;
            }

            @Override
            protected void onPostExecute(String json) {
                super.onPostExecute(json);

                Gson gson = new Gson();
                Type listType = new TypeToken<List<Book>>(){}.getType();
                books =  gson.fromJson(json, listType);
                adapter = new BookAdapter(context, 1, books);
                listView.setAdapter(adapter);
            }
        };

        asyncTask.execute(url);
    }
}
