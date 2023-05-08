package com.sysmap.laersonjr.socialnetwork.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CommentResponseBodyDTO {

    private UUID id;
    private UserResume user;
    private String commentary;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Integer likeCounts;

}
