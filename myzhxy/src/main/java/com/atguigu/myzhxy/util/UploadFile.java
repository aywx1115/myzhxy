package com.atguigu.myzhxy.util;

import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class UploadFile {


    private static Map<String, Object> error_result = new HashMap<>();

    private static Map<String, Object> upload_result = new HashMap<>();


    private static Map<String, Object> uploadPhoto(MultipartFile photo, String path) {

        int MAX_SIZE = 20971520;

        String orginalName = photo.getOriginalFilename();

        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        if (photo.getSize() > MAX_SIZE) {
            error_result.put("success", false);
            error_result.put("msg", "Maximum upload image size is 20M");
            return error_result;
        }

        String[] suffixs = new String[]{".png", ".PNG", ".jpg", ".JPG", ".jpeg", ".JPEG", ".gif", ".GIF", ".bmp", ".BMP"};
        SuffixFileFilter suffixFileFilter = new SuffixFileFilter(suffixs);
        if (!suffixFileFilter.accept(new File(path + orginalName))) {
            error_result.put("success", false);
            error_result.put("msg", "Invalid file format, pls select picture file!");
            return error_result;
        }

        return null;
    }


    public static Map<String, Object> getUploadResult(MultipartFile photo, String dirPaht, String portraitPath) {

        if (!photo.isEmpty() && photo.getSize() > 0) {

            String orginalName = photo.getOriginalFilename();

            Map<String, Object> error_result = UploadFile.uploadPhoto(photo, dirPaht);
            if (error_result != null) {
                return error_result;
            }

            String newPhotoName = UUID.randomUUID() + "__" + orginalName;

            try {
                photo.transferTo(new File(dirPaht + newPhotoName));
                upload_result.put("success", true);
                upload_result.put("portrait_path", portraitPath + newPhotoName);
            } catch (IOException e) {
                e.printStackTrace();
                upload_result.put("success", false);
                upload_result.put("msg", "Upload failed! Server abnormal!");
                return upload_result;
            }

        } else {
            upload_result.put("success", false);
            upload_result.put("msg", "Upload failed! Cannot find picture!");
        }
        return upload_result;
    }
}
