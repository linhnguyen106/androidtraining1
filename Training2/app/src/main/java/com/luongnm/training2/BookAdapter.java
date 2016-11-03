package com.luongnm.training2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.luongnm.training2.model.Book;

import java.util.List;

/**
 * Created by luongnguyenm on 11/1/2016.
 */

public class BookAdapter extends ArrayAdapter {

    private List<Book> booksList;
    private Context context;
    private LayoutInflater inflater;

    public BookAdapter(Context context, int resource, List<Book> books) {
        super(context, resource, books);
        this.booksList = books;
        this.context = context;
    }

    @Nullable
    @Override
    public Book getItem(int position) {
        return booksList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_layout, null);
        }

        Book book = getItem(position);
        TextView id = (TextView) convertView.findViewById(R.id.tv_id);
        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView author = (TextView) convertView.findViewById(R.id.tv_author);

        id.setText(book.getId());
        name.setText(book.getName());
        author.setText(book.getAuthor());

        return convertView;
    }
}
