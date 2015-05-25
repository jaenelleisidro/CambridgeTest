package com.herobo.securitywizards.cambridgetest.model.dataaccesslayer;


import com.herobo.securitywizards.cambridgetest.Constant;
import com.herobo.securitywizards.cambridgetest.domain.Movies;

import retrofit.http.GET;
import retrofit.http.Query;

public interface MovieHttpService {

    public static final String PARAM_PRODUCTLIST_TITLE="title";


    @GET(Constant.HTTP.SEARCH)
    Movies searchProducts(
            @Query(PARAM_PRODUCTLIST_TITLE) int start);

}
