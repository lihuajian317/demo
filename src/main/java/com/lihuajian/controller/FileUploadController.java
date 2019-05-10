package com.lihuajian.controller;

import com.lihuajian.upload.component.IContentPublisher;
import com.lihuajian.utils.JsonUtils;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传控制器
 */
@RestController
@RequestMapping("system")
public class FileUploadController {

    @Resource
    private IContentPublisher contentPublisher;

    /**
     * MultipartFile 转 File
     * @param ins
     * @param file
     * @param length
     * @return
     */
    public static File multipartFileToFile(InputStream ins, File file,Long length) {
        File file1 = null;
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            int length2= Math.toIntExact(length);
            byte[] buffer = new byte[length2];
            //System.out.println(ins.read(buffer, 0,length2 ));
            if ((bytesRead = ins.read(buffer, 0,length2 )) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 上传文件到服务器
     * @param request
     * @return
     */
    @RequestMapping("upload")
    public String upload(MultipartHttpServletRequest request) {
        MultipartFile multipartFile = null;
        MultiValueMap<String, MultipartFile> multiFiles = request.getMultiFileMap();
        Map<String, Object> resultMap = new HashMap<>(10);
        try {
            System.out.println("------------------得到当前的classpath的绝对路径---------------------");
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
                    .replace("target/classes/", "");
            System.out.println("得到当前的classpath的绝对路径" + rootPath);

            System.out.println("------------------本地文件上传路径---------------------");
            String filePath = rootPath + "upload/images/";
            System.out.println("文件上传路径" + filePath);
            for (String key : multiFiles.keySet()) {
                //获取上传文件
                multipartFile = multiFiles.getFirst(key);

                //获取上传文件名称
                String fileName = multipartFile.getOriginalFilename();

                //获取上传文件后缀
                String suffixName = fileName.substring(fileName.lastIndexOf("."));

                //生成新文件名称
                String newFileName = System.currentTimeMillis() + suffixName;

                //图片存储到本地
                //FileUtils.copyInputStreamToFile(file.getInputStream(),new File(filePath+newFileName));

                //发送图片至服务器
                String imgPath = "/upload/images/" + newFileName;// 服务器上图片存储路径

                List<String> imgFiles = new ArrayList<String>();
                imgFiles.add(imgPath);

                //MultipartFile转到File  方式一
               /* File file = null;
                InputStream inputStream = multipartFile.getInputStream();
                file = new File(newFileName);
                File file1 = multipartFileToFile(inputStream, file,multipartFile.getSize());*/

                //MultipartFile转到File  方式二
                File file=File.createTempFile(System.currentTimeMillis()+"",suffixName);
                multipartFile.transferTo(file);

                this.contentPublisher.synchronousPublish(file, imgFiles);
                resultMap.put("url", imgPath);
                resultMap.put("result", true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.toJson(resultMap);
    }

    /**
     * 绝对路径删除文件
     * @return
     */
    @RequestMapping("deleteFile")
    public String deleteFile(){
        Map<String, Object> resultMap = new HashMap<>(10);
        try{
            contentPublisher.deleteFile("/home/upload/images/1557481000224.jpg");
            resultMap.put("status","删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonUtils.toJson(resultMap);
    }

    /**
     * 相对路径删除文件
     * @return
     */
    @RequestMapping("deleteRelativeFilePath")
    public String deleteRelativeFilePath(){
        Map<String, Object> resultMap = new HashMap<>(10);
        try{
            contentPublisher.deleteRelativeFilePath("/upload/images/1557481150453.jpg");
            resultMap.put("status","删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonUtils.toJson(resultMap);
    }

}
