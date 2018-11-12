package com.block.service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.math.BigInteger;

import org.apache.logging.log4j.LogManager;
//import org.apache.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.bcos.goldtoken.sample.EvidenceData;
import com.block.service.app.BcosApp;
import com.block.servlet.BlockChainServlet;
import com.block.constants.BlockServiceConstants;
//import com.block.service.app.TransactionReceipt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonIOException;
//import com.google.gson.JsonSyntaxException;
import com.block.response.RespResult;
import com.block.response.BalanceRespResult;
import com.block.utils.Util;
//import com.ebupt.wfc.service.impl.WfcCommonService;

import java.util.Map;

public class FiscoBcosService {
	private static final Map<String, Object> bankNameMap;  
    static  
    {  
    	bankNameMap = new HashMap<String, Object>();  
    	bankNameMap.put("CITIC", 1);  
    	bankNameMap.put("Industrial", 2);  
    	bankNameMap.put("ICBC", 3);  
    	bankNameMap.put("CCB", 4);  
    }
    private static final Map<String, Object> investmentTypeMap;  
    static  
    {  
    	investmentTypeMap = new HashMap<String, Object>();  
    	investmentTypeMap.put("Deposit", 1);  
    	investmentTypeMap.put("Current", 2);  
    } 
    

    private static String contractAddrName = "contractAddress";
    private static String accountAddrName = "accountAddress";
    private static String opTypeName = "opType";
    private static String bankNameName = "bankName";
    private static String investmentTypeName = "investmentType";
    private static String amountName = "amount";
	private static Logger logger = LogManager.getLogger(FiscoBcosService.class.getName());
	
	//private static BcosApp bcosapp = new BcosApp();
	private BcosApp bcosapp;
	
	public FiscoBcosService() throws Exception {
		//bcosapp = new BcosApp();
		/* boolean configure = bcosapp.loadConfig();
		 if (!configure) {
			logger.error("error in load configure, init failed !!!");
			//System.exit(0);
		}*/
		
		logger.debug("FiscoBcosService init");
		
	}

	public void init() throws Exception {
		 boolean configure = bcosapp.loadConfig();
		 if (!configure) {
			logger.error("error in load configure, init failed !!!");
			//System.exit(0);
		}	
		logger.debug("FiscoBcosService init init");
	}
	
	public RespResult deployContract(String jsonText) throws Exception {
		//RespResult respResult = RespResult.Common.OK;
		RespResult respResult = new RespResult(RespResult.Common.OK);
		
		/*String contractAddrValue;
		String accountAddrValue;
		JsonParser parse = new JsonParser();
		try {
			JsonObject jsonObj = (JsonObject) parse.parse(jsonText);
			contractAddrValue = jsonObj.get(contractAddrName).getAsString();
			accountAddrValue = jsonObj.get(accountAddrName).getAsString();		
		} catch (Exception e) {
			logger.error("parse deployContract cmd failed:", e);
			contractAddrValue = null;
			accountAddrValue = null;
		}*/

		String contractAddr = null;
		try {
			contractAddr = bcosapp.deployContract(BlockServiceConstants.BLOCK_KEYSTOREFILENAME, BlockServiceConstants.BLOCK_KEYSTOREPASSWORD, BlockServiceConstants.BLOCK_KEYPASSWORD).toString();	
		} catch (Exception e) {
			logger.error("deployContract error:", e);
		}
		
		
		if (null == contractAddr) {
			respResult = RespResult.Common.DEPLOYCONTRACT_ERROR;
		}
		else
		{
			respResult.setDescription(contractAddr);
		}
		//logger.debug("contractAddr is " + contractAddr + "1");
		logger.debug("respResult:" + respResult);
		return respResult;
	}
	public RespResult useropenaccount(String jsonText) throws Exception {
		//logger.debug("122jsonObj is:" + jsonText);
		RespResult respResult = new RespResult(RespResult.Common.OK);
		logger.debug("respResult:" + respResult);
		String contractAddrValue;
		String accountAddrValue;
		JsonParser parse = new JsonParser();
		try {
			JsonObject jsonObj = (JsonObject) parse.parse(jsonText).getAsJsonObject();
			//logger.debug("111jsonObj is:" + jsonObj.toString());
			contractAddrValue = jsonObj.get(contractAddrName).getAsString();
			//logger.debug("useropenaccount contractAddrValue:" + contractAddrValue);
			accountAddrValue = jsonObj.get(accountAddrName).getAsString();	
			//logger.debug("useropenaccount accountAddrValue:" +  accountAddrValue);
		} catch (Exception e) {
			logger.error("parse useropenaccount cmd failed:", e);
			contractAddrValue = null;
			accountAddrValue = null;
		}
		boolean result = false;
		try {
			logger.debug("useropenaccount BlockServiceConstants.BLOCK_KEYSTOREFILENAME:" +  BlockServiceConstants.BLOCK_KEYSTOREFILENAME);
			logger.debug("useropenaccount BlockServiceConstants.BLOCK_KEYSTOREPASSWORD:" + BlockServiceConstants.BLOCK_KEYSTOREPASSWORD);
			logger.debug("useropenaccount BlockServiceConstants.BLOCK_KEYPASSWORD:"+ BlockServiceConstants.BLOCK_KEYPASSWORD);
			result = bcosapp.newAcount(BlockServiceConstants.BLOCK_KEYSTOREFILENAME, BlockServiceConstants.BLOCK_KEYSTOREPASSWORD, BlockServiceConstants.BLOCK_KEYPASSWORD, contractAddrValue, accountAddrValue);
			//result = bcosapp.newAcount("classpath:user.jks", "123456", "123456", contractAddrValue, accountAddrValue);
		}catch (Exception e) {
			logger.error("useropenaccount failed:", e);
		}
		
		if (false == result) {
			respResult = RespResult.Common.NEWACCOUNTFAIL;
		}
		logger.debug("respResult:" + respResult);
		return respResult;
	}
	public RespResult userfreezeAccount(String jsonText) throws Exception {
		//RespResult respResult = RespResult.Common.OK;
		RespResult respResult = new RespResult(RespResult.Common.OK);
		String contractAddrValue = "";
		String accountAddrValue = "";
		String opTypeValue = "";
		JsonParser parse = new JsonParser();
		try {
			JsonObject jsonObj = (JsonObject) parse.parse(jsonText);
			contractAddrValue = jsonObj.get(contractAddrName).getAsString();
			accountAddrValue = jsonObj.get(accountAddrName).getAsString();
			opTypeValue =  jsonObj.get(opTypeName).getAsString();
		} catch (Exception e) {
			logger.error("parse userfreezeAccount cmd failed:", e);
			contractAddrValue = null;
			accountAddrValue = null;
			opTypeValue = null;
		}
		boolean freeze = false;
		if("0" == opTypeValue)
		{
			freeze = false;
		}
		else if("1" == opTypeValue)
		{
			freeze = true;
		}
		
		boolean result = false;
		try {
			result = bcosapp.freezeAccount(BlockServiceConstants.BLOCK_KEYSTOREFILENAME, BlockServiceConstants.BLOCK_KEYSTOREPASSWORD, BlockServiceConstants.BLOCK_KEYPASSWORD, contractAddrValue, accountAddrValue, freeze);
		}catch (Exception e) {
			logger.error("freezeAccount failed:", e);
		}
		if (false == result) {
			respResult = RespResult.Common.FREEZEACCOUNTFail;
		}

		return respResult;
	}
	public RespResult userbuy(String jsonText) throws Exception {
		//RespResult respResult = RespResult.Common.OK;
		RespResult respResult = new RespResult(RespResult.Common.OK);
		String contractAddrValue;
		String accountAddrValue;
		String bankNameValue = "";
		String investmentTypeValue = "";
		String amountValue = "";
		JsonParser parse = new JsonParser();
		try {
			JsonObject jsonObj = (JsonObject) parse.parse(jsonText);
			contractAddrValue = jsonObj.get(contractAddrName).getAsString();	
			logger.debug("contractAddrValue:" + contractAddrValue);
			accountAddrValue = jsonObj.get(accountAddrName).getAsString();	
			logger.debug("accountAddrValue:" + accountAddrValue);
			bankNameValue = jsonObj.get(bankNameName).toString();
			logger.debug("bankNameValue:" + bankNameValue);
			investmentTypeValue = jsonObj.get(investmentTypeName).toString();
			logger.debug("investmentTypeValue:" + investmentTypeValue);
			amountValue = jsonObj.get(amountName).getAsString();	
			logger.debug("amountValue:" + amountValue);
		} catch (Exception e) {
			logger.error("parse userbuy cmd failed:", e);
			contractAddrValue = null;
			accountAddrValue = null;
			bankNameValue = null;
			investmentTypeValue = null;
			amountValue = null;
		}
		boolean result = false;
		try {
			int bankNameInt = Util.getIntFromMap(bankNameMap, bankNameValue);
			int investment = Util.getIntFromMap(investmentTypeMap, investmentTypeValue);
			//int amount = Integer.valueOf(amountValue).intValue();
			//String amountValue1 = amountValue.replace("\"","").replace("\"","");
			int amount = Integer.parseInt(amountValue);
			logger.debug("amount:" + amount);
			//accountAddrValue = accountAddrValue.replace("\"","").replace("\"","");
			result = bcosapp.buy(BlockServiceConstants.BLOCK_KEYSTOREFILENAME, BlockServiceConstants.BLOCK_KEYSTOREPASSWORD, BlockServiceConstants.BLOCK_KEYPASSWORD, contractAddrValue, accountAddrValue, bankNameInt, investment, amount);
		}catch (Exception e) {
			logger.error("buy failed:", e);
		}
				
		if (false == result) {
			respResult = RespResult.Common.BUYFAIL;
		}

		return respResult;
	}
	public RespResult usersell(String jsonText) throws Exception {
		RespResult respResult = new RespResult(RespResult.Common.OK);
		String contractAddrValue;
		String accountAddrValue;
		String bankNameValue = "";
		String investmentTypeValue = "";
		String amountValue = "";
		JsonParser parse = new JsonParser();
		try {
			JsonObject jsonObj = (JsonObject) parse.parse(jsonText);
			contractAddrValue = jsonObj.get(contractAddrName).getAsString();
			accountAddrValue = jsonObj.get(accountAddrName).getAsString();	
			bankNameValue = jsonObj.get(bankNameName).toString();	
			investmentTypeValue = jsonObj.get(investmentTypeName).toString();	
			amountValue = jsonObj.get(amountName).getAsString();	
		} catch (Exception e) {
			logger.error("parse usersell cmd failed:", e);
			contractAddrValue = null;
			accountAddrValue = null;
			bankNameValue = null;
			investmentTypeValue = null;
			amountValue = null;
		}
		boolean result = false;
		try {
			int bankNameInt = Util.getIntFromMap(bankNameMap, bankNameValue);
			int investment = Util.getIntFromMap(investmentTypeMap, investmentTypeValue);
			String amountValue1 = amountValue.replace("\"","").replace("\"","");
			int amount = Integer.valueOf(amountValue1).intValue();
			result = bcosapp.sell(BlockServiceConstants.BLOCK_KEYSTOREFILENAME, BlockServiceConstants.BLOCK_KEYSTOREPASSWORD, BlockServiceConstants.BLOCK_KEYPASSWORD, contractAddrValue, accountAddrValue, bankNameInt, investment, amount);
		}catch (Exception e) {
			logger.error("sell failed:", e);
		}
		if (false == result) {
			respResult = RespResult.Common.SELLFAIL;
		}

		return respResult;
	}
	public BalanceRespResult usergetAccountBalance(String jsonText) throws Exception {
		BalanceRespResult respResult = new BalanceRespResult(RespResult.Common.OK, "0");
		String contractAddrValue;
		String accountAddrValue;
		String bankNameValue = "";
		String investmentTypeValue = "";
		JsonParser parse = new JsonParser();
		try {
			JsonObject jsonObj = (JsonObject) parse.parse(jsonText);
			contractAddrValue = jsonObj.get(contractAddrName).getAsString();
			accountAddrValue = jsonObj.get(accountAddrName).getAsString();	
			bankNameValue = jsonObj.get(bankNameName).toString();	
			investmentTypeValue = jsonObj.get(investmentTypeName).toString();
		} catch (Exception e) {
			logger.error("parse usergetAccountBalance cmd failed:", e);
			contractAddrValue = null;
			accountAddrValue = null;
			bankNameValue = null;
			investmentTypeValue = null;
		}
		BigInteger accountBalance = new BigInteger("0");
		try {
			int bankNameInt = Util.getIntFromMap(bankNameMap, bankNameValue);
			int investment = Util.getIntFromMap(investmentTypeMap, investmentTypeValue);
			accountBalance = bcosapp.getAccountBalance(BlockServiceConstants.BLOCK_KEYSTOREFILENAME, BlockServiceConstants.BLOCK_KEYSTOREPASSWORD, BlockServiceConstants.BLOCK_KEYPASSWORD, contractAddrValue, accountAddrValue, bankNameInt, investment);
		}catch (Exception e) {
			logger.error("getAccountBalance failed:", e);
		}		
		respResult.setAmount(accountBalance.toString());;
		
		return respResult;
	}
	
	public BalanceRespResult usergetBankBalance(String jsonText) throws Exception {
		BalanceRespResult respResult = new BalanceRespResult(RespResult.Common.OK, "0");
		String contractAddrValue;
		String bankNameValue = "";
		String investmentTypeValue = "";
		JsonParser parse = new JsonParser();
		try {
			JsonObject jsonObj = (JsonObject) parse.parse(jsonText);
			contractAddrValue = jsonObj.get(contractAddrName).getAsString();
			bankNameValue = jsonObj.get(bankNameName).toString();	
			investmentTypeValue = jsonObj.get(investmentTypeName).toString();
		} catch (Exception e) {
			logger.error("parse usergetBankBalance cmd failed:", e);
			contractAddrValue = null;
			bankNameValue = null;
			investmentTypeValue = null;
		}
		BigInteger accountBalance = new BigInteger("0");
		try {
			int bankNameInt = Util.getIntFromMap(bankNameMap, bankNameValue);
			int investment = Util.getIntFromMap(investmentTypeMap, investmentTypeValue);
			accountBalance = bcosapp.getBankBalance(BlockServiceConstants.BLOCK_KEYSTOREFILENAME, BlockServiceConstants.BLOCK_KEYSTOREPASSWORD, BlockServiceConstants.BLOCK_KEYPASSWORD, contractAddrValue, bankNameInt, investment);
		}catch (Exception e) {
			logger.error("getBankBalance failed:", e);
		}
		respResult.setAmount(accountBalance.toString());;
		
		return respResult;
	}
	public BcosApp getBcosapp() {
		logger.debug("111getBcosapp");
		return bcosapp;
	}

	public void setBcosapp(BcosApp bcosapp) {
		logger.debug("222setBcosapp");
		this.bcosapp = bcosapp;
	}
}
