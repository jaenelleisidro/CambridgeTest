package com.herobo.securitywizards.cambridgetest.viewcontroller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.herobo.securitywizards.cambridgetest.R;
import com.herobo.securitywizards.cambridgetest.domain.Book;
import com.herobo.securitywizards.cambridgetest.model.BookService;
import com.herobo.securitywizards.cambridgetest.other.dagger.Injector;
import com.herobo.securitywizards.cambridgetest.viewcontroller.adapter.BookAdapter;

import javax.inject.Inject;

import butterknife.InjectView;

public class BooksFragment extends BaseFragment {

    @Inject
    protected BookService bookService;

    @InjectView(R.id.lvListHolder)
    protected ListView lvMovies;

    Book book;
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
    }
    @Override
    public View onCreateView2(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simplelist, container, false);
    }

    @Override
    public void onActivityCreated2(Bundle savedInstanceState) {
        BookAdapter adapter=new BookAdapter(getActivity(), bookService,book.title);
        lvMovies.setAdapter(adapter);
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book =(Book)adapterView.getItemAtPosition(i);
            }
        });
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
         book=(Book)savedInstanceState.getSerializable("book");
    }

    //Here you Save your data
    @Override
    public void onSaveInstanceState2(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("book",book);
    }


    public static BooksFragment newInstance(Book book){
        BooksFragment booksFragment=new BooksFragment();
        booksFragment.book=book;
        return booksFragment;
    }
}