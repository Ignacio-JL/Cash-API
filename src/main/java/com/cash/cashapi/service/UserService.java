package com.cash.cashapi.service;

import com.cash.cashapi.entity.User;

public interface UserService {
	User saveUser(User user);
	
	User getUser(Long idUser);
	
	User updateUser(Long id, User user);
	
	boolean deleteUser (Long id);
}
