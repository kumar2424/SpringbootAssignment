package com.springmicroservices.rest.controller;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;

//import org.springframework.security.web.header.writers.CacheControlHeadersWriter;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springmicroservices.rest.exceptions.DuplicateArrayKeyException;
import com.springmicroservices.rest.exceptions.EmptyStringException;
import com.springmicroservices.rest.exceptions.NotAValidIndexException;
import com.springmicroservices.rest.exceptions.NotAValidInputException;
import com.springmicroservices.rest.exceptions.StringContainsNumberException;
import com.springmicroservices.rest.service.CheckTriangleService;
import com.springmicroservices.rest.service.CreateSingleArrayService;
import com.springmicroservices.rest.service.FibonacciService;
import com.springmicroservices.rest.service.ReverseStringService;


@RestController
public class AssignmentController {
	private static final Logger log = LoggerFactory.getLogger(AssignmentController.class);
	
	@Autowired
	private FibonacciService fibService;
	@Autowired
	private ReverseStringService reverseStringService;
	@Autowired
	private CheckTriangleService checkTriangleService;
	@Autowired
	private CreateSingleArrayService createSingleArrayService;
	
	//CacheControlHeadersWriter cacheControl=null;
	String regex="(.)*(\\d)(.)*";
	String triangleType=null;
	Integer fibonaccinumber=0;
	String reversedString=null;
	
	/*Controller for calculating a fibonacci number*/
	@RequestMapping(path="/api/Fibonacci/n={fibonacciIndex}",method=RequestMethod.GET)
	public ResponseEntity<Integer> calculateFibonacci(@PathVariable("fibonacciIndex") int fibNum){
		
		//cacheControl=new CacheControlHeadersWriter();
		
		if(fibNum<0) {
			throw new NotAValidIndexException("Index should be positive for calculating Fibonacci number");
		}
		/*Handle Fibonacci number for large numbers
		else if(fibNum>40) {
			
		}*/
		else {
		fibonaccinumber=fibService.fibonacciCalculator(fibNum);
		//return fibonaccinumber;
		
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).cacheControl(CacheControl.noCache()).body(fibonaccinumber);
		}
	}
	/*Controller for reversing input string sequence*/
	@RequestMapping(path="/api/ReverseWords/sentence={stringToBeReversed}",method=RequestMethod.GET)
	public ResponseEntity<String> reverseStr(@PathVariable("stringToBeReversed") String stringToBeReversed){
		Pattern pattern = Pattern.compile(regex);
		
		boolean containsNumber = pattern.matcher(stringToBeReversed).matches();
		if(stringToBeReversed.equals(null)||stringToBeReversed.equals("")) {
			throw new EmptyStringException("Input String cannot be empty");
		}else if(containsNumber) {
			throw new StringContainsNumberException("Input String should not contain Numbers");
		}		
		else {
		 reversedString=reverseStringService.reverseString(stringToBeReversed);
		//cacheControl=new CacheControlHeadersWriter();	
		
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).cacheControl(CacheControl.noCache()).body(reversedString);
		}
	}
	
	/*Controller for check type of triangle*/
	@RequestMapping(path="/api/TriangleType/a={side1}&b={side2}&c={side3}",method=RequestMethod.GET)
	public ResponseEntity<String> checkTriangle(@PathVariable("side1") int side1,@PathVariable("side2") int side2,@PathVariable("side3") int side3){
		
		//cacheControl=new CacheControlHeadersWriter();
		if(side1+side2<=side3) {
			throw new NotAValidInputException("Triangle is not possible with sent inputs");
		}else {
			triangleType=checkTriangleService.checkTriangleType(side1,side2,side3);
		}
		
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).cacheControl(CacheControl.noCache()).body(triangleType);
		
	}
	/*Controller for creating a single array*/
	@RequestMapping(value = "/api/makeonearray", method = RequestMethod.POST,consumes="application/json")	
	public ResponseEntity<String> makeOneArray(@RequestBody String inputArray) throws JSONException {
		String singleOutputArray = null;
		log.info("Inside Controller");
		try {
		JSONObject json=new JSONObject(inputArray);
		
		log.info("Calling array service");
		singleOutputArray = createSingleArrayService.createSingleArray(json).toString();		
		
		}catch(JSONException e) {
			if(e.getMessage().contains("Duplicate")) {
			throw new DuplicateArrayKeyException("JSON Array Keys cannot be Duplicate");
			}else {
				throw new DuplicateArrayKeyException("JSON Exception");
			}
		}
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
				.cacheControl(CacheControl.noCache()).body(singleOutputArray);
	}
	
	
}
