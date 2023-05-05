package com.sysmap.laersonjr.socialnetwork.domain.model;

import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

public class Like {

    private User user;
    @Field("created_date")
    private LocalDateTime createdDate;

    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }


}
