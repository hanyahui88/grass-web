package com.grass.test;

import com.beust.jcommander.internal.Maps;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
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

        list.forEach((item) -> System.out.println(item));

        String[] list1 = {"张索", "发射点", "花费"};
        Arrays.sort(list1, CHINA_COMPARE);
        for (String item : list1) {
            System.out.println(item);
        }
    }

    @Test
    public void testSwap() {
        ////
        //: 2016/5/13
        //
        int aa = 11;
        int bb = 222;
        aa = aa ^ bb;
        bb = aa ^ bb;
        aa = aa ^ bb;
        System.out.println(aa);
        System.out.println(bb);
    }

    @Test
    public void testReadFile() throws IOException {
//        String fileName = "e:\\fk.mp4";
//        RandomAccessFile randomFile = null;
//        randomFile = new RandomAccessFile(fileName, "r");
//        long fileLength = randomFile.length();
//        System.out.println("文件大小:" + fileLength);
//        byte[] bytes = new byte[91];
//        int byteread = 0;
//        // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
//        // 将一次读取的字节数赋给byteread
//        while ((byteread = randomFile.read(bytes)) != -1) {
//            // System.out.write(bytes, 0, byteread);
//            System.out.println(byteread);
//        }
//        System.out.println(new String(bytes, "UTF-8"));
//        if (randomFile != null) {
//            randomFile.close();
//        }
    }

    @Test
    public void testMap() {
        Map<String, String> map = Maps.newHashMap();
        map.put("userName", "alvin");
        if (map.containsKey("userName")) {
            String userName = map.get("userName");
            System.out.println(userName);
        }
        //推荐使用下面这种，因为上面的会进行两次map的查找
        String userName = map.get("userName");
        if (StringUtils.isNotBlank(userName)) {
            System.out.println(userName);
        }
    }

    @Test
    public void testDate() throws InterruptedException {
        Date date = new Date();
        Thread.sleep(10000);
        Date date1 = new Date();
        System.out.println(date1.compareTo(date));
    }

}
