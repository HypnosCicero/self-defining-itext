package org.oceanT.tools;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;

public class HandlerModule {

    public<T> String startConformity(String str,T data){
        char[] chars = str.toCharArray();
        char[] charsResult = new char[0];
        //int charsLength = chars.length;
        for (int i = 0; i < chars.length; i++) {
            //预测环
            if (chars[i] == '{') {

                int strLength = betweenCurlyBraces(chars, i);
                charsResult = changeStr(chars, i, strLength, data);

                //i += (strLength - 1);
                i=-1;//性能不优，但能完成
                //  合并
                chars = new char[charsResult.length];
                for (int j = 0; j < charsResult.length; j++) {
                    chars[j] = charsResult[j];
                }
            }
        }

        String targetStr = str;
        if (charsResult.length != 0) {
            targetStr = String.valueOf(charsResult);
        }
        return targetStr;
    }

    public Integer expectLeftCurlyBrace(){
        return 0;
    }

    /**
     * 测量环 可以进行先一步预测值然后进行i的替换
     */
    public int betweenCurlyBraces(char[] chars,int jStar){
        int result = -1;
        for(int j = jStar; j <= chars.length;j++){
            if(chars[j] == '}'){
                result = j;
                break;
            }
        }
        return result;
    }

    //装配方法
    public <T> char[] changeStr(char[] chars,int iStar,int end,T data) {
        //尝试能否进行异步去做，但需要知道什么时候需要进行装配
        String prefixString = createPrefixString(chars,iStar);
        String midString=createValueString(chars,iStar,end,data);
        String suffixString = createSuffixString(chars,end);

        String resultString = prefixString+midString+suffixString;
        chars=resultString.toCharArray();
        return chars;
    }


    /**
     * 创建段落前缀字符串，没有什么特殊的地方
     * @param chars 需要传入的字符数组
     * @param iStar 需要传入的左花括号的索引
     * @return 返回段落前缀字符串
     */
    public String createPrefixString(char[] chars,int iStar){
        char[] charsPrefix = new char[iStar];
        for(int i =0 ; i < iStar;i++){
            charsPrefix[i]=chars[i];
        }
        return String.valueOf(charsPrefix);
    }


    /**
     * 创建中间字符串 本算法的核心部分 利用反射获取其值
     * @param chars 需要传入的原本的字符数据
     * @param iStar 左花括号起始位置
     * @param end 右花括号起始位置
     * @param data 数据
     * @return 返回中间替换的值
     * @param <T> 泛型表示任何数据的，但不包括list集合类型的嵌套泛型
     */
    public <T> String createValueString(char[] chars,int iStar,int end,T data){
        char[] charsMid = new char[end-iStar-1];
        for(int i = iStar+1, indexM=0; i < end;i++,indexM++){
            charsMid[indexM]=chars[i];
        }
        String midString = String.valueOf(charsMid);
        Field[] declaredFields = data.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            String name = field.getName();

            if( name.equals(midString) ) {
                field.setAccessible(true);
                Object methodValue = null;
                try {
                    methodValue = field.get(data);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                if("class java.util.Date".equals(field.getType().toString())) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                    String format = simpleDateFormat.format(methodValue);
                    methodValue=format;
                }
                midString = methodValue.toString();
                break;
            }
        }
        return midString;
    }


    /**
     * 创建后缀字符串 也没什么特殊的地方
     * @param chars 传入字符串
     * @param end 右花括号的索引
     * @return 返回后缀字符串
     */
    public String createSuffixString(char[] chars,int end){
        //后缀
        //5 - 10
        char[] charsSuffix = new char[chars.length-end-1];
        for(int i = end+1, indexS=0; i < chars.length ;i++,indexS++){
            charsSuffix[indexS]=chars[i];
        }
        return String.valueOf(charsSuffix);
    }

}
