package com.cash.cashapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<?> saveLoan(@RequestBody Loan loan) {
		
		return new ResponseEntity<>(loanService.saveLoan(loan), HttpStatus.CREATED);
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
		return new ResponseEntity<LoanResponse>(allLoansWithPagination, HttpStatus.OK);
//		}
//			
//		else{
//			allLoansWithPagination = loanService.findAllLoansWithPaginationAndFilter(offset, pageSize, userId);			
//			return new APIResponse<>(allLoansWithPagination.getPageSize(), allLoansWithPagination);
//		}
		
	}
	
	
	/*
	@GetMapping("/all/{id}")
	public ResponseEntity<?> getAll(@PathVariable("id") Long idLoan){
		return new ResponseEntity<>(loanService.findAllLoans().stream().filter(x -> x.getUserId() == idLoan).collect(Collectors.toList()), HttpStatus.OK);
		
	}*/
	
	/*
	@GetMapping
	public APIResponse<Page<Loan>> getLoansWithPagination(@RequestParam(value = "page") int offset, @RequestParam(value = "size") int pageSize, @RequestParam(value = "user_id", required = false) String userId){
		Page<Loan> allLoansWithPagination;
		
		if(userId == null) {
			allLoansWithPagination = loanService.findAllLoansWithPagination(offset, pageSize);
		}
		else {
			allLoansWithPagination = loanService.findAllLoansWithPaginationAndFilter(offset, pageSize, userId);
		}
			return new APIResponse<>(allLoansWithPagination.getSize(), allLoansWithPagination);
	}*/
	/*
	@GetMapping
	public APIResponse<Page<Loan>> getLoansWithPaginationAndFilter(@RequestParam(value = "page") int offset, @RequestParam(value = "size") int pageSize, @RequestParam(value = "user_id") int userId){
		Page<Loan> allLoansWithPagination = loanService.findAllLoansWithPaginationAndFilter(offset, pageSize, userId);
		return new APIResponse<>(allLoansWithPagination.getSize(), allLoansWithPagination);
	}*/
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getLoan(@PathVariable("id") Long idLoan) {
		return new ResponseEntity<>(loanService.getLoan(idLoan), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateLoan(@PathVariable("id") Long idLoan, @RequestBody Loan loan){
		return new ResponseEntity<>(loanService.updateLoan(idLoan, loan), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long idLoan){
		if(loanService.deleteLoan(idLoan))
		{
			return new ResponseEntity<>(loanService.deleteLoan(idLoan), HttpStatus.OK);			
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
