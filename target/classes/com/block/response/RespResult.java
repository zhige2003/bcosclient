package com.block.response;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用结果
 * 
 * @author ligy
 * 
 */
public class RespResult {

	private String code; // 结果码

	private String description; // 结果描述

	protected RespResult() {
	};

	public RespResult(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public RespResult(RespResult result) {
		this.code = result.code;
		this.description = result.description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("[code=%s, desc=%s]", code, description);
	}

	/**
	 * 结果是否为成功
	 */
	public static boolean isOk(RespResult result) {
		return (result != null)
				&& (RespResult.Common.OK.getCode().equals(result.getCode()));
	}
	public static class Common {

		public static final RespResult OK = new RespResult("00000000", "Success");
		
		public static final RespResult URL_ERROR = new RespResult("00001000",
				"URL UNKNOWN");
		
		public static final RespResult APP_PERMISSION_DENYED = new RespResult("00010000",
				"APP PERMISSION DENYED");

		public static final RespResult NEWACCOUNTFAIL = new RespResult("00010001",
				"New account failed");

		public static final RespResult FREEZEACCOUNTFail = new RespResult("00010002",
				"Freeze Account failed");

		public static final RespResult BUYFAIL = new RespResult(
				"00010003", "Buy failed");

		public static final RespResult SELLFAIL = new RespResult(
				"00010004", "Sell failed");

		public static final RespResult GETACCOUNTBALANCEFAIL = new RespResult(
				"00010005", "GetAccountBalance failed");

		public static final RespResult GETBANKBALANCEFAIL = new RespResult(
				"00010006", "GetBankBalance failed");
		
		public static final RespResult PARAMFORMAT_ERROR = new RespResult(
				"00010007", "Paramformaterror error");

		public static final RespResult DEPLOYCONTRACT_ERROR = new RespResult(
				"00010008", "deployContract error");
		
		public static final RespResult UNKOWN_ERROR = new RespResult("11110000",
				"UNKNOWN Error");


	}
}
