package com.grass.test;

import org.junit.Test;

import java.text.Collator;
import java.util.*;

/**
 * Created by 韩亚辉 on 2016/5/11.
 */
public class CollectText {
    //使用拼音排序
    public static final Comparator CHINA_COMPARE = Collator.getInstance(Locale.CHINA);
    @Test
    public void textSort() {
        List<String> list = Arrays.asList("张索", "发射点", "花费");
        Collections.sort(list, CHINA_COMPARE);

        list.forEach((item)-> System.out.println(item));

        String[] list1 = {"张索", "发射点", "花费"};
        Arrays.sort(list1, CHINA_COMPARE);
        for (String item : list1) {
            System.out.println(item);
        }
    }
    @Test
    public void testSwap(){
        ////
        //: 2016/5/13
        //
        int aa=11;
        int bb=222;
        aa=aa^bb;
        bb=aa^bb;
        aa=aa^bb;
        System.out.println(aa);
        System.out.println(bb);

    }
}
