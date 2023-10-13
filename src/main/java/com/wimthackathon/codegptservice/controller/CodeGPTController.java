package com.wimthackathon.codegptservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wimthackathon.codegptservice.io.Error;
import com.wimthackathon.codegptservice.io.GetCompanyInfoResponse;
import com.wimthackathon.codegptservice.service.DetectLogoService;
import com.wimthackathon.codegptservice.service.StockInfoService;
import com.wimthackathon.codegptservice.util.CompanyTicker;

@RestController
@RequestMapping("/codeGPT")
public class CodeGPTController {
	
	@Autowired
	StockInfoService stockInfoService;
	
	@Autowired
	DetectLogoService detectLogoService;
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/getCompanyInfo", method = RequestMethod.POST, produces = {"application/json"}, 
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public @ResponseBody ResponseEntity<GetCompanyInfoResponse> getCompanyInfo(@RequestParam("file") MultipartFile image) {
		GetCompanyInfoResponse response = new GetCompanyInfoResponse();
		List<Error> errors = new ArrayList<Error>();
		try {
			if(image == null) {
				throw new RuntimeException("Image not present");
			}
			String company = detectLogoService.detectLogo(image);
			if(company == null) {
				throw new RuntimeException("Unable to detect company name in the image");
			}
			String ticker = CompanyTicker.getTicker(company);
			if(ticker == null) {
				throw new RuntimeException("Unable to map ticker for company name");
			}
			String stockInformation = stockInfoService.getStockInfo(ticker);
			response.setData(stockInformation);
			String tickerInfo = stockInfoService.getChartInfo(ticker);
			response.setChartInfo(tickerInfo);
		} catch (Exception e) {
			Error error = new Error();
			error.setMessage(e.getMessage());
			errors.add(error);
			response.setErrors(errors);
		}
		if(errors.isEmpty()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
