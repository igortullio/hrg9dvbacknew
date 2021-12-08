package com.igortullio.hering.adapter.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoOutput extends AbstractDtoOutput {

    private String name;
    private Integer age;
    private String phone;
    private String email;

}
