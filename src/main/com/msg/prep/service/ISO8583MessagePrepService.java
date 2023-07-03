package com.msg.prep.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msg.prep.constants.MessageConstants;
import com.msg.prep.constants.MessageConstants.MessageLengthFormat;
import com.msg.prep.exception.InvalidFieldException;
import com.msg.prep.utils.MessageUtils;
import com.msg.prep.vo.MessageVO;

public class ISO8583MessagePrepService {

	public static String preparISO8583Message(String jsonRequest) throws IOException {

		if (isValidJSONRequest(jsonRequest)) {

			MessageVO messageVo = getMessageVoOfJSONRequest(jsonRequest);
			if (null != messageVo) {
				System.out.println("MessageVo prepared from JSON Request: " + messageVo.toString());
				if (isMandatoryFieldsPresent(messageVo)) {
					Map<Integer, String> dataElements = getDataElements(messageVo);

					return getFinalMessage(dataElements, messageVo);
				}
			}

		}

		return null;

	}

	private static boolean isValidJSONRequest(String jsonRequest){
		if (MessageUtils.isNotNullorEmptyString(jsonRequest)) {
			return true;
		} else {
			throw new InvalidFieldException("JSON Request is either null or empty");
		}
	}

	private static MessageVO getMessageVoOfJSONRequest(String jsonRequest)
			throws JsonMappingException, JsonProcessingException {
		MessageVO messageVo = new ObjectMapper().readValue(jsonRequest, MessageVO.class);

		return messageVo;
	}

	private static boolean isMandatoryFieldsPresent(MessageVO messageVo){
		if (MessageUtils.isNotNullorEmptyString(messageVo.getPan())
				&& MessageUtils.isNotNullorEmptyString(messageVo.getProcessingCode())

				&& MessageUtils.isNotNullorEmptyString(messageVo.getTransactionAmount())

				&& MessageUtils.isNotNullorEmptyString(messageVo.getBillingAmount())

				&& MessageUtils.isNotNullorEmptyString(messageVo.getSysTraceAuditNo())

				&& MessageUtils.isNotNullorEmptyString(messageVo.getCardExpiry())

				&& MessageUtils.isNotNullorEmptyString(messageVo.getMerchantType())

				&& MessageUtils.isNotNullorEmptyString(messageVo.getPosCondCode())

				&& MessageUtils.isNotNullorEmptyString(messageVo.getAcquiringInstId())

				&& MessageUtils.isNotNullorEmptyString(messageVo.getRetrievelRefNo())

				&& MessageUtils.isNotNullorEmptyString(messageVo.getCardAcceptorNameLoc())

				&& MessageUtils.isNotNullorEmptyString(messageVo.getTransCurrency())

				&& MessageUtils.isNotNullorEmptyString(messageVo.getBillCurrency())) {
			return true;
		} else {
			throw new InvalidFieldException("Mandatory field/s is/are missing");
		}

	}

	private static Map<Integer, String> getDataElements(MessageVO messageVo){

		Map<Integer, String> dataElements = new LinkedHashMap<Integer, String>();

		dataElements.put(2, messageVo.getPan());

		dataElements.put(3, messageVo.getProcessingCode());

		dataElements.put(4, messageVo.getTransactionAmount()); // if not in request then by default it's zero by JSON.

		dataElements.put(6, messageVo.getBillingAmount());

		dataElements.put(11, messageVo.getSysTraceAuditNo());

		dataElements.put(14, messageVo.getCardExpiry()); // TODO how to perform validation

		dataElements.put(18, messageVo.getMerchantType());

		dataElements.put(22, messageVo.getPosCondCode());

		dataElements.put(32, messageVo.getAcquiringInstId());

		dataElements.put(37, messageVo.getRetrievelRefNo());

		dataElements.put(43, messageVo.getCardAcceptorNameLoc());

		dataElements.put(49, messageVo.getTransCurrency());

		dataElements.put(51, messageVo.getBillCurrency());

		return dataElements;
	}

	private static String getFinalMessage(Map<Integer, String> dataElements, MessageVO messageVo){

		// Message Length , Headers, MTI, Bitmap, PAN and remaining DE
		StringBuilder finalMsg = new StringBuilder();
		if (null != dataElements && !dataElements.isEmpty()) {

			finalMsg.append(MessageConstants.messageHeader);

			finalMsg.append(messageVo.getMti(
					null != messageVo.getProcessingCode() ? messageVo.getProcessingCode().substring(0, 2) : null));

			finalMsg.append(getBitmapAsString(dataElements));

			for (Integer key : dataElements.keySet()) {
				finalMsg.append(dataElements.get(key));
			}

			int finalMsgLength = finalMsg.length();
			finalMsg.insert(0, getMessageFormatedLength(finalMsgLength, MessageLengthFormat.DEFAULT));

		} else {
			finalMsg.append("Data Elements map is empty or null.");
		}

		return finalMsg.toString();
	}

	private static String getMessageFormatedLength(int finalMsgLength, MessageLengthFormat network) {

		String networkMsgLenFormat = network.toString();

		String finalLength = "";

		switch (networkMsgLenFormat) {
		case "ASCII":
			finalLength = String.format("%04d", finalMsgLength);
			break;

		}

		return finalLength;

	}

	private static StringBuilder getBitmapAsString(Map<Integer, String> dataElements) {

		StringBuilder bitmapString = new StringBuilder();
		/*
		 * First bit = 0 as we aren't using secondary bitmap fields. 2nd Bit = 1 as MTI
		 * will always be present i.e. system generated
		 */
		bitmapString.append("01");
		if (null == dataElements.keySet()) {
			System.out.println("Data Elements KeySet is either null or empty.");
		} else {
			for (int i = 2; i <= MessageConstants.primaryBitmapSize; i++) {
				if (null != dataElements.get(i)) {
					bitmapString.append(1);
				} else {
					bitmapString.append(0);
				}
			}
		}

		return bitmapString;
	}

}
