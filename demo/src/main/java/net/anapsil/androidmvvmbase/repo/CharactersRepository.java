package net.anapsil.androidmvvmbase.repo;

import net.anapsil.androidmvvmbase.BuildConfig;
import net.anapsil.androidmvvmbase.domain.MarvelApi;
import net.anapsil.androidmvvmbase.domain.model.Character;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ana.silva on 19/01/18.
 */

public class CharactersRepository {
    private MarvelApi api;

    @Inject
    public CharactersRepository(MarvelApi api) {
        this.api = api;
    }

    public Single<List<Character>> getCharacters(String timestamp, String hash) {
        return api.getCharacters(BuildConfig.MARVEL_API_KEY, timestamp, hash)
                .flatMap(response -> Single.just(response.getData().getResults()));
    }

    public Single<Character> getCharactersById(String timestamp, String hash, int id) {
        return api.getCharactersById(id, BuildConfig.MARVEL_API_KEY, timestamp, hash)
                .map(response -> response.getData().getResults().get(0));
    }
}
