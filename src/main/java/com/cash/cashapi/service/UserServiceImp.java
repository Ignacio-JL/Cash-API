package com.cash.cashapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cash.cashapi.entity.Loan;
import com.cash.cashapi.entity.User;
import com.cash.cashapi.repository.LoanRepository;
import com.cash.cashapi.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{
	
	private final UserRepository userRepository;
	private final LoanRepository loanRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(Long idUser) {
		return userRepository.findById(idUser).orElseThrow(() -> {throw new RuntimeException();});
	}

	@Override
	public User updateUser(Long id, User user) {
		User userToUpdate = userRepository.findById(id).get();
		userToUpdate.setEmail(user.getEmail());
		userToUpdate.setFirstName(user.getFirstName());
		userToUpdate.setLastName(user.getLastName());
		return userRepository.save(userToUpdate);
	}

	@Override
	public boolean deleteUser(Long id) {
		try {			
			userRepository.deleteById(id);
			List<Loan> loans = loanRepository.findAll().stream().filter(loan -> loan.getUserId() == id).collect(Collectors.toList());
			for (Loan loan : loans) {
				loanRepository.deleteById(loan.getId());
			}
			return true;
		} catch (Exception e) {
			System.out.println("el error es:" + e);
			return false;
		}
		
	}

}
