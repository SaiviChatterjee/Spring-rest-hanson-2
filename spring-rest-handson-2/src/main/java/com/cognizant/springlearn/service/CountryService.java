package com.cognizant.springlearn.service;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	public Country getCountry(String code) throws CountryNotFoundException {
		ApplicationContext context=new ClassPathXmlApplicationContext("country.xml");
		ArrayList<Country> countries=(ArrayList)context.getBean("countryList");
		for(Country country:countries) {
			if(country.getCode().equalsIgnoreCase(code)) {
				return country;
			}
		}
		throw new CountryNotFoundException();
	}
}
