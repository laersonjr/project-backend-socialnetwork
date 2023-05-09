package com.sysmap.laersonjr.socialnetwork.api.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Data
public class UserResume {

    private UUID id;
    private String nickName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResume that = (UserResume) o;
        return Objects.equals(id, that.getId());
    }

}
