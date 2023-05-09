package com.sysmap.laersonjr.socialnetwork.api.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
public class UserResume {

    private UUID id;
    private String nickName;

}
