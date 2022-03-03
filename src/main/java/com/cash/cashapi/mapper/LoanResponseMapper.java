package com.cash.cashapi.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cash.cashapi.dto.LoanResponse;
import com.cash.cashapi.dto.PageInfoResponse;
import com.cash.cashapi.entity.Loan;

@Component
public class LoanResponseMapper {

	public LoanResponse LoanResponse2DTO(int offset, int totalPages, List<Loan> loans, PageInfoResponse pageInfoResponse) {
		LoanResponse dto = new LoanResponse();
		//Pregunto si se sobrepasa la cantidad de paginas posibles, si es asi, retorna null.
		dto.setItems(((offset) > totalPages)? null: loans);
		dto.setPagging(pageInfoResponse);
		return dto;
	}
}
