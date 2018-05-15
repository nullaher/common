package cn.nullah.common.http.file.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.nullah.common.http.file.model.FileInf;

public interface FileInfRepository extends JpaRepository<FileInf, String> {
	FileInf findByFileId(String fileId);
}
