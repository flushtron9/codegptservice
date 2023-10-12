package com.wimthackathon.codegptservice.util;

import org.apache.commons.text.similarity.JaroWinklerDistance;

public enum CompanyTicker {
    WELLSFARGO("wellsfargo", "WFC"),
    COCOCOLA("cocacola", "KO");

    private final String company; 
    private final String ticker;
    CompanyTicker(String company, String ticker) {
        this.company = company;
        this.ticker = ticker;
    }
    public String company() { return company; }
    public String ticker() { return ticker; }
    public static String getTicker(String company) {
    	String t = null;
    	String c = company.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
    	JaroWinklerDistance d = new JaroWinklerDistance();
    	for(CompanyTicker ct : CompanyTicker.values()) {
    		double distance = d.apply(c, ct.company());
    		if(distance < 0.20) {
    			t = ct.ticker();
    		}
    	}
    	return t;
    }
}
