package com.cash.cashapi.mapper;

import org.springframework.stereotype.Component;

import com.cash.cashapi.dto.PageInfoResponse;

@Component
public class PageInfoResponseMapper {
	public PageInfoResponse pageInfoResponse2DTO(int offset, int pageSize, int total) {
		PageInfoResponse dto = new PageInfoResponse();
		dto.setPage(offset);
		dto.setSize(pageSize);
		dto.setTotal(total);
		return dto;
	}

}
