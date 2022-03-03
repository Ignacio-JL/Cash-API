package com.cash.cashapi.dto;

import java.util.List;

import com.cash.cashapi.entity.Loan;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanResponse {
	@JsonProperty("items")
	private List<Loan> items;
	
	@JsonProperty("pagging")
	private PageInfoResponse pagging;
	
	
}
