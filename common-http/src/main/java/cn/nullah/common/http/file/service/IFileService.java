package cn.nullah.common.http.file.service;

import cn.nullah.common.http.file.model.FileInf;
import cn.nullah.common.http.file.model.FileProcResult;
import cn.nullah.common.http.file.model.InputFile;

public interface IFileService {
	/**
	 * @note 保存文件
	 * @param inputFile
	 * @return 上传结果
	 */
	public FileProcResult save(InputFile inputFile);
	
	/**
	 * @note 获取文件绝对地址
	 * @param fileId 文件id
	 * @return 文件绝对地址
	 */
	public String getURIById(String fileId);
	
	/**
	 * @note 批量删除文件,多个逗号分割
	 * @param fileIdStr
	 * @return 删除的文件个数
	 */
	public int delete(String fileIdStr);
	
	/**
	 * @note  获取文件信息
	 * @param fileId
	 * @return 文件信息
	 */
	public FileInf getInf(String fileId);
}
