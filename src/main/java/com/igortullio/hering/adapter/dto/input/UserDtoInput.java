package com.igortullio.hering.adapter.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class UserDtoInput extends AbstractDtoInput {

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Integer age;

    @NotBlank
    private String phone;

    @NotBlank
    @Email
    private String email;

}
