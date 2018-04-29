package com.lms.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListUtilsTest {

    @Test
    public void testGetSubLists() throws Exception {
        List<Long> largeList = new ArrayList<Long>();
        for (int i = 0; i < 2003; i++) {
            largeList.add((long) i);
        }
        List<List<Long>> subLists = ListUtils.split(largeList);
        assertEquals(3, subLists.size());
        assertEquals(new Long(0), subLists.get(0).get(0));
        assertEquals(new Long(1000), subLists.get(1).get(0));
        assertEquals(new Long(2000), subLists.get(2).get(0));

        largeList.clear();
        for (int i = 0; i < 600; i++) {
            largeList.add((long) i);
        }
        subLists = ListUtils.split(largeList);
        assertEquals(1, subLists.size());

        largeList.clear();
        subLists = ListUtils.split(largeList);
        assertEquals(0, subLists.size());

        largeList.clear();
        for (int i = 0; i < 600; i++) {
            largeList.add((long) i);
        }
        subLists = ListUtils.split(largeList, 200);
        assertEquals(3, subLists.size());

        subLists = ListUtils.split(largeList, 100);
        assertEquals(6, subLists.size());

        subLists = ListUtils.split(largeList, 1000);
        assertEquals(1, subLists.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSubListsIllegalArgument() throws Exception {
        List<Long> largeList = new ArrayList<Long>();
        ListUtils.split(largeList, 0);
    }
}