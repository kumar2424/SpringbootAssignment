package com.springmicroservices.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReverseStringService {
	private static final Logger log = LoggerFactory.getLogger(ReverseStringService.class);
	
	StringBuilder reverseWord=null;
	
	public String reverseString(String stringToBeReversed) {
		String[] wordsInSentence = stringToBeReversed.split("[[ ]*|[//.]]");	
		
		reverseWord = new StringBuilder();
		for (int i = 0; i < wordsInSentence.length; i++) {
			String wordInString=wordsInSentence[i];
			for (int j = wordsInSentence[i].length() - 1; j >= 0; j--) {				
				reverseWord=reverseWord.append(wordInString.charAt(j));
			}			
			reverseWord=reverseWord.append(" ");
		}
		log.info("Reveresed Word is "+reverseWord.toString());
		return reverseWord.toString();
	}
}
