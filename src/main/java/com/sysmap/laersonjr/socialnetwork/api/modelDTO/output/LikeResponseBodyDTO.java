package com.sysmap.laersonjr.socialnetwork.api.modelDTO.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LikeResponseBodyDTO {

    private UserResume user;
    private LocalDateTime createdDate;

}
