package com.Zopa.utilities.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostQuoteRequest {

    private int currentSalary;
    private int amountToBorrow;
    private int termLength;

}
