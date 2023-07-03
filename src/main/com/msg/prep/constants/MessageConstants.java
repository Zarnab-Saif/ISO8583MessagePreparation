package com.msg.prep.constants;

import java.util.Arrays;

public interface MessageConstants {

	public static String messageHeader = "ISO000000000";

	public static int primaryBitmapSize = 64;

	public enum TransType {
		TRANS_TYPE_00("00"), TRANS_TYPE_01("01"), TRANS_TYPE_20("20"), TRANS_TYPE_31("31"), TRANS_TYPE_02("02");

		public String transType;

		private TransType(String transType) {
			this.transType = transType;
		}

		public String getTransType() {
			return this.transType;
		}

		public static TransType getByValue(String value) {
			return Arrays.stream(TransType.values())
					.filter(transType -> transType.getTransType().equalsIgnoreCase(value)).findFirst().get();
		}
	}

	public enum MessageLengthFormat {
		
		VISA("ASCII"), DEFAULT("ASCII");

		public String msgLengthFormat;

		private MessageLengthFormat(String msgLengthFormat) {
			this.msgLengthFormat = msgLengthFormat;
		}

		@Override
		public String toString() {
			return msgLengthFormat;
		}

		public String getMessageLengthFormat() {
			return this.msgLengthFormat;
		}

		public static MessageLengthFormat getByValue(String value) {
			return Arrays.stream(MessageLengthFormat.values())
					.filter(msgLengthFormat -> msgLengthFormat.getMessageLengthFormat().equalsIgnoreCase(value))
					.findFirst().get();
		}

	}

}
