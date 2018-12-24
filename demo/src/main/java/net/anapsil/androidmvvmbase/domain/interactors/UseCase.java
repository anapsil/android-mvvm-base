package net.anapsil.androidmvvmbase.domain.interactors;

import java.io.Serializable;

import io.reactivex.Observable;

/**
 * Created by ana.silva on 19/01/18.
 */

public interface UseCase<D extends Serializable> {
    Observable<D> execute();
}
