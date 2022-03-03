package com.cash.cashapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cash.cashapi.dto.LoanResponse;
import com.cash.cashapi.dto.PageInfoResponse;
import com.cash.cashapi.entity.Loan;
import com.cash.cashapi.entity.User;
import com.cash.cashapi.mapper.LoanResponseMapper;
import com.cash.cashapi.mapper.PageInfoResponseMapper;
import com.cash.cashapi.repository.LoanRepository;
import com.cash.cashapi.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanServiceImp implements LoanService{
	
	private final LoanRepository loanRepository;
	private final UserRepository userRepository;
	private final LoanResponseMapper loanResponseMapper;
	private final PageInfoResponseMapper pageInfoResponseMapper;
	
	@Override
	public Loan saveLoan(Loan loan) {
		
		try {
			User userLoan = userRepository.findById(loan.getUserId()).get();
			userLoan.getLoans().add(loan);
				userRepository.save(userLoan);
			} catch (Exception e) {
				System.out.println("El error es: "+e);
			}
			
			return loanRepository.save(loan);
		
	}

	@Override
	public Loan getLoan(Long idLoan) {
		return loanRepository.findById(idLoan).orElseThrow(() -> {throw new RuntimeException();});
	}

	@Override
	public Loan updateLoan(Long id, Loan loan) {
		Loan loanToUpdate = loanRepository.findById(id).get();
		loanToUpdate.setTotal(loan.getTotal());
		//omito actualizar el propietario del prestamo
		return loanRepository.save(loanToUpdate);
	}

	@Override
	public boolean deleteLoan(Long id) {
		try {
			loanRepository.deleteById(id);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public LoanResponse findAllLoansWithPagination(int offset, int pageSize) {
		//Asumo el valor minimo de pagina como 1 
		if(offset <= 0)
			offset = 1;
		
		//Incremento y decremento offset para tener una mejor respuesta al usuario, tambien el desafio especifica la primer pagina es la 1 y no 0
		Page<Loan> loans = loanRepository.findAll(PageRequest.of((offset-1), pageSize));
		
		PageInfoResponse pageInfoResponse = new PageInfoResponse();
		LoanResponse loanResponse = new LoanResponse();
		
		pageInfoResponse = pageInfoResponseMapper.pageInfoResponse2DTO(offset, pageSize, (int)loans.getTotalElements());
		loanResponse = loanResponseMapper.LoanResponse2DTO(offset, loans.getTotalPages(), loans.getContent(), pageInfoResponse);

		return loanResponse;

	}

	@Override
	public LoanResponse findAllLoansWithPaginationAndFilter(int offset, int pageSize, Long idFilter) {
		if(offset <= 0)
			offset = 1;
		
		PageInfoResponse pageInfoResponse = new PageInfoResponse();
		LoanResponse loanResponse = new LoanResponse();
		
		List<Loan> listLoans = loanRepository.findAll().stream().filter(loan -> loan.getUserId() == idFilter ).collect(Collectors.toList());
		
		PagedListHolder<Loan> plhLoan = new PagedListHolder<Loan>(listLoans);
		//Incremento y decremento offset para tener una mejor respuesta al usuario, tambien el desafio especifica la primer pagina es la 1 y no 0 
		plhLoan.setPageSize(pageSize);
		plhLoan.setPage(offset-1);
		
		pageInfoResponse = pageInfoResponseMapper.pageInfoResponse2DTO(offset, pageSize, listLoans.size());
		loanResponse = loanResponseMapper.LoanResponse2DTO(offset, plhLoan.getPageCount(), plhLoan.getPageList(), pageInfoResponse);

		return loanResponse;
	}
	

	
}
