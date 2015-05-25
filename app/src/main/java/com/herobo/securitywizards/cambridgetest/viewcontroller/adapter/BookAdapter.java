package com.herobo.securitywizards.cambridgetest.viewcontroller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.commonsware.cwac.endless.EndlessAdapter;
import com.herobo.securitywizards.cambridgetest.R;
import com.herobo.securitywizards.cambridgetest.domain.Book;
import com.herobo.securitywizards.cambridgetest.domain.Books;
import com.herobo.securitywizards.cambridgetest.model.BookService;

import java.util.ArrayList;


public class BookAdapter extends EndlessAdapter {
  private RotateAnimation rotate=null;
  private View pendingView=null;
  private BookService bookService;
  private String title;


  public BookAdapter(Context ctxt, BookService bookService,String title) {
    this(ctxt,new ArrayList<Book>(), bookService,title);
  }

  public BookAdapter(Context ctxt, ArrayList<Book> list, BookService bookService,String title) {
    super(new ArrayAdapter<Book>(ctxt,
                                    R.layout.adapter_simplelist_row,
                                    R.id.title,
                                    list));
    this.bookService = bookService;
    this.title=title;
    rotate=new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                                0.5f, Animation.RELATIVE_TO_SELF,
                                0.5f);
    rotate.setDuration(1000);
    rotate.setRepeatMode(Animation.RESTART);
    rotate.setRepeatCount(Animation.INFINITE);
  }
  
  @Override
  protected View getPendingView(ViewGroup parent) {
    View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_simplelist_row, null);
    
    pendingView=row.findViewById(R.id.title);
    pendingView.setVisibility(View.GONE);
    pendingView=row.findViewById(R.id.icon);
    pendingView.setVisibility(View.VISIBLE);
    startProgressAnimation();
    
    return(row);
  }    @Override
       public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        View rowView=null;
        if(convertView==null){
            rowView=super.getView(position,convertView,parent);
            viewHolder=new ViewHolder();
            viewHolder.title = (TextView) rowView.findViewById(R.id.title);
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.icon);
            viewHolder.description = (TextView) rowView.findViewById(R.id.description);
            rowView.setTag(viewHolder);
        }else{
            rowView=convertView;
            viewHolder=(ViewHolder)rowView.getTag();
        }

        if(position<getWrappedAdapter().getCount()) {
            Book book = (Book) getWrappedAdapter().getItem(position);
            viewHolder.title.setVisibility(View.VISIBLE);
            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.title.setText(book.title);
        }
        return rowView;
    }




  volatile Books books;

    /**
     * returns true if there's still data that can be fetched
     * @return
     */
  @Override
  protected boolean cacheInBackground() {
    //to test for slow netwrok try this -> SystemClock.sleep(10000);
    try {
        books = bookService.searchBooks(title, getWrappedAdapter().getCount());
        return (getWrappedAdapter().getCount() < books.num_found);
    }catch(RuntimeException e2){

    }catch(Exception e){
    }
      return true;
  }
  
  @Override
  protected void appendCachedData() {
    if (books !=null && getWrappedAdapter().getCount()< books.num_found) {
      ArrayAdapter<Book> a=(ArrayAdapter<Book>)getWrappedAdapter();
      //if you need to find the index.. a.getCount()-1
      for(Book book : books.docs){
          a.add(book);
      }
    }
  }
  
  void startProgressAnimation() {
    if (pendingView!=null) {
      pendingView.startAnimation(rotate);
    }
  }

    private class ViewHolder{
        TextView title;
        TextView description;
        ImageView imageView;
    }

}