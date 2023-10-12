package com.wimthackathon.codegptservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wimthackathon.codegptservice.service.StockInfoService;

@RestController
@RequestMapping("/codeGPT")
public class CodeGPTController {
	
	@Autowired
	StockInfoService stockInfoService;
	
	@RequestMapping(value="/getCompanyInfo", method = RequestMethod.POST, produces = {"application/json"}, 
			consumes = {"multipart/form-data"})
	public String getCompanyInfo(@RequestPart(value = "contentItems", required = true) List<MultipartFile> contentItems) {

		String stockInformation = stockInfoService.getStockInfo("WFC");
		return stockInformation;
	}

}
