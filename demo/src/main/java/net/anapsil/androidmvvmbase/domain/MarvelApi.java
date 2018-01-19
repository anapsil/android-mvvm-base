package net.anapsil.androidmvvmbase.domain;

import net.anapsil.androidmvvmbase.domain.model.Response;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ana.silva on 19/01/18.
 */

public interface MarvelApi {

    @GET("v1/public/characters")
    public Single<Response> getCharacters(@Query("apikey") String apiKey,
                                          @Query("ts") String timestamp,
                                          @Query("hash") String hash);

    @GET("v1/public/characters/{characterId}")
    public Single<Response> getCharactersById(@Query("apikey") String apiKey,
                                              @Query("ts") String timestamp,
                                              @Query("hash") String hash,
                                              @Path("characterId") int characterId);
}
