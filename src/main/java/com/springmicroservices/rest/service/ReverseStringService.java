package com.springmicroservices.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReverseStringService {
	private static final Logger log = LoggerFactory.getLogger(ReverseStringService.class);
	
	StringBuilder reverseWord = new StringBuilder();	
	
	public String reverseString(String stringToBeReversed) {
		String[] wordsInSentence = stringToBeReversed.split("[[ ]*|[//.]]");	
		
		
		for (int i = 0; i < wordsInSentence.length; i++) {
			String wordInString=wordsInSentence[i];		
			log.info("Word array is ", wordsInSentence[i]);
			
			for (int j = wordsInSentence.length - 1; j >= 0; j--) {				
				reverseWord=reverseWord.append(wordInString.charAt(j));
			}			
			reverseWord=reverseWord.append(" ");
		}
		log.info(reverseWord.toString());
		return reverseWord.toString();
	}
}
