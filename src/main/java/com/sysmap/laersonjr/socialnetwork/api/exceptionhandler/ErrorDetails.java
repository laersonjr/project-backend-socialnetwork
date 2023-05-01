package com.sysmap.laersonjr.socialnetwork.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {

    private Integer status;
    private String title;
    private List<ProblemError> problemErrorList;

    @AllArgsConstructor
    @Getter
    public static class ProblemError{

        private String field;
        private String message;
    }

}
