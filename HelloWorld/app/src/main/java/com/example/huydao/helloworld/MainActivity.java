package com.example.huydao.helloworld;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.example.huydao.helloworld.adapter.BookAdapter;
import com.example.huydao.helloworld.model.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
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

public class MainActivity extends Activity {

    private ListView listView;
    private List<Book> books;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        listView = (ListView) findViewById(R.id.lv);
        books = new ArrayList<>();
        loadData();

    }

    private void loadData() {
        String url = "https://raw.githubusercontent.com/linhnguyen106/androidtraining1/master/books.json";
        AsyncTask<String, Void, String>  asyncTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String json = "";
                try {
                    URL url = new URL(params[0]);
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.connect();

                    BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = rd.readLine()) != null)
                    {
                        sb.append(line);
                    }
                    json = sb.toString();
                    return json;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Book>>(){}.getType();
                books = gson.fromJson(s, listType);
                BookAdapter adapter = new BookAdapter(context, 1, books);
                listView.setAdapter(adapter);
            }
        };

        asyncTask.execute(url);
    }
}
