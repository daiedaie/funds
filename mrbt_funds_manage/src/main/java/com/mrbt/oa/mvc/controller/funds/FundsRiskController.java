package com.mrbt.oa.mvc.controller.funds;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.funds.FundsRiskService;
import com.mrbt.oa.mvc.vo.funds.FundsKnowledge;
import com.mrbt.oa.mvc.vo.funds.FundsRisk;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.ReadExcel;
import com.mrbt.utils.ResponseUtils;

@Controller
@RequestMapping("funds/risk")
public class FundsRiskController extends
		BaseController<FundsRisk, FundsRiskService> {
	
	private static String uploadFilePath = "/home/www/temp_upload_file/";

	@RequestMapping("upload")
	@ResponseBody
	public Object upload(MultipartHttpServletRequest request) throws Exception {
		try {
			MultipartFile file = request.getFile("excelFile");
			System.out.println("file长度" + file.getSize());
			String fileName = file.getOriginalFilename();
			String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
			System.out.println("后缀名" + prefix);
			if (file.getSize() <= 0
					|| (!("xls".equals(prefix) || "xlsx".equals(prefix)))) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"请上传excel文件");
			}

			InputStream is = file.getInputStream();
			String path = request.getSession().getServletContext()
					.getRealPath("");
			uploadFilePath = path + File.separator + "temp";
			// 如果不存在该文件夹，则创建
			File tempFileDir = new File(uploadFilePath);
			if (!tempFileDir.exists()) {
				tempFileDir.mkdirs();
				System.out.println("已创建该文件夹");
			}
			// 如果服务器已经存在和上传文件同名的文件，则输出提示信息
			File tempFile = new File(uploadFilePath + File.separator + fileName);
			if (tempFile.exists()) {
				boolean delResult = tempFile.delete();
				System.out.println("删除已存在的文件：" + delResult);
			}
			// 开始保存文件到服务器
			if (!fileName.equals("")) {

				FileOutputStream fos = new FileOutputStream(uploadFilePath
						+ File.separator + fileName);

				byte[] buffer = new byte[8192]; // 每次读8K字节
				int count = 0;
				// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count); // 向服务端文件写入字节流
				}
				fos.close(); // 关闭FileOutputStream对象
				is.close(); // InputStream对象
			}

			try {
				this.baseService.deleteByExample(new FundsRisk());
				List<FundsRisk> list = ReadExcel.readExcel(uploadFilePath
						+ File.separator + fileName);
				System.out.println(list.size()+"........");
				this.baseService.batchInsertFundsRisk(list);
				return ResponseUtils.success();
			} catch (Exception ex) {
				log.error(ex);
				return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
						ex.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}
}
