package com.mrbt.oa.mvc.controller.funds;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.funds.FundsKnowledgeService;
import com.mrbt.oa.mvc.vo.funds.FundsKnowledge;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.FtpUtils;
import com.mrbt.utils.ResponseUtils;

@Controller
@RequestMapping("funds/knowledge")
public class FundsKnowledgeController extends
		BaseController<FundsKnowledge, FundsKnowledgeService> {
	@Autowired
	private FtpUtils ftpUtils;
	private String knowledgeUrl = "knowledge";

	/**
	 * 保存
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("saveAndUpdate")
	@ResponseBody
	public Object saveAndUpdate(MultipartHttpServletRequest request,
			FundsKnowledge vo) {
		try {
			if (vo.getId() == null) {
				MultipartFile file = request.getFile("pictureImg");
				if (file != null) {
					BufferedImage img = ImageIO.read(file.getInputStream());
					if (img != null) {
						System.out.println("图片大小为：" + file.getSize());
						if (file.getSize() > AppCons.DEFAULT_IMAGE_SIZE * 1024) {
							return ResponseUtils
									.failure(
											ResponseUtils.ERROR_PARAM,
											"图片大小不能超过"
													+ AppCons.DEFAULT_IMAGE_SIZE
													+ "kb");
						}
						String fileName = UUID.randomUUID().toString();
						String type = file.getOriginalFilename().substring(
								file.getOriginalFilename().lastIndexOf("."));
						System.out.println("type = " + type);
						if (StringUtils.isNotBlank(type)) {
							fileName += type;
						} else {
							fileName += ".jpg";
						}
						ftpUtils.upload(file.getInputStream(), knowledgeUrl,
								fileName);
						String url = ftpUtils.getUrl() + knowledgeUrl + "/"
								+ fileName;
						vo.setPicture(url);
					} else {
						return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
								"上传文件错误，文件类型不是图片");
					}
				}
				MultipartFile file2 = request.getFile("textPdf");
				if (file2 != null) {
					String fileName = UUID.randomUUID().toString();
					String type = file2.getOriginalFilename().substring(
							file2.getOriginalFilename().lastIndexOf("."));
					System.out.println("type = " + type);
					if (StringUtils.isNotBlank(type) && ".pdf".equals(type)) {
						fileName += type;
						ftpUtils.upload(file2.getInputStream(), knowledgeUrl,
								fileName);
						String url = ftpUtils.getUrl() + knowledgeUrl + "/"
								+ fileName;
						vo.setText(url);
					} else {
						return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
								"请上传pdf格式文件");
					}
				}
				vo.setCreateTime(new Date());
				vo.setOnLine(AppCons.DEFAULT_ON_LINE);
				baseService.save(vo);
			} else {
				return update(request, vo);
			}
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}

	/**
	 * 更新
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	public Object update(MultipartHttpServletRequest request, FundsKnowledge vo) {
		try {
			if (vo.getId() == null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"参数错误，未找到记录");
			}
			FundsKnowledge record = baseService.listById(vo.getId());
			if (record == null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"参数错误，未找到记录");
			}
			MultipartFile file = request.getFile("pictureImg");
			if (file.getSize() > 0) {
				BufferedImage img = ImageIO.read(file.getInputStream());
				if (img != null) {
					System.out.println("图片大小为：" + file.getSize());
					if (file.getSize() > AppCons.DEFAULT_IMAGE_SIZE * 1024) {
						return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
								"图片大小不能超过" + AppCons.DEFAULT_IMAGE_SIZE + "kb");
					}
					String fileName = UUID.randomUUID().toString();
					String type = file.getOriginalFilename().substring(
							file.getOriginalFilename().lastIndexOf("."));
					System.out.println("type = " + type);
					if (StringUtils.isNotBlank(type)) {
						fileName += type;
					} else {
						fileName += ".jpg";
					}
					ftpUtils.upload(file.getInputStream(), knowledgeUrl,
							fileName);
					String url = ftpUtils.getUrl() + knowledgeUrl + "/"
							+ fileName;
					vo.setPicture(url);
				} else {
					return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
							"上传文件错误，文件类型不是图片");
				}
			}
			MultipartFile file2 = request.getFile("textPdf");
			if (file2.getSize() > 0) {
				String fileName = UUID.randomUUID().toString();
				String type = file2.getOriginalFilename().substring(
						file2.getOriginalFilename().lastIndexOf("."));
				System.out.println("type = " + type);
				if (StringUtils.isNotBlank(type) && ".pdf".equals(type)) {
					fileName += type;
					ftpUtils.upload(file2.getInputStream(), knowledgeUrl,
							fileName);
					String url = ftpUtils.getUrl() + knowledgeUrl + "/"
							+ fileName;
					vo.setText(url);
				} else {
					return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
							"请上传pdf格式文件");
				}
			}
			baseService.updateSelective(vo);
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}

	/**
	 * 修改在线状态
	 * 
	 * @param id
	 * @param online
	 * @return
	 */
	@RequestMapping("online")
	@ResponseBody
	public Object online(String id, String online) {
		if (StringUtils.isNotBlank(id) && NumberUtils.isNumber(id)
				&& StringUtils.isNotBlank(online)
				&& NumberUtils.isNumber(online)) {
			try {
				FundsKnowledge record = baseService
						.listById(new BigDecimal(id));
				if (record != null) {
					if ("0".equals(online)) {// 已发布
						record.setOnLine(AppCons.DEFAULT_ON_LINE);
					} else if ("1".equals(online)) {
						record.setOnLine((short) 0);
					}
					baseService.updateSelective(record);
				} else {
					return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
							"参数错误，未找到记录");
				}
				return ResponseUtils.success();
			} catch (Exception ex) {
				log.error(ex);
				return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
						ex.getMessage());
			}
		} else {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "参数错误");
		}
	}
}
