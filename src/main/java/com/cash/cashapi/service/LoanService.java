package com.cash.cashapi.service;

import com.cash.cashapi.dto.LoanResponse;
import com.cash.cashapi.entity.Loan;

public interface LoanService {
	
	Loan saveLoan(Loan loan);
	LoanResponse findAllLoansWithPagination(int offset, int pageSize);
	LoanResponse findAllLoansWithPaginationAndFilter(int offset, int pageSize, Long idFilter);
	
	
}
