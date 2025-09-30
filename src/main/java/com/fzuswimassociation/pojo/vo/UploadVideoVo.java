package com.fzuswimassociation.pojo.vo;

import com.fzuswimassociation.validator.UploadVideo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 接受上传的视频，视频封面图片
 *
 * @author 李泽聿
 * @since 2025-09-05 07:58
 */

@Data
public class UploadVideoVo {
    @NotNull(groups = {UploadVideo.class})
    private String token;
    @NotNull(groups = {UploadVideo.class})
    private MultipartFile video;
}
