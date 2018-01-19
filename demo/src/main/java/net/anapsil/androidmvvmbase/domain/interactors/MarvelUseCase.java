package net.anapsil.androidmvvmbase.domain.interactors;

import net.anapsil.androidmvvmbase.BuildConfig;
import net.anapsil.mvvmbase.utils.StringUtils;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

/**
 * Created by ana.silva on 19/01/18.
 */

public abstract class MarvelUseCase<D extends Serializable, R> implements UseCase<D> {
    String timestamp;
    R repository;

    public MarvelUseCase(R repository) {
        this.repository = repository;

        timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
    }

    protected String generateHash(String timestamp) {
        try {
            return StringUtils.generateMd5(timestamp + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_API_KEY);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }
}
