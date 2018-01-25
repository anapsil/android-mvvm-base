package net.anapsil.androidmvvmbase.domain.interactors;

import net.anapsil.androidmvvmbase.BuildConfig;
import net.anapsil.androidmvvmbase.domain.model.Image;
import net.anapsil.androidmvvmbase.domain.model.ImageVariants;
import net.anapsil.mvvmbase.utils.StringUtils;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

/**
 * Created by ana.silva on 19/01/18.
 */

public abstract class MarvelUseCase<D extends Serializable, R> implements UseCase<D> {
    private final char SLASH = '/';
    private final char DOT = '.';

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

    protected String generateImageUrl(Image image) {
        return generateImageUrl(image, 0, 0);
    }

    protected String generateImageUrl(Image image, int density, int orientation) {
        StringBuilder sb = new StringBuilder();
        sb.append(image.getPath());

        if (density != 0 && orientation != 0) {
            sb.append(SLASH).append(getImageVariants(density, orientation));
        }

        sb.append(DOT).append(image.getExtension());

        return sb.toString();
    }

    private String getImageVariants(int density, int orientation) {
        return ImageVariants.get(density, orientation).name().toLowerCase();
    }
}
