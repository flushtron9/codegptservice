package com.wimthackathon.codegptservice.io;

import java.util.List;

public class GetCompanyInfoResponse {
	
	private List<Error> errors;
	private String data;
	public List<Error> getErrors() {
		return errors;
	}
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
