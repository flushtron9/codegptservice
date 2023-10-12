package com.wimthackathon.codegptservice.service;

import java.io.IOException;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

@Service
public class StockInfoService {
	
	public String getStockInfo(String symbol) throws IOException {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("https://query1.finance.yahoo.com/v7/finance/options/" + symbol);
		//HttpGet httpget = new HttpGet("https://query1.finance.yahoo.com/v8/finance/chart/" + symbol);
		HttpResponse httpresponse;
		try {
			httpresponse = httpclient.execute(httpget);
			Scanner sc = new Scanner(httpresponse.getEntity().getContent());
			StringBuffer sb = new StringBuffer();
			while (sc.hasNext()) {
				sb.append(sc.next());
			}
			result = sb.toString();
			System.out.println(result);
			result = result.replaceAll("<[^>]*>", "");
		} catch (ClientProtocolException e) {
			System.out.println(e.getMessage());
			throw e;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return result;
	}

}
