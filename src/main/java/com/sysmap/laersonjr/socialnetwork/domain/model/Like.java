package com.sysmap.laersonjr.socialnetwork.domain.model;

import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResume;
import com.sysmap.laersonjr.socialnetwork.api.modelDTO.output.UserResumeLikeDTO;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
public class Like {

    private UserResume user;
    @Field("created_date")
    private LocalDateTime createdDate;

    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }


}
