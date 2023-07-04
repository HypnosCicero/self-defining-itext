package org.oceanT.tools;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class CreatePDF {



    public <T> void createContract(T data,String modelFilePath,String targetFilePath){

        //创建pdf
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, Files.newOutputStream( Paths.get(targetFilePath)));
            document.open();
            // 文字字体格式
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            //创建头字体样式
            // 构造函数第二个参数是字体大小 第三个参数是加粗、倾斜、加下划线等属性
            Font fontChinese = new Font(baseFont, 13, Font.NORMAL);

            //读取文件

            //注入自己所写的handlerModule
            HandlerModule handlerModule = new HandlerModule();
            //
            BufferedReader br = Files.newBufferedReader(Paths.get(modelFilePath));
            String str="";
            Boolean titleIndex=false;
            while((str = br.readLine()) != null) {
                String targetStr=null;
                Font selfFont=null;
                if (titleIndex) {
                    targetStr = handlerModule.startConformity(str, data);
                    selfFont = fontChinese;
                } else {
                    targetStr=str;
                    // 创建头部的font
                    Font touFont = new Font(baseFont);
                    //给头字体样式赋值大小
                    touFont.setSize(32);
                    selfFont=touFont;
                    titleIndex = true;
                }
                if("".equals(targetStr)) {
                    targetStr=" ";
                }
                document.add(new Paragraph(targetStr, selfFont));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            document.close();
        }
    }



}
