package com.block.response;

/**
 * 调用结果
 * 
 * @author ligy
 * 
 */
import com.block.response.RespResult;

public class BalanceRespResult {

	//private RespResult commonResult; // 通用结果部分
	private String code; // 结果码

	private String description; // 结果描述
	
	private String amount; //金额

	protected BalanceRespResult() {
	};

	public BalanceRespResult(RespResult result, String amount) {
		//this.commonResult = result;
		this.code = result.getCode();
		this.description = result.getDescription();
		this.amount = amount;
	}

	public BalanceRespResult(BalanceRespResult result) {
		//this.commonResult = result.commonResult;
		this.code = result.code;
		this.description = result.description;
		this.amount = result.amount;
	}

	/*public RespResult getCommonResult() {
		return commonResult;
	}*/
	
	public String getAmount() {
		return amount;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}

	public void setCommonResult(RespResult result) {
		this.code = result.getCode();
		this.description = result.getDescription();
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		//return String.format("[code=%s, desc=%s，amount=%s]", commonResult.getCode(), commonResult.getDescription(), amount);
		return String.format("[code=%s, desc=%s，amount=%s]", code, description, amount);
	}
}
