package com.example.yilunwu.abcworry.utils;

/**
 * Created by yilunwu on 16/12/7.
 */

/**
 * 用来匹配字符串
 */
public class StringMacher {
    //value:item的文本
    //keyword:索引列表中的字符
    public static boolean match(String value,String keyword){
        //value和keyword的参数值都不能为null
        if (value==null||keyword==null){
            return false;
        }
        if (keyword.length()>value.length()){
            return false;
        }
        int i=0/*value的指针*/,j=0;/*keyword的指针*/
        do {
            /**
             * keyword=bc  value=abcde
             * keyword=bx  value=abcde
             */
             // j=0 ,i=1
            //  j=1 ,i=2
            //  j=2,i=3
            /**
             * j=0,i=1
             * j=1,i=2
             *
             */
            if (keyword.charAt(j)==value.charAt(i)){
                i++;
                j++;
            }else if(j>0){
                break;
            }else {
                i++;
            }
        }while (i<value.length()&&j<keyword.length());
        return (j==keyword.length())?true:false;
    }

}
