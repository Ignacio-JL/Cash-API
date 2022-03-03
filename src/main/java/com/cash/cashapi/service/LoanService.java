package com.cash.cashapi.service;

import com.cash.cashapi.dto.LoanResponse;
import com.cash.cashapi.entity.Loan;

public interface LoanService {
	
	LoanResponse findAllLoansWithPagination(int offset, int pageSize);
	LoanResponse findAllLoansWithPaginationAndFilter(int offset, int pageSize, Long idFilter);
	
	Loan saveLoan(Loan loan);
	
	Loan getLoan(Long idLoan);
	
	
	Loan updateLoan(Long id, Loan loan);
	
	boolean deleteLoan (Long id);
}
