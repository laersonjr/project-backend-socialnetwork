package com.sysmap.laersonjr.socialnetwork.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern =  "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern =  "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedDate;
    private Integer likeCounts;

}
