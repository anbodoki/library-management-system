package com.lms.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ListUtils {

    /**
     * default size of subList is 1000
     * @param set
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> split(Set<T> set) {
        List<T> all = new ArrayList<T>(set);
        List<List<T>> result = new ArrayList<List<T>>();

        for (int i = 0; i < all.size(); i += 1000) {
            int toIndex = (i + 1000) > all.size() ? all.size() : (i + 1000);
            result.add(all.subList(i, toIndex));
        }
        return result;
    }

    /**
     * default size of subList is 1000
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> split(List<T> list) {
        List<T> all = new ArrayList<T>(list);
        List<List<T>> result = new ArrayList<List<T>>();

        for (int i = 0; i < all.size(); i += 1000) {
            int toIndex = (i + 1000) > all.size() ? all.size() : (i + 1000);
            result.add(all.subList(i, toIndex));
        }
        return result;
    }

    public static <T> List<List<T>> split(List<T> list, int subListSize) {
        if (subListSize == 0) {
            throw new IllegalArgumentException("SubListSize must be greater than zero");
        }
        List<T> all = new ArrayList<T>(list);
        List<List<T>> result = new ArrayList<List<T>>();

        for (int i = 0; i < all.size(); i += subListSize) {
            int toIndex = (i + subListSize) > all.size() ? all.size() : (i + subListSize);
            result.add(all.subList(i, toIndex));
        }
        return result;
    }

    public static List<byte[]> split(byte[] list, int targetSize) {
        List<byte[]> lists = new ArrayList<byte[]>();
        for (int i = 0; i < list.length; i += targetSize) {
            lists.add(Arrays.copyOfRange(list, i, Math.min(i + targetSize, list.length)));
        }
        return lists;
    }

    public static <T> List<T[]> split(T[] list, int targetSize) {
        List<T[]> lists = new ArrayList<T[]>();
        for (int i = 0; i < list.length; i += targetSize) {
            lists.add(Arrays.copyOfRange(list, i, Math.min(i + targetSize, list.length)));
        }
        return lists;
    }
}
