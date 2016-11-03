package com.longch.example.training2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.longch.example.training2.model.Books;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by longch on 11/3/2016.
 */

public class BooksAdapter extends ArrayAdapter{
    private List<Books> datas;
    private Context context;
    private LayoutInflater inflater;

    public BooksAdapter(Context context, int resource, List<Books> books) {
        super(context, resource, books);
        this.datas = books;
        this.context = context;
    }

    @Nullable
    @Override
    public Books getItem(int position) {
        return datas.get(position);
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

        Books book = getItem(position);
        TextView id = (TextView) convertView.findViewById(R.id.tv_id);
        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView genre = (TextView) convertView.findViewById(R.id.tv_genre);
        TextView price = (TextView) convertView.findViewById(R.id.tv_price);

        id.setText(book.getId());
        name.setText(book.getName());
        genre.setText(book.getGenre_s());
        price.setText(Double.toString(book.getPrice()));

        return convertView;
    }
}
