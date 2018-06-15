package com.mrbt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

public class FtpUtils {
	Logger log = MyUtils.getLogger(FtpUtils.class);
	/**
	 * 主机
	 */
	private String host;
	/**
	 * 端口
	 */
	private int port;
	/**
	 * 用户名
	 */
	private String user_name;
	/**
	 * 用户密码
	 */
	private String user_pwd;
	/**
	 * 客户端
	 */
	private FTPClient ftpClient;
	/**
	 * 访问的url根据路
	 */
	private String url;

	public FtpUtils() {
		ftpClient = new FTPClient();
	}

	/**
	 * 初始化
	 * 
	 * @throws SocketException
	 * @throws IOException
	 */
	private void init() throws SocketException, IOException {
		ftpClient.connect(host, port);
		ftpClient.login(user_name, user_pwd);
		ftpClient.enterLocalPassiveMode();
		ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
		ftpClient.setControlEncoding("UTF-8");
	}

	/**
	 * 创建文件夹
	 * 
	 * @param dir
	 * @return
	 */
	private boolean makeDirectory(String dir) {
		boolean flag = true;
		try {
			String dirs[] = dir.split("/");
			StringBuffer pathBuf = new StringBuffer();
			for (String path : dirs) {
				if (StringUtils.isNotBlank(path)) {
					pathBuf.append("/").append(path);
					if (!ftpClient.changeWorkingDirectory(pathBuf.toString())) {
						flag = ftpClient.makeDirectory(pathBuf.toString());
					}
					if (!flag) {
						return flag;
					}
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return flag;
	}

	/**
	 * FTP上传单个文件测试
	 */
	public void upload(InputStream is, String savePath, String saveName) {
		try {
			init();
			// 设置上传目录
			boolean flag = ftpClient.changeWorkingDirectory(savePath);
			if (!flag) {
				if (!makeDirectory(savePath)) {
					throw new RuntimeException("创建文件夹失败,未保存文件");
				}
				ftpClient.changeWorkingDirectory("/");
				ftpClient.changeWorkingDirectory(savePath);
			}
			ftpClient.setBufferSize(1024);
			// 设置文件类型（二进制）
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.storeFile(saveName, is);
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException("FTP客户端出错！", e);
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				log.error(e);
				throw new RuntimeException("关闭FTP连接发生异常！", e);
			}
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

}
