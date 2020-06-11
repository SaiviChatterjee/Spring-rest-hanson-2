package com.cognizant.springlearn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@RestController
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/country")
	public Country getCountryIndia() {
		ApplicationContext context=new ClassPathXmlApplicationContext("country.xml");
		Country country=context.getBean("in",Country.class);
		return country;
	}
	@GetMapping("/countries")
	public List<Country> getAllCountries(){
		ApplicationContext context=new ClassPathXmlApplicationContext("country.xml");
		ArrayList<Country> countries=(ArrayList)context.getBean("countryList");
		((ClassPathXmlApplicationContext) context).close();
		return countries;
	}
	@GetMapping("/countries/{code}")
	public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
		return countryService.getCountry(code);
	}
}
