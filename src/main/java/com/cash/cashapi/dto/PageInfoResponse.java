package com.cash.cashapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageInfoResponse {
	@JsonProperty("page")
	private int page;
	@JsonProperty("size")
	private int size;
	@JsonProperty("total")
	private int total;
}
