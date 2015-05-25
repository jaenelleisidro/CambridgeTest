package com.herobo.securitywizards.cambridgetest.model;

import com.herobo.securitywizards.cambridgetest.domain.Books;
import com.herobo.securitywizards.cambridgetest.model.dataaccesslayer.BookHttpService;
import com.herobo.securitywizards.cambridgetest.other.helper.AndroidUtils;

/**
 * Created by Android 17 on 5/8/2015.
 */
public class BookService {
    private final BookHttpService bookHttpService;
    private final AndroidUtils androidUtils;

    public BookService(BookHttpService bookHttpService, AndroidUtils androidUtils) {
        this.bookHttpService = bookHttpService;
        this.androidUtils=androidUtils;
    }

    public Books searchBooks(String title, int start){
        Books books = bookHttpService.searchProducts(title,start);
        return books;
    }

}
