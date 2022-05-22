package com.Zopa.utilities.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMemberAddress {

    private String firstLine;
    private String town;
    private String postCode;

}
