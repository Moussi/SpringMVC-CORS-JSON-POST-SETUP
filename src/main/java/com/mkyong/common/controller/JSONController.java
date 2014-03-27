package com.mkyong.common.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkyong.common.model.Person;
import com.mkyong.common.model.Shop;

@Controller
@RequestMapping("/kfc/brands")
public class JSONController {

	@RequestMapping(value = "/mkyong/{name}", method = RequestMethod.GET)
	public @ResponseBody
	Shop getShopInJSON(@PathVariable String name) {

		Shop shop = new Shop();
		shop.setName(name);
		shop.setStaffName(new String[] { "mkyong1", "mkyong2" });

		return shop;

	}
	
	//this method response to POST request http://localhost/spring-mvc-json/rest/cont/person
	  // receives json data sent by client --> map it to Person object
	  // return Person object as json
	  @RequestMapping(value="person", method = RequestMethod.POST)
	  public @ResponseBody 
	  ResponseEntity<Person> post( @RequestBody final  Person person, HttpServletRequest request) {    		  
		  
		  HttpHeaders responseHeaders = new HttpHeaders();
		   
		  Enumeration headerNames = request.getHeaderNames();
		  while(headerNames.hasMoreElements()){
		   String nextElement = (String)headerNames.nextElement();
		   System.out.println(nextElement + "=" + request.getHeaders(nextElement));
		   responseHeaders.set(nextElement, request.getHeader(nextElement));
		  }
		   
		   
		  //populating the header required for CORS
		  responseHeaders.set("Access-Control-Allow-Origin", "*"); 
		   
		   
		  return new ResponseEntity<Person>(person, responseHeaders, HttpStatus.OK);
	  }

}