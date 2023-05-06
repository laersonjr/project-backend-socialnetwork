package com.sysmap.laersonjr.socialnetwork.api.modelDTO.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseBodyDTO {

    private UserResumeCommentDTO user;
    private String commentary;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Integer likeCounts;

}
