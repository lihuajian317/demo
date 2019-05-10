package com.lihuajian.configer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageServerConfig {

    @Value(value = "${sftp.host}")
    private String host; //ip地址

    @Value(value = "${sftp.username}")
    private String username; //用户名

    @Value(value = "${sftp.passwd}")
    private String passwd; //密码

    @Value(value = "${sftp.sync}")
    private boolean sync; //是否同步

    @Value(value = "${sftp.port}")
    private int port;  //端口

    @Value(value = "${sftp.sitepath}")
    public String sitepath;  //服务器路径


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSitepath() {
        return sitepath;
    }

    public void setSitepath(String sitepath) {
        this.sitepath = sitepath;
    }

    /*@Bean
    public CmsConfig cmsConfig() {
        CmsConfig cmsConfig = new CmsConfig();
        List<WebServerConfig> webServers = new ArrayList<WebServerConfig>();
        WebServerConfig webServer = new WebServerConfig();
        webServer.setHost(host);
        webServer.setPasswd(passwd);
        webServer.setPort(port);
        webServer.setSync(sync);
        webServer.setUsername(username);
        webServer.setSitepath(sitepath);
        webServers.add(webServer);
        cmsConfig.setWebServers(webServers);
        return cmsConfig;
    }*/
}
