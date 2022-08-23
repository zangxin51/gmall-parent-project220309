package com.atguigu.gmall.product;

import com.atguigu.gmall.product.util.GmallFileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName GmallProductTest.java
 * @Description TODO
 * @createTime 2022年08月22日 19:25:00
 */
@SpringBootTest
@Slf4j
public class GmallProductTest {

    @Test
    public void testUpload() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("src/实教.png"));
        int available = fileInputStream.available();
        // 文件字节数有值说明读取文件路径正确
        log.info(String.valueOf(available));
        System.err.println(available);


        // 创建一个字节数组，用来接收上传的文件内容
        byte[] bytes = new byte[available];
        // 把读取到的文件数据存入字节数组
        fileInputStream.read(bytes);
        // // 根据字节数组执行文件上传
        String fileAccessPath = GmallFileUploadUtil.doFileUpload("实教.png", bytes);
        log.info(fileAccessPath);

    }
}
