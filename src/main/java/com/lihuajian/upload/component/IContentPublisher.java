package com.lihuajian.upload.component;

import java.io.File;
import java.util.List;


public interface IContentPublisher {

	/**
	 * 同步传输 同步文件到资源服务器
	 * @param file 要上传的文件
	 * @param files 相对路径文件列表，如：/img/a.jpg
	 */
	 void synchronousPublish(File file, List<String> files)throws Exception ;
	/**
	 * 异步传输 同步文件到资源服务器
	 * @param file 要上传的文件
	 * @param files 相对路径文件列表，如：/img/a.jpg
	 */
	 void asynchronousPublish(File file, List<String> files);
	
	
	 void deleteFile(String path);
	
	/**
	 * 相对路径
	 * @param relativeFilePath 相对路径
	 */
	 void deleteRelativeFilePath(String relativeFilePath);
}
