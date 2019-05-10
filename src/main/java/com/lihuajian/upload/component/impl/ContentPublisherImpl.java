package com.lihuajian.upload.component.impl;


import com.lihuajian.configer.ImageServerConfig;
import com.lihuajian.upload.common.Ssh2FilePublisher;
import com.lihuajian.upload.component.IContentPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;


/**
 * 发布线程
 *
 * @author
 */
@Service
public class ContentPublisherImpl implements IContentPublisher {

    @Autowired
    private ImageServerConfig imageServerConfig;

    public ContentPublisherImpl() {
        super();
    }

    /**
     * 同步传输 同步文件到资源服务器
     *
     * @param file  要上传的文件
     * @param files 相对路径文件列表，如：/img/a.jpg
     */
    public void synchronousPublish(File file, List<String> files) throws Exception {
        if (imageServerConfig.isSync()) {
            Thread thread = new HostPublisher(imageServerConfig, file, files);
            thread.start();
            thread.join();
        }
    }

    /**
     * 异步传输 同步文件到资源服务器
     *
     * @param file  要上传的文件
     * @param files 相对路径文件列表，如：/img/a.jpg
     */
    public void asynchronousPublish(File file, List<String> files) {
        if (!imageServerConfig.isSync()) {
            Thread thread = new HostPublisher(imageServerConfig, file, files);
            thread.start();
        }
    }

    /**
     * 发布器
     *
     * @author ccl
     */
    private class HostPublisher extends Thread {
        private Logger log = LoggerFactory.getLogger(HostPublisher.class);
        private ImageServerConfig server;
        /**
         * 相对路径文件
         */
        private List<String> htmlFiles;

        private File file;

        public HostPublisher(ImageServerConfig server, File file, List<String> htmlFiles) {
            super();
            this.server = server;
            this.file = file;
            this.htmlFiles = htmlFiles;
        }

        @Override
        public void run() {
            Ssh2FilePublisher filePublisher = null;
            try {
                log.info("开始连接服务器，" + server);
                filePublisher = new Ssh2FilePublisher(server.getHost(), server.getPort(),
                        server.getUsername(), server.getPasswd(), server.getSitepath());
                filePublisher.getConnection();
                //装载文件
                for (int i = 0; i < htmlFiles.size(); i++) {
                    filePublisher.upload(file, server.getSitepath() + htmlFiles.get(i));
                }
                filePublisher.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            } catch (InternalError e) {
                e.printStackTrace();
            } finally {
                if (filePublisher != null) {
                    filePublisher.closeConnection();
                }
            }
        }
    }

    @Override
    public void deleteFile(String path) {
        Ssh2FilePublisher filePublisher = new Ssh2FilePublisher(imageServerConfig.getHost(), imageServerConfig.getPort(),
                imageServerConfig.getUsername(), imageServerConfig.getPasswd(), imageServerConfig.getSitepath());
        filePublisher.getConnection();
        filePublisher.deleteFile(path);
        filePublisher.closeConnection();
    }

    /**
     * 相对路径
     *
     * @param relativeFilePath 相对路径
     */
    public void deleteRelativeFilePath(String relativeFilePath) {
        Ssh2FilePublisher filePublisher = new Ssh2FilePublisher(imageServerConfig.getHost(), imageServerConfig.getPort(),
                imageServerConfig.getUsername(), imageServerConfig.getPasswd(), imageServerConfig.getSitepath());
        filePublisher.getConnection();
        filePublisher.deleteFile(imageServerConfig.getSitepath() + relativeFilePath);
        filePublisher.closeConnection();
    }
}
