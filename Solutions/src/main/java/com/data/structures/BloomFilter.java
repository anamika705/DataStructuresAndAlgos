package com.data.structures;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * https://github.com/gkcs/Competitive-Programming/tree/master/src/main/java/main/java/course
 * Bloom Filters are data structures used to efficiently answer queries when we do not have enough "search key"
 * space to handle all possible queries. In this case, the "search key" is hashed, marked and then used later to
 * check if it was searched earlier or not.
 *
 * Bloom Filters use hashing as this is an immutable function result, and marking the respective positions in the
 * data structure guarantees that the next search for the same string will return true.
 *
 * This data structure has an error rate when returning 'true', and we look into how the number of hash functions
 * effect it's performance. In practice, Bloom Filters can be used to check for membership and to avoid
 * 'One Hit Wonders'.
 */
public class BloomFilter<T> {
    private final long[] points;
    private final int size;
    private final List<Function<T, Integer>> hashFunctions;

    BloomFilter(final int size) {
        this.size = size;
        points = new long[(size + 63) >> 6];
        hashFunctions = Arrays.asList(multiplyWith(29), multiplyWith(19), multiplyWith(17));
    }

    private Function<T, Integer> multiplyWith(final int i) {
        return t -> (t.hashCode() * i) % size;
    }

    public void mark(int hash) {
        hash %= size;
        points[hash >> 6] |= (1 << (hash & 63));
    }

    public boolean isMarked(int hash) {
        hash %= size;
        return (points[hash >> 6] & (1 << (hash & 63))) != 0;
    }

    public void add(final T value) {
        hashFunctions.stream().map(function -> function.apply(value)).forEach(this::mark);
    }

    public boolean exists(final T value) {
        return hashFunctions.stream().map(function -> function.apply(value)).allMatch(this::isMarked);
    }
}
