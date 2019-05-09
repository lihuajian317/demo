package com.lihuajian.controller;

import com.lihuajian.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器
 */
@RestController
@RequestMapping("system")
public class FileUploadController {


    @RequestMapping("upload")
    public String upload(MultipartHttpServletRequest request) {
        MultipartFile file = null;
        MultiValueMap<String, MultipartFile> multiFiles = request.getMultiFileMap();
        Map<String, Object> resultMap = new HashMap<>(10);
        try {
            System.out.println("------------------得到当前的classpath的绝对路径---------------------");
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
                    .replace("target/classes/", "");
            System.out.println("得到当前的classpath的绝对路径"+rootPath);

            System.out.println("------------------文件上传路径---------------------");
            String filePath = rootPath + "images" + File.separator + "cbcec" + File.separator + "library"
                    + File.separator + "teacher" + File.separator;
            System.out.println("文件上传路径"+filePath);
            for (String key : multiFiles.keySet()) {
                //获取上传文件
                file = multiFiles.getFirst(key);

                //获取上传文件名称
                String fileName = file.getOriginalFilename();

                //获取上传文件后缀
                String suffixName = fileName.substring(fileName.lastIndexOf("."));

                //生成新文件名称
                String newFileName = System.currentTimeMillis() + suffixName;

                //图片存储到本地
                FileUtils.copyInputStreamToFile(file.getInputStream(),new File(filePath+newFileName));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.toJson(resultMap);
    }
}
