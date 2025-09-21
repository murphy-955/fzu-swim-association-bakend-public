package com.fzuswimassociation.pojo.vo;

import com.fzuswimassociation.validator.UploadVideo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
    private byte[] video;
    @NotNull(groups = {UploadVideo.class})
    private String title;
    private byte[] videoPreviewImg;
    private String content;
}
