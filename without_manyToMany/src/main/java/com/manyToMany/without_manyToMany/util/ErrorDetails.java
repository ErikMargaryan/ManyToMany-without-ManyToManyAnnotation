package com.manyToMany.without_manyToMany.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
    private Date custom_timestamp;
    private String customException;
    private String custom_message;
    private String custom_details;
}
