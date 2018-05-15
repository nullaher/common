package cn.nullah.common.http.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.tobato.fastdfs.FdfsClientConfig;
@Import(FdfsClientConfig.class)
@Configuration
public class FastDFSConfig {

}
