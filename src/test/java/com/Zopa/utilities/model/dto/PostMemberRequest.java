package com.Zopa.utilities.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMemberRequest {

    private String firstName;
    private String lastName;
    private PostMemberAddress address;
    private String emailAddress;
    private String jobTitle;

}
