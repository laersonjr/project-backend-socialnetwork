package com.sysmap.laersonjr.socialnetwork.api.modelDTO.output;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResumePostDTO {

    private UUID id;
    private String nickName;

}