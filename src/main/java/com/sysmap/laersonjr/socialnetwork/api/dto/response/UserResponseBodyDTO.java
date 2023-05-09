package com.sysmap.laersonjr.socialnetwork.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserResponseBodyDTO {

    private UUID id;
    private String email;
    private String nickName;
    @JsonFormat(pattern =  "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern =  "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedDate;
    private List<UserResume> friends;
    private List<UserResume> requestsFriends;

}
