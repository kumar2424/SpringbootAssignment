package com.springmicroservices.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CheckTriangleService {
	private static final Logger log = LoggerFactory.getLogger(CheckTriangleService.class);

	public String checkTriangleType(int side1,int side2,int side3) {
		String triangletype = null;
		
		if((side1==side2) && (side2==side3))
        {
			triangletype="Equilateral";
        }
        //To check to see if it is an Isosceles triangle (2 of the same size and 1 different)
        else if ((side1 == side2) & (side2 != side3) || (side2 == side3) & (side3!= side1))
        {
        	triangletype="Isosceles";            
        }//else if

		//To check to see if it is a Scalene triangle (3 different sizes)
        else if((side1 != side2) & (side2 != side3))
        {
        	triangletype="Scalene";
        }
		log.info("Triangle type is ",triangletype);
		return triangletype;
	}
}
