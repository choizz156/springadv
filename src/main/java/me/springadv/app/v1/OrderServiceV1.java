package me.springadv.app.v1;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceV0 {

	private final OrderRepositoryV0 repository;

	public void orderItem(String itemId) {
		repository.save(itemId);
	}
}
