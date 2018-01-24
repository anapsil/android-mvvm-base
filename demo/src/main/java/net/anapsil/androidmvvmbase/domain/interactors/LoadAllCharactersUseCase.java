package net.anapsil.androidmvvmbase.domain.interactors;

import net.anapsil.androidmvvmbase.domain.model.Character;
import net.anapsil.androidmvvmbase.repo.CharactersRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ana.silva on 19/01/18.
 */

public class LoadAllCharactersUseCase extends MarvelUseCase<Character, CharactersRepository> {

    @Inject
    public LoadAllCharactersUseCase(CharactersRepository repository) {
        super(repository);
    }

    @Override
    public Observable<Character> execute() {
        return execute(0, 0);
    }

    public Observable<Character> execute(float density, int orientation) {
        return repository.getCharacters(timestamp, generateHash(timestamp))
                .map(character -> {
                    character.getThumbnail().setUrl(generateImageUrl(character.getThumbnail(), density, orientation));
                    return character;
                });
    }
}
