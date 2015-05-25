package com.herobo.securitywizards.cambridgetest.model;

import com.herobo.securitywizards.cambridgetest.domain.Movies;
import com.herobo.securitywizards.cambridgetest.model.dataaccesslayer.MovieHttpService;
import com.herobo.securitywizards.cambridgetest.other.helper.AndroidUtils;

/**
 * Created by Android 17 on 5/8/2015.
 */
public class MovieService {
    private final MovieHttpService movieHttpService;
    private final AndroidUtils androidUtils;

    public MovieService(MovieHttpService movieHttpService, AndroidUtils androidUtils) {
        this.movieHttpService=movieHttpService;
        this.androidUtils=androidUtils;
    }

    public Movies getMovie(int start){
        Movies movies=movieHttpService.searchProducts(start);
        return movies;
    }

}
