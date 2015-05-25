package com.herobo.securitywizards.cambridgetest.viewcontroller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.herobo.securitywizards.cambridgetest.R;
import com.herobo.securitywizards.cambridgetest.domain.Book;


public class BookListActivity extends ActionBarActivity {
    public static final String KEY_MOVIE="KEY_MOVIE";
    WebView webView;
    ProgressBar progressBar;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);
        book = (Book) getIntent().getSerializableExtra(KEY_MOVIE);

    }

    public static void start(Context context,Book book){
        Intent intent = new Intent(context, BookListActivity.class);
        intent.putExtra(KEY_MOVIE, book);
        context.startActivity(intent);
    }

}
