package com.cash.cashapi;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cash.cashapi.entity.Loan;
import com.cash.cashapi.entity.User;
import com.cash.cashapi.service.LoanService;
import com.cash.cashapi.service.UserService;

@SpringBootApplication
public class CashApiApplication {
	
	@Autowired
	private UserService userService;
	@Autowired
	private LoanService loanService;

	public static void main(String[] args) {
		SpringApplication.run(CashApiApplication.class, args);
		
	}
	@Bean
	InitializingBean initUsersDatabase() {
	    return () -> {
	        userService.saveUser(new User(1L, "alfio@gmail.com", "alfio", "argento", null));
	        userService.saveUser(new User(2L, "pao@gmail.com", "paola", "argento", null));
	        userService.saveUser(new User(3L, "pepe@gmail.com", "jose", "argento", null));
	        userService.saveUser(new User(4L, "moni@gmail.com", "monica", "argento", null));
	        long auxId = 1;
	        
	        //Cada usuario tendra tantos prestamos como indique su id elevado al cuadrado
	        //Ej Usuario de Id 3 tendra 3^2 loans es decir 9
	        for(long i = 1 ; i <= 4; i++) {
	        	for(int j = 0; j < (i*i) ;j++) {
	        		Loan loan = new Loan(auxId ,9999.99, i);
	        		loanService.saveLoan(loan);
	        		auxId= auxId+1;
	        	}
	        }
	        
	      };
	   }

}
