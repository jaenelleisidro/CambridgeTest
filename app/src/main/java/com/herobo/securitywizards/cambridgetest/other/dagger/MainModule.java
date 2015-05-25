package com.herobo.securitywizards.cambridgetest.other.dagger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.herobo.securitywizards.cambridgetest.Constant;
import com.herobo.securitywizards.cambridgetest.model.MovieService;
import com.herobo.securitywizards.cambridgetest.model.dataaccesslayer.MovieHttpService;
import com.herobo.securitywizards.cambridgetest.other.MainApplication;
import com.herobo.securitywizards.cambridgetest.other.helper.AndroidUtils;
import com.herobo.securitywizards.cambridgetest.other.helper.PostFromAnyThreadBus;
import com.herobo.securitywizards.cambridgetest.other.retrofit.RestAdapterRequestInterceptor;
import com.herobo.securitywizards.cambridgetest.other.retrofit.RestErrorHandler;
import com.herobo.securitywizards.cambridgetest.other.retrofit.UserAgentProvider;
import com.herobo.securitywizards.cambridgetest.viewcontroller.fragment.BaseFragment;
import com.herobo.securitywizards.cambridgetest.viewcontroller.fragment.MoviesFragment;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Dagger module for setting up provides statements.
 * Register all of your entry points below.
 */
@Module(
        complete = false,

        injects = {
                MainApplication.class
                ,BaseFragment.class
                , MoviesFragment.class

        }
)
public class MainModule {
    @Singleton
    @Provides
    Bus provideOttoBus() {
        return new PostFromAnyThreadBus();
    }

    @Provides
    @Singleton
    AndroidUtils provideAndroidUtils(Context context){
        return new AndroidUtils(context);
    }

    @Provides
    Gson provideGson() {
        /**
         * GSON instance to use for all request  with date format set up for proper parsing.
         * <p/>
         * You can also configure GSON with different naming policies for your API.
         * Maybe your API is Rails API and all json values are lower case with an underscore,
         * like this "first_name" instead of "firstName".
         * You can configure GSON as such below.
         * <p/>
         *
         * public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd")
         *         .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
         */
        return new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    }


    @Provides
    RestAdapterRequestInterceptor provideRestAdapterRequestInterceptor(UserAgentProvider userAgentProvider,AndroidUtils androidUtils) {
        return new RestAdapterRequestInterceptor(userAgentProvider,androidUtils);
    }
    @Provides
    RestErrorHandler provideRestErrorHandler(Bus bus,RestAdapterRequestInterceptor restAdapterRequestInterceptor) {
        return new RestErrorHandler(bus,restAdapterRequestInterceptor);
    }

    @Provides
    OkHttpClient provideRestAdapterRequestInterceptor(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
        int cacheSize = 50 * 1024 * 1024; // 10 MiB
        File cacheDirectory = new File(context.getCacheDir().getAbsolutePath(), "HttpCache");
        try {
            Cache cache = new Cache(cacheDirectory, cacheSize);
            okHttpClient.setCache(cache);
        } catch (IOException e) {
            //cache is not really important, skip if something went wrong
        }
        return okHttpClient;
    }



    @Provides
    RestAdapter provideRestAdapter(Context context,RestErrorHandler restErrorHandler, RestAdapterRequestInterceptor restRequestInterceptor, Gson gson,OkHttpClient okHttpClient) {
        return new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(Constant.HTTP.OPENLIBRARY_URL)
                .setErrorHandler(restErrorHandler)
                .setRequestInterceptor(restRequestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();
    }

    @Singleton
    @Provides
    MovieHttpService provideProductHttpService(RestAdapter restAdapter){
        return restAdapter.create(MovieHttpService.class);
    }

    @Singleton
    @Provides
    MovieService provideMovieService(MovieHttpService movieHttpService,AndroidUtils androidUtils){
        return new MovieService(movieHttpService,androidUtils);
    }



}
