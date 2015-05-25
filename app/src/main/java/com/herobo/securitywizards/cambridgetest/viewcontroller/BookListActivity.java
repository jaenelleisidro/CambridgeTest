package com.herobo.securitywizards.cambridgetest.viewcontroller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.herobo.securitywizards.cambridgetest.R;
import com.herobo.securitywizards.cambridgetest.domain.Book;
import com.herobo.securitywizards.cambridgetest.other.helper.AndroidUtils;
import com.herobo.securitywizards.cambridgetest.viewcontroller.activity.BaseActivity;
import com.herobo.securitywizards.cambridgetest.viewcontroller.fragment.BooksFragment;

import javax.inject.Inject;


public class BookListActivity extends BaseActivity {
    @Inject
    AndroidUtils androidUtils;

    public static final String KEY_BOOK ="KEY_BOOK";
    WebView webView;
    ProgressBar progressBar;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);
        book = (Book) getIntent().getSerializableExtra(KEY_BOOK);
        androidUtils.loadFragment(this,R.id.container, BooksFragment.newInstance(book));

    }

    public static void start(Context context,Book book){
        Intent intent = new Intent(context, BookListActivity.class);
        intent.putExtra(KEY_BOOK, book);
        context.startActivity(intent);
    }

}
