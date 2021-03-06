/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.util.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by CovertJaguar on 3/25/2016 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public final class CollectionTools {
    private CollectionTools() {
    }

    @SafeVarargs
    public static <T> BiMap<Integer, T> createIndexedLookupTable(T... elements) {
        return createIndexedLookupTable(Arrays.asList(elements));
    }

    public static <T> BiMap<Integer, T> createIndexedLookupTable(List<T> elements) {
        BiMap<Integer, T> biMap = HashBiMap.create();
        for (int i = 0; i < elements.size(); i++) {
            biMap.put(i, elements.get(i));
        }
        return biMap;
    }

    public static <T> Collection<T> makeSafeSet(@Nullable Collection<T> original) {
        return original == null ? Collections.emptySet() : original;
    }

    public static <T> boolean intersects(Collection<T> collection, T[] array) {
        return Arrays.stream(array).anyMatch(collection::contains);
    }
}
