package com.sysmap.laersonjr.socialnetwork.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InviteRequest {

    @NotNull
    private Boolean invite;

}
