package com.msg.prep.main;

import java.io.IOException;

import com.msg.prep.service.ISO8583MessagePrepService;

public class Main {

	public static void main(String[] args) throws IOException {

		String jsonRequest = "{" + "\"pan\": \"4324320000000001\"," + "\"processingCode\": \"000000\","
				+ "\"transactionAmount\": \"000000001000\"," + "\"billingAmount\": \"000000001000\","
				+ "\"sysTraceAuditNo\": 110000," + "\"cardExpiry\": \"3012\"," + "\"merchantType\": 5999,"
				+ "\"posCondCode\": \"00\"," + "\"acquiringInstId\": \"10000000001\","
				+ "\"retrievelRefNo\": \"123456789012\"," + "\"cardAcceptorNameLoc\": \"GasStation01 US\","
				+ "\"transCurrency\": 840," + "\"billCurrency\": 840" + "}";
		System.out.println("Message in ISO8583 format prepared from JSON Request: "
				+ ISO8583MessagePrepService.preparISO8583Message(jsonRequest));

	}
}
