package com.sysmap.laersonjr.socialnetwork.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PostResponseBodyDTO {

    private UUID id;
    private UserResume user;
    private String title;
    private String content;
    private Integer likeCounts;
    private LocalDateTime createdDate;
    private List<CommentResponseBodyDTO> comments;


}
