package cn.nullah.common.http.file.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import cn.nullah.common.http.file.model.FileInf;
import cn.nullah.common.http.file.model.InputFile;
import cn.nullah.common.http.file.service.IFileService;

/**
 * @author nullah
 */
//@Controller
//@RequestMapping("/file")
public class GeneralFileController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Resource(name = "fileServiceSupport")
	IFileService fileService;

	@RequestMapping("/upload")
	public String fileUpload() {
		return "upload";
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> multiUpload(@RequestParam String platfmId,
			@RequestParam("file") MultipartFile[] files) {
		Map dataMap = new HashMap();
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				logger.info("收到上传的文件,名字为:{}", fileName);
				InputStream inputStream = null;
				try {
					inputStream = file.getInputStream();
					InputFile upFile = new InputFile(inputStream);
					upFile.setFileName(fileName);
					upFile.setPlatfmId(platfmId);
					upFile.setFileSize((long) file.getBytes().length);
					fileService.save(upFile);
					dataMap.put(upFile.getFileId(), fileName);
				} catch (IOException e) {
					logger.error("文件[" + fileName + "]上传出错", e);
				} finally {
					if (null != inputStream) {
						try {
							inputStream.close();
						} catch (IOException e) {
							// throw new ServiceException("文件上传失败", e);
							logger.error("文件[" + fileName + "]异常关闭", e);
						}
					}
				}
			}
		}
		return dataMap;
	}

	@RequestMapping("down/{fileId}")
	public String dowload(@PathVariable String fileId) {
		FileInf fileInf = fileService.getInf(fileId);
		if (null != fileInf) {
			return "redirect:" + fileInf.getFileKey();
		}
		return "404";
	}

}
