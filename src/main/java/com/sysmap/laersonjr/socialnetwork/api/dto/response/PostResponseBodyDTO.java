package com.sysmap.laersonjr.socialnetwork.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern =  "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern =  "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedDate;
    private List<CommentResponseBodyDTO> comments;


}
