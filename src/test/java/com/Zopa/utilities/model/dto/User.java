package com.Zopa.utilities.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String memberId;
    private String firstName;
    private String lastName;
    private String firstLine;
    private String town;
    private String postCode;
    private String email;
    private String jobTitle;

}
