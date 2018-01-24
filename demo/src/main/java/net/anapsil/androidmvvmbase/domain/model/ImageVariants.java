package net.anapsil.androidmvvmbase.domain.model;

/**
 * Created by ana.silva on 24/01/18.
 */

public enum ImageVariants {
    PORTRAIT_SMALL(120, 1),
    PORTRAIT_MEDIUM(160, 1),
    PORTRAIT_XLARGE(240, 1),
    PORTRAIT_FANTASTIC(320, 1),
    PORTRAIT_INCREDIBLE(480, 1),
    LANDSCAPE_SMALL(120, 2),
    LANDSCAPE_MEDIUM(160, 2),
    LANDSCAPE_XLARGE(240, 2),
    LANDSCAPE_AMAZING(320, 2),
    LANDSCAPE_INCREDIBLE(480, 2),
    FULL_SIZE(0, 0);

    int density;
    int orientation;

    ImageVariants(int density, int orientation) {
        this.density = density;
        this.orientation = orientation;
    }
}
