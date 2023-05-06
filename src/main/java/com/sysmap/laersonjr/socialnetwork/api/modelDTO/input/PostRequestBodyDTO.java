package com.sysmap.laersonjr.socialnetwork.api.modelDTO.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PostRequestBodyDTO {

    private UUID id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}
