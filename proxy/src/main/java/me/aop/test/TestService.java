package me.aop.test;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TestService {

	private final TestRepository testRepository;

	@Trace
	public void req(String itemId) {
		testRepository.save(itemId);
	}
}
