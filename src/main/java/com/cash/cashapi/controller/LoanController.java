package com.cash.cashapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cash.cashapi.dto.LoanResponse;
import com.cash.cashapi.entity.Loan;
import com.cash.cashapi.service.LoanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {
	@Autowired
	private final LoanService loanService;
	
	@PostMapping
	public ResponseEntity<Loan> saveLoan(@RequestBody Loan loan) {
		try {
			return new ResponseEntity<Loan>(loanService.saveLoan(loan), HttpStatus.CREATED);			
		} catch (Exception e) {
			return new ResponseEntity<Loan>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//BORRAR
	@GetMapping
	public ResponseEntity<LoanResponse> getLoansWithPagination(@RequestParam(value = "page") int offset, @RequestParam(value = "size") int pageSize, @RequestParam(value = "user_id", required = false) String userId){
		LoanResponse allLoansWithPagination = new LoanResponse();
		
		if(userId == null) {
			allLoansWithPagination = loanService.findAllLoansWithPagination(offset, pageSize);
		}
		else {
			allLoansWithPagination = loanService.findAllLoansWithPaginationAndFilter(offset, pageSize, Long.parseLong(userId));
		}
		try {
			return new ResponseEntity<LoanResponse>(allLoansWithPagination, HttpStatus.OK);			
		} catch (Exception e) {
			return new ResponseEntity<LoanResponse>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
}
