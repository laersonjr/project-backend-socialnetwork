package com.sysmap.laersonjr.socialnetwork.api.dto.response;

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
    private LocalDateTime createdDate;
    private List<UserResume> friends;
    private List<UserResume> requestsFriends;

}
