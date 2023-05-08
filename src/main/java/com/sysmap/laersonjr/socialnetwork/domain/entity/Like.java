package com.sysmap.laersonjr.socialnetwork.domain.entity;

import com.sysmap.laersonjr.socialnetwork.api.dto.response.UserResume;
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
