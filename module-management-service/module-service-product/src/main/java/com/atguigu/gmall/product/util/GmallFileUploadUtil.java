package com.atguigu.gmall.product.util;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName GmalFileUploadUtil.java
 * @Description TODO
 * @createTime 2022年08月22日 19:14:00
 */
public class GmallFileUploadUtil {
    private static StorageClient1 storageClient1;

    static {

        try {

            String file = GmallFileUploadUtil.class.getResource("/tracker.conf").getFile();

            ClientGlobal.init(file);

            TrackerClient trackerClient = new TrackerClient();

            TrackerServer trackerServer = trackerClient.getConnection();

            storageClient1 = new StorageClient1(trackerServer, null);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }

    public synchronized static String doFileUpload(String originalFileName, byte[] fileBytes) {

        try {
            String extension = FilenameUtils.getExtension(originalFileName);

            return storageClient1.upload_appender_file1(fileBytes, extension, null);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        return null;
    }
}
