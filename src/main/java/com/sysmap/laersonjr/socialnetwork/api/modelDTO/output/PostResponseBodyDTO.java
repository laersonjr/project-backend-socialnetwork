package com.sysmap.laersonjr.socialnetwork.api.modelDTO.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PostResponseBodyDTO {

    private UUID id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private UserResumePostDTO user;
    private List<CommentResponseBodyDTO> comments;

}
