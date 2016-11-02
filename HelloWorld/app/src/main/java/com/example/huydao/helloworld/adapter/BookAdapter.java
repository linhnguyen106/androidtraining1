package com.example.huydao.helloworld.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.huydao.helloworld.R;
import com.example.huydao.helloworld.model.Book;

import java.util.List;

/**
 * Created by huydao on 10/31/2016.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    private Context context;
    private List<Book> books;

    public BookAdapter(Context context, int resource, List<Book> books) {
        super(context, resource, books);
        this.context = context;
        this.books = books;
    }

    @Nullable
    @Override
    public Book getItem(int position) {
        return books.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_layout, null);

        TextView id = (TextView) view.findViewById(R.id.id);
        TextView cat = (TextView) view.findViewById(R.id.cat);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView author = (TextView) view.findViewById(R.id.author);
        TextView series = (TextView) view.findViewById(R.id.series);
        TextView sequence = (TextView) view.findViewById(R.id.sequence);
        TextView genre = (TextView) view.findViewById(R.id.genre);
        TextView instock = (TextView) view.findViewById(R.id.instock);
        TextView price = (TextView) view.findViewById(R.id.price);
        TextView pages = (TextView) view.findViewById(R.id.pages);

        Book book = getItem(position);

        id.setText(book.getId());
        cat.setText(book.getCat().get(0) + ": " + book.getCat().get(1));
        name.setText(book.getName());
        author.setText(book.getAuthor());
        series.setText(book.getSeries_t());
        sequence.setText(String.valueOf(book.getSequence_i()));
        genre.setText(book.getGenre_s());
        instock.setText(book.isInStock() ? "Yes" : "No");
        price.setText(String.valueOf(book.getPrice()));
        pages.setText(String.valueOf(book.getPages_i()));

        return view;
    }

}
