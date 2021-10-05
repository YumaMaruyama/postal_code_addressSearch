package com.example.demo.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.ZipcodeDTO;

@Service
public class ZipcodeService {

	@Autowired
	@Qualifier("zipCodeSearchRestTemplate")
	RestTemplate restTemplate;

	//郵便番号検索API　リクエストURL
	private static final String url = "http://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";

	public ZipcodeDTO service(String zipcode) {
		System.out.println("zipcode  " + zipcode);

		return restTemplate.getForObject(url, ZipcodeDTO.class,zipcode);
	}

}
