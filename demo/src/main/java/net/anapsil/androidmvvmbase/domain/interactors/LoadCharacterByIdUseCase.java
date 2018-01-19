package net.anapsil.androidmvvmbase.domain.interactors;

import net.anapsil.androidmvvmbase.domain.model.Character;
import net.anapsil.androidmvvmbase.repo.CharactersRepository;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by ana.silva on 19/01/18.
 */

public class LoadCharacterByIdUseCase extends MarvelUseCase<Character, CharactersRepository> {

    public LoadCharacterByIdUseCase(CharactersRepository repository) {
        super(repository);
    }

    @Override
    public Observable<Character> execute() {
        return Observable.error(new IllegalArgumentException("Id must be informed."));
    }

    public Single<Character> execute(int id) {
        return repository.getCharactersById(timestamp, generateHash(timestamp), id);
    }
}
