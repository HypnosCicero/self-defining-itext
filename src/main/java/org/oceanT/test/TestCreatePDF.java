package org.oceanT.test;

import org.oceanT.tools.CreatePDF;
import org.oceanT.tools.User;

import java.util.Date;

public class TestCreatePDF {
    public static void main(String[] args) {
        CreatePDF createPDF = new CreatePDF();
        User user = new User();
        user.setaName("甲方");
        user.setbName("乙方");
        user.setaIDNo(12334);
        user.setbIDNo(2345679);
        user.setFuzeren("负责人");
        user.setA(1.3);
        user.setbGnaration(true);
        user.setbGender("女");
        user.setStartDate(new Date());

        String modelFilePath="E:\\Work\\LearnJava\\CreteLand\\self-defining-itext\\src\\main\\resources\\contract.txt";
        String targetFilePath="E:\\Work\\LearnJava\\CreteLand\\self-defining-itext\\test.pdf";
        createPDF.createContract(user,modelFilePath,targetFilePath);
    }
}
