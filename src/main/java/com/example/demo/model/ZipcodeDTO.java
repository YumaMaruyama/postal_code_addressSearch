package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ZipcodeDTO {

	int status;

	String message;

	List<ZipcodeDataDTO> results = new ArrayList<>();

}
