package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.util.GmallFileUploadUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName FileUploadController.java
 * @Description TODO
 * @createTime 2022年08月22日 19:57:00
 */
@RestController
@Api(tags = "文件上传接口")
public class FileUploadController {
    // 文件上传后，服务器的访问地址
    // @Value 注解：表示从 YAML 配置文件读取数据，赋值给当前成员变量
    // 不做直接赋值的原因是：考虑到万一服务器地址发生变化，可以只修改配置文件不修改 Java 代码
    // 区别：修改配置文件不需要重新编译，修改 Java 代码需要重新编译、应用服务器也需要重新部署
    // 软编码：在 Java 代码中没有写死，动态读取配置文件
    // 硬编码：写死了
    @Value("${fileServer.url}")
    private String fileServerAddress;

    @RequestMapping("/admin/product/fileUpload")
    public Result<String> doFileUpload(@RequestPart("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            Result<String> result = Result.fail();
            result.setMessage("抱歉!您上传的文件为空文件!");
            return  result;
        }
        String originName = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        String fileServerStoragePath = GmallFileUploadUtil.doFileUpload(originName, bytes);
        String fileServerAccessPath = fileServerAddress+fileServerStoragePath;
        return Result.ok(fileServerAccessPath);

    }
}
