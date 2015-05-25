package com.herobo.securitywizards.cambridgetest.model.dataaccesslayer;


import com.herobo.securitywizards.cambridgetest.Constant;
import com.herobo.securitywizards.cambridgetest.domain.Books;

import retrofit.http.GET;
import retrofit.http.Query;

public interface BookHttpService {

    public static final String PARAM_PRODUCTLIST_TITLE="title";
    public static final String PARAM_PRODUCTLIST_INDEX="start";


    @GET(Constant.HTTP.SEARCH)
    Books searchProducts(
            @Query(PARAM_PRODUCTLIST_TITLE) String title,@Query(PARAM_PRODUCTLIST_INDEX) int index);

}
