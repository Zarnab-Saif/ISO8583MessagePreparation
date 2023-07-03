package com.msg.prep.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msg.prep.constants.MessageConstants.TransType;
import com.msg.prep.exception.InvalidFieldException;
import com.msg.prep.utils.MessageUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageVO extends Throwable {

	private static final long serialVersionUID = 1L;

	String pan;

	String processingCode;

	String transactionAmount;

	String billingAmount;

	String sysTraceAuditNo;

	String cardExpiry;

	String merchantType;

	String posCondCode;

	String acquiringInstId;

	String retrievelRefNo;

	String cardAcceptorNameLoc;

	String transCurrency;

	String billCurrency;

	public String getMti(String transTypeStr) {
		String mti = "";
		TransType transType = TransType.getByValue(transTypeStr);
		switch (transType) {
		case TRANS_TYPE_00:
		case TRANS_TYPE_31: {
			mti = "0100";
			break;
		}
		case TRANS_TYPE_01:
		case TRANS_TYPE_20:
		case TRANS_TYPE_02: {
			mti = "0200";
			break;
		}
		default:
			// TODO
			mti = "0000";
		}

		return mti;
	}

	public String getPan(){
		if (MessageUtils.isNotNullorEmptyString(pan) && (pan.length() > 15 && pan.length() < 20)
				&& MessageUtils.isNumeric(pan)) {
			return pan;
		} else {
			throw new InvalidFieldException(
					"Pan is either null / empty or length is not between 15 and 20 characters or incorrect format");
		}
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getProcessingCode(){
		if (MessageUtils.isNotNullorEmptyString(processingCode) && processingCode.length() == 6
				&& MessageUtils.isNumeric(processingCode)) {
			return processingCode;
		} else {
			throw new InvalidFieldException(
					"Processing code is either null / empty or length is not equal to 6 or incorrect format");
		}
	}

	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	public String getTransactionAmount(){
		if (MessageUtils.isNotNullorEmptyString(transactionAmount) && transactionAmount.length() == 12
				&& MessageUtils.isNumeric(transactionAmount)) {
			return transactionAmount;
		} else {
			throw new InvalidFieldException(
					"Transaction Amount is either null / empty or length is not equal to 12 or incorrect format");
		}

	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getBillingAmount(){
		if (MessageUtils.isNotNullorEmptyString(billingAmount) && billingAmount.length() == 12
				&& MessageUtils.isNumeric(billingAmount)) {
			return billingAmount;
		} else {
			throw new InvalidFieldException(
					"Billing Amount is either null / empty or length is not equal to 12 or incorrect format");
		}
	}

	public void setBillingAmount(String billingAmount) {
		this.billingAmount = billingAmount;
	}

	public String getSysTraceAuditNo() {
		if (MessageUtils.isNotNullorEmptyString(sysTraceAuditNo) && sysTraceAuditNo.length() == 6
				&& MessageUtils.isNumeric(sysTraceAuditNo))

		{
			return sysTraceAuditNo;
		} else {
			throw new InvalidFieldException("Either SysTraceAuditNo length is not correct or incorrect format");
		}
	}

	public void setSysTraceAuditNo(String sysTraceAuditNo) {
		this.sysTraceAuditNo = sysTraceAuditNo;
	}

	public String getCardExpiry() {
		if (MessageUtils.isNumeric(cardExpiry)) {
			return cardExpiry;
		} else {
			throw new InvalidFieldException("cardExpiry format is incorrect");
		}
	}

	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public String getMerchantType(){
		if (MessageUtils.isNotNullorEmptyString(merchantType) && merchantType.length() == 4
				&& MessageUtils.isNumeric(merchantType)) {
			return merchantType;
		} else {
			throw new InvalidFieldException("Merchant type is not correct");
		}
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getPosCondCode(){
		if (MessageUtils.isNotNullorEmptyString(posCondCode) && posCondCode.length() == 2
				&& MessageUtils.isNumeric(posCondCode)) {
			return posCondCode;
		} else {
			throw new InvalidFieldException("Pos condition code is not correct");
		}
	}

	public void setPosCondCode(String posCondCode) {
		this.posCondCode = posCondCode;
	}

	public String getAcquiringInstId(){

		if (MessageUtils.isNotNullorEmptyString(acquiringInstId)
				&& (acquiringInstId.length() > 3 && acquiringInstId.length() < 12)
				&& MessageUtils.isNumeric(acquiringInstId)) {
			return acquiringInstId;
		} else {
			throw new InvalidFieldException("Acquiring institution Id is not correct");
		}
	}

	public void setAcquiringInstId(String acquiringInstId) {
		this.acquiringInstId = acquiringInstId;
	}

	public String getRetrievelRefNo(){
		if (MessageUtils.isNotNullorEmptyString(retrievelRefNo) && retrievelRefNo.length() < 13
				&& MessageUtils.isAlphaNumeric(retrievelRefNo)) {
			return retrievelRefNo;
		} else {
			throw new InvalidFieldException("Retrievel Reference Number is not correct");
		}
	}

	public void setRetrievelRefNo(String retrievelRefNo) {
		this.retrievelRefNo = retrievelRefNo;
	}

	public String getCardAcceptorNameLoc(){
		if (MessageUtils.isNotNullorEmptyString(cardAcceptorNameLoc) && cardAcceptorNameLoc.length() < 41
				&& MessageUtils.isAlphaNumericSpecial(cardAcceptorNameLoc)) {
			return acquiringInstId;
		} else {
			throw new InvalidFieldException("Card Acceptor Name Location is incorrect");
		}

	}

	public void setCardAcceptorNameLoc(String cardAcceptorNameLoc) {
		this.cardAcceptorNameLoc = cardAcceptorNameLoc;
	}

	public String getTransCurrency(){

		if (MessageUtils.isNotNullorEmptyString(transCurrency) && transCurrency.length() == 3
				&& MessageUtils.isNumeric(transCurrency)) {
			return transCurrency;
		} else {
			throw new InvalidFieldException("Transaction currency is incorrect");
		}
	}

	public void setTransCurrency(String transCurrency) {
		this.transCurrency = transCurrency;
	}

	public String getBillCurrency(){
		if (MessageUtils.isNotNullorEmptyString(billCurrency) && billCurrency.length() == 3
				&& MessageUtils.isNumeric(billCurrency)) {
			return billCurrency;
		} else {
			throw new InvalidFieldException("Billing currency is incorrect");
		}
	}

	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}

	@Override
	public String toString() {
		return "MessageVO [pan=" + pan + ", processingCode=" + processingCode + ", transactionAmount="
				+ transactionAmount + ", billingAmount=" + billingAmount + ", sysTraceAuditNo=" + sysTraceAuditNo
				+ ", cardExpiry=" + cardExpiry + ", merchantType=" + merchantType + ", posCondCode=" + posCondCode
				+ ", acquiringInstId=" + acquiringInstId + ", retrievelRefNo=" + retrievelRefNo
				+ ", cardAcceptorNameLoc=" + cardAcceptorNameLoc + ", transCurrency=" + transCurrency
				+ ", billCurrency=" + billCurrency + "]";
	}

}
