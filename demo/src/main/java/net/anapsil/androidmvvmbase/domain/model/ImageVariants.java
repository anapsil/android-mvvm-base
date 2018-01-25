package net.anapsil.androidmvvmbase.domain.model;

/**
 * Created by ana.silva on 24/01/18.
 */

public enum ImageVariants {
    PORTRAIT_SMALL(0.75f, 1),
    PORTRAIT_MEDIUM(1.0f, 1),
    PORTRAIT_XLARGE(1.5f, 1),
    PORTRAIT_FANTASTIC(2.0f, 1),
    PORTRAIT_INCREDIBLE(3.0f, 1),
    LANDSCAPE_SMALL(0.75f, 2),
    LANDSCAPE_MEDIUM(1.0f, 2),
    LANDSCAPE_XLARGE(1.5f, 2),
    LANDSCAPE_AMAZING(2.0f, 2),
    LANDSCAPE_INCREDIBLE(3.0f, 2),
    FULL_SIZE(0, 0);

    float density;
    int orientation;

    ImageVariants(float density, int orientation) {
        this.density = density;
        this.orientation = orientation;
    }

    public static ImageVariants get(float density, int orientation) {
        for (ImageVariants v : ImageVariants.values()) {
            if (v.density == density && v.orientation == orientation) {
                return v;
            } else if (v.density < density && orientation == 1) {
                return PORTRAIT_INCREDIBLE;
            } else if (v.density < density && orientation == 2) {
                return LANDSCAPE_INCREDIBLE;
            }
        }

        return FULL_SIZE;
    }
}
