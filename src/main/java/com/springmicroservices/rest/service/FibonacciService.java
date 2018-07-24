package com.springmicroservices.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FibonacciService {

	private static final Logger log = LoggerFactory.getLogger(FibonacciService.class);
	
	public static int calculateFibonacci(int fibonacciNumber) {
		
		if(fibonacciNumber ==1||fibonacciNumber ==2) {
			return 1;
		}
		return calculateFibonacci(fibonacciNumber-1)+calculateFibonacci(fibonacciNumber-2);
	}
	
	public Integer fibonacciCalculator(int fibonacciNumber) {
		int fibonacciValue=0;
		log.info("Calculating fibonacci series upto ",fibonacciNumber);
		for(int i=1;i<=fibonacciNumber;i++) {
			fibonacciValue= calculateFibonacci(i);
		}
		log.info("Calculated fibonacci number is ",fibonacciValue);
		return fibonacciValue;
		
	}
}
