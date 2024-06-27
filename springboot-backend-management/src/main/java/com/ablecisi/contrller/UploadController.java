package com.ablecisi.contrller;

import com.ablecisi.entity.Result;
import com.ablecisi.util.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * itheima_web_project
 * com.ablecisi.contrller
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/4/30
 * 星期二
 * 下午6:10
 */
@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("上传文件, 参数: image = {}", image);

        String url = aliOSSUtils.upload(image);

        log.info("上传文件完成, 返回url: {}", url);
        return Result.success(url);
    }
}
