package al.sankevich.placeholders.cache;

import al.sankevich.placeholders.dtos.Placeholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides API for caching {@link Placeholder placeholders}.
 */
public class PlaceholdersCache {

    private static final Map<Integer, List<Placeholder>> PLACEHOLDERS_CACHE = new HashMap<>();

    /**
     * Checks if the {@code source}, defined by its {@code hash}, was already cached.
     *
     * @param hash the {@link String#hashCode hash} of the {@code source}.
     * @return {@code true} if the {@code source} already cached, otherwise {@code false}.
     */
    public static boolean isCached(final int hash) {
        return PLACEHOLDERS_CACHE.containsKey(hash);
    }

    /**
     * Retrieves {@link Placeholder placeholder} for the {@code source}, defined by its {@code hash}, on required {@code position}.
     *
     * @param hash    the {@link String#hashCode hash} of the {@code source}.
     * @param pointer the order number of the {@link Placeholder placeholder} in the {@code source}.
     * @return the {@link Placeholder}, if the {@code source} already cached
     * and has {@link Placeholder placeholder} on such {@code position}, otherwise {@code null}.
     */
    public static Placeholder get(final int hash, final int pointer) {
        if (isCached(hash)) {

            List<Placeholder> placeholders = PLACEHOLDERS_CACHE.get(hash);

            if (placeholders.size() > pointer) {
                return placeholders.get(pointer);
            }
        }

        return null;
    }

    /**
     * Ads {@link Placeholder placeholder} to the cache of the {@code source}, defined by its {@code hash}.
     *
     * @param hash        the {@link String#hashCode hash} of the {@code source}.
     * @param placeholder the {@link Placeholder placeholder}, required to be cached.
     */
    public static void add(final int hash, final Placeholder placeholder) {
        PLACEHOLDERS_CACHE.computeIfAbsent(hash, (k) -> new ArrayList<>()).add(placeholder);
    }

}
