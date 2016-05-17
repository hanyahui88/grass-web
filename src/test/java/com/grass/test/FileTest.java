package com.grass.test;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;
import com.grass.common.utils.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 韩亚辉 on 2016/5/17.
 */
public class FileTest {
    @Test
    /**
     * 获取文件中的字符个数
     */
    public void testFile() throws IOException {
        FileInputStream fis = new FileInputStream("e:\\a.txt");
        BufferedReader dr = new BufferedReader(new InputStreamReader(fis));
        List<String> list = Lists.newArrayList();
        while (dr.readLine() != null) {
            list.add(dr.readLine());
        }
        Map<String, Integer> map = Maps.newHashMap();
        list.forEach((item) -> {
            if (null != map.get(item)) {
                map.put(item, map.get(item) + 1);
            } else {
                map.put(item, 1);
            }
        });
        List<FileTestEntity> fileTestEntities = Lists.newArrayList();
        map.forEach((x, y) -> {
            FileTestEntity fileTestEntity = new FileTestEntity();
            fileTestEntity.setKey(x);
            fileTestEntity.setValue(y);
            fileTestEntities.add(fileTestEntity);
        });
        Collections.sort(fileTestEntities);
        fileTestEntities.forEach((x) -> {
            System.out.println(x.getKey() + ":" + x.getValue());
        });
    }

    @Test
    /**
     *    密码规则参考Apple的密码规则（同时满足4个条件）：
     a).必须同时包含数字、字母、大写、小写字母。
     b).只能是ASCII可见符号。
     c).连续的字母、数字不能超过3个(比如1234,dcba都不可以)。
     d).相同字符连续不超过3个(比如aaaa，0000都不可以)。
     */
    public void testPasswordRule() {
        String email = "hanyahui88@163.com";
        String password = "fsd222sf23244431111cvAS123.fd";
        if (StringUtils.isBlank(email)) {
            System.out.println("email 不能为空");
        }
        if (StringUtils.isBlank(password)) {
            System.out.println("password 不能为空");
        }
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            System.err.println("email 不符合规则");
        }

        char[] chars = password.toCharArray();
        boolean ascii = true;
        boolean number = false;
        boolean upper = false;
        boolean lower = false;
        //ASCII可见符号。
        int minASCII = 32;
        int maxASCII = 126;
        //数字范围
        int minNumber = 48;
        int maxNumber = 57;
        //小写字母
        int minUpper = 65;
        int maxUpper = 90;
        //大写字母
        int minLower = 97;
        int maxLower = 122;
        int continuousCharCount=0;
        //存放字符，和相同字符的下标数组
        Map<Character, List<Integer>> map = Maps.newHashMap();
        //判断前两个规则
        for (int i = 0; i < chars.length; i++) {
            if (minASCII <= chars[i] && maxASCII >= chars[i]) {
                if (minNumber <= chars[i] && maxNumber >= chars[i]) {
                    number = true;
                }
                if (minUpper <= chars[i] && maxUpper >= chars[i]) {
                    upper = true;
                }
                if (minLower <= chars[i] && maxLower >= chars[i]) {
                    lower = true;
                }
                List<Integer> result = map.get(chars[i]);
                if (null != result) {
                    result.add(i);
                } else {
                    result = Lists.newArrayList();
                    result.add(i);
                }
                map.put(chars[i], result);

            } else {
                ascii = false;
            }
        }
        int sameCharCount = 0;
        if (!ascii) {
            System.err.println("密码只能是ASCII可见符号。");
        } else {
            if (!number || !upper || !lower){
                System.err.println("必须同时包含数字、字母、大写、小写字母");
            }
            //判断第三个规则
            for (int i = 0; i < chars.length-1; i++) {
                if(Character.getNumericValue(chars[i])+1==Character.getNumericValue(chars[i+1])){
                    ++continuousCharCount;
                }else{
                    continuousCharCount=0;
                }
            }
            //大于2 就行了，大于3就是5个以上连续的
            if(continuousCharCount>2){
                System.err.println("连续的字母、数字不能超过3个(比如1234,dcba都不可以)。");
            }
            //判断第四个规则
            for (Character item : map.keySet()) {
                List<Integer> list = map.get(item);
                if (list.size() > 3) {
                    //判断是否都连续
                    for (int i = 0; i < list.size()-1; i++) {

                        if (list.get(i + 1).equals(list.get(i) + 1)) {
                            ++sameCharCount;
                        } else {
                            sameCharCount = 0;
                        }
                    }
                }
            }
            //大于2 就行了，大于3就是5个以上连续的
            if (sameCharCount > 2) {
                System.err.println("相同字符连续不超过3个(比如aaaa，0000都不可以)。");
            }
        }
    }
}

class FileTestEntity implements Comparable<FileTestEntity> {
    private String key;
    private int value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(FileTestEntity o) {
        int result = 1;
        if (this.getValue() < o.getValue()) {
            result = 1;
        } else if (this.getValue() == o.getValue()) {
            //判断键
            result = this.getKey().compareTo(o.getKey());
        } else if (this.getValue() > o.getValue()) {
            result = -1;
        }
        return result;
    }
}
