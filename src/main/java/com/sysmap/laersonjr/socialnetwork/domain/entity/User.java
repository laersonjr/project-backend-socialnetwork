package com.sysmap.laersonjr.socialnetwork.domain.entity;

import com.sysmap.laersonjr.socialnetwork.api.dto.response.UserResume;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "users")
public class User {

    @EqualsAndHashCode.Include
    @Id
    private UUID id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @Field("nick_name")
    private String nickName;

    @Field("photo_url")
    private String photoUrl;

    @Field("friends")
    private List<UserResume> friends = new ArrayList<>();

    @Field("request_friends")
    private List<UserResume> requestsFriends = new ArrayList<>();

    @Field("created_date")
    private LocalDateTime createdDate;

    @Field("updated_date")
    private LocalDateTime updatedDate;

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    public void setUpdatedDate() {
        this.updatedDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResume that = (UserResume) o;
        return Objects.equals(id, that.getId());
    }



}
