package com.block.service.app;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyStore;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.util.*;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bcos.channel.client.Service;
//import org.bcos.evidence.web3j.Evidence;

import com.block.service.FiscoBcosService;
import com.block.service.app.PublicAddressConf;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
//import org.bcos.goldtoken.sample.EvidenceData;
//import org.bcos.goldtoken.sample.PublicAddressConf;
//import org.bcos.goldtoken.util.Tools;
//import org.bcos.sample.app.BcosConfig;
//import org.bcos.goldtoken.web3j.Evidence;
//import org.bcos.goldtoken.web3j.EvidenceSignersData;
//import org.bcos.sample.web3j.GoldToken;
//import org.bcos.sample.web3j.SimpleStorage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.bcos.web3j.abi.datatypes.generated.Uint256;
//import org.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.Bool;
import org.bcos.web3j.abi.datatypes.DynamicArray;
import org.bcos.web3j.abi.datatypes.Type;
import org.bcos.web3j.abi.datatypes.Uint;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.bcos.web3j.abi.datatypes.generated.Uint8;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.ECKeyPair;
import org.bcos.web3j.crypto.Keys;
import org.bcos.web3j.crypto.Sign;
import org.bcos.web3j.protocol.Web3j;
//import org.web3j.protocol.channel.ChannelEthereumService;
import org.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.tx.BcosRawTxManager;
import com.block.service.web3j.GoldToken;
import com.block.service.web3j.GoldToken.FreezeAccountEventEventResponse;

import net.sf.json.JSONObject;

public class BcosApp {
	
	private GoldToken goldToken;
	private Web3j web3j;
	ApplicationContext context;
	//protected BcosConfig configure;
	//protected Credentials credentials;
	private static Logger logger = LogManager.getLogger(BcosApp.class.getName());
	
	/*public static BigInteger gasPrice = new BigInteger("99999999999");
	public static BigInteger gasLimited = new BigInteger("9999999999999");
	public static BigInteger initialValue = new BigInteger("0");*/
	public BigInteger gasPrice;
	public BigInteger gasLimited;
	public BigInteger initialValue;
	public BigInteger getGasPrice() {  
        return gasPrice;  
    }  
    public void setGasPrice(BigInteger gasPrice) {  
        this.gasPrice = gasPrice;  
    }
    public BigInteger getGasLimited() {  
        return gasLimited;  
    }  
    public void setGasLimited(BigInteger gasLimited) { 
    	logger.debug("setGasLimited BigInteger");
        this.gasLimited = gasLimited;  
    }
    public BigInteger getInitialValue() {  
        return initialValue;  
    }  
    public void setInitialValue(BigInteger initialValue) {  
        this.initialValue = initialValue;  
    }

    public void setGasPrice(String gasPrice) {  
        this.gasPrice = new BigInteger(gasPrice);  
    }
    public void setGasLimited(String gasLimited) { 
    	logger.debug("setGasLimited String");
        this.gasLimited = new BigInteger(gasLimited);  
    }
    public void setInitialValue(String initialValue) {  
        this.initialValue = new BigInteger(initialValue);  
    }
	public BcosApp() {
		logger.debug("11BcosApp init");
		goldToken =null;
		web3j = null;
		context=null;
		//configure = null;
		//credentials = null;
		logger.debug("BcosApp init");
	}
	
	//loadConfig
	public boolean loadConfig() throws Exception{
		context = new ClassPathXmlApplicationContext("applicationContext-bcos.xml");
		//context = new ClassPathXmlApplicationContext("classpath:applicationContext-bcos.xml");
		//context = new FileSystemXmlApplicationContext
		Service service = context.getBean(Service.class);
        service.run();
        Thread.sleep(3000);
        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        channelEthereumService.setChannelService(service);
        web3j = Web3j.build(channelEthereumService);
        boolean flag=false;
        if(web3j != null){
        	flag=true;
        }
        else
        {
        	logger.error("loadConfig web3j=null");
        }
        return flag;
	}
	//deploy
	public Address deployContract(String keyStoreFileName,String keyStorePassword, String keyPassword) throws Exception {
		if (web3j == null)
			return null;
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if(credentials==null){
			return null;
		}
	    Service service = context.getBean(Service.class);
	    service.run();
	    PublicAddressConf conf = context.getBean(PublicAddressConf.class);
	    Thread.sleep(3000);
	    
	    ConcurrentHashMap<String, String> addressConf = conf.getAllPublicAddress();
        for (Entry<String, String> s : addressConf.entrySet()) {
        	logger.info("键值对:" + s);
        	//System.out.println("键值对:" + s);
        }
        ArrayList<Address> arrayList = addressConf.values().stream().map(Address::new).collect(Collectors.toCollection(ArrayList::new));
        for (int i = 0; i < arrayList.size(); i++) {  
            //System.out.println("arrayList对:" + arrayList.get(i));  
            logger.info("arrayList对:" + arrayList.get(i));
        }
        //DynamicArray<Address> evidenceSigners = new DynamicArray<Address>(arrayList);
        
		//Future<GoldToken> futureGoldToken = GoldToken.deploy(web3j, new BcosRawTxManager(web3j, credentials, 100, 100), gasPrice, gasLimited, new BigInteger("0"));
		Future<GoldToken> futureGoldToken = GoldToken.deploy(web3j, credentials, gasPrice, gasLimited, new BigInteger("0"));
	
		try {
			goldToken = futureGoldToken.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			logger.error("*** deploy contract failed *** ");
			return null;
		}
		
		logger.info("BcosAPP deploy success, address: " + goldToken.getContractAddress());
		return new Address(goldToken.getContractAddress());
	}

	/*
	 * public TransactionReceipt executeTransaction(Address address) {
		if (configure == null || web3j == null || credentials == null)
			return null;
		
		if (address != null) {
			goldToken = GoldToken.load(address.toString(), web3j, credentials, gasPrice, gasLimited);
		}
		
		TransactionReceipt receipt = null;
		try {
			receipt = goldToken.set(new Uint256(new BigInteger("1000"))).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("execute transaction successully, txHash: " + receipt.getTransactionHash());
		return receipt;
	}
	*/
	//newAccount
	//public TransactionReceipt newAcount(String keyStoreFileName,String keyStorePassword, String keyPassword,String contractAddress, Address accountAddress) throws Exception {
	public boolean newAcount(String keyStoreFileName,String keyStorePassword, String keyPassword,String contractAddress, String accountAddress) throws Exception {
		boolean flag=false;
		//logger.debug("newAcount keyStoreFileName:" + keyStoreFileName);
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if (web3j == null) {
			logger.error("newAcount web3j = null");
			return flag;
		}
		if (contractAddress != null) {
			//goldToken = GoldToken.load(address.toString(), web3j, new BcosRawTxManager(web3j, credentials, 100, 100), gasPrice, gasLimited);
			goldToken = GoldToken.load(contractAddress, web3j, credentials, gasPrice, gasLimited);
		}
		else
		{
			logger.error("newAcount contractAddress = null");
		}
		
		TransactionReceipt receipt = null;
		try {
			Address accountAddr = new Address(accountAddress);
			receipt = goldToken.newAccount(accountAddr).get();
			List<GoldToken.NewAccountEventEventResponse> addList = goldToken.getNewAccountEventEvents(receipt);
			logger.debug("receipt.toString():" + receipt.toString());
			if (addList.size() > 0 )
            {
            	flag = true;	
            }
			else
			{
				logger.error("newAcount addList.size() = 0");
			}
		} catch (InterruptedException | ExecutionException e) {
			logger.error("BcosAPP goldToken.newAccount error:", e);
			//e.printStackTrace();
		}

		return flag;
	}
	//freezeAccount
	public boolean freezeAccount(String keyStoreFileName,String keyStorePassword, String keyPassword,String contractAddress, String accountAddress, boolean freeze) throws Exception {
		boolean flag = false;
		if (web3j == null)
			return flag;
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if (contractAddress != null) {
			//goldToken = GoldToken.load(address.toString(), web3j, new BcosRawTxManager(web3j, credentials, 100, 100), gasPrice, gasLimited);
			goldToken = GoldToken.load(contractAddress.toString(), web3j, credentials, gasPrice, gasLimited);
		}
		
		TransactionReceipt receipt = null;
		try {
			Address accountAddr = new Address(accountAddress);
			Bool freezebool = new Bool(freeze);
			receipt = goldToken.freezeAccount(accountAddr, freezebool).get();
			List<GoldToken.FreezeAccountEventEventResponse> addList = goldToken.getFreezeAccountEventEvents(receipt);
			if (addList.size() > 0 )
            {
            	flag = true;	
            }
		} catch (InterruptedException | ExecutionException e) {
			logger.error("BcosAPP goldToken.freezeAccount error:", e);
			//e.printStackTrace();
		}

		return flag;
	}
	//buy
	public boolean buy(String keyStoreFileName,String keyStorePassword, String keyPassword,String contractAddress, String accountAddress, int bankName, int investmentType, int amount) throws Exception {
		boolean flag = false;
		if (web3j == null)
			return flag;
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if (contractAddress != null) {
			//goldToken = GoldToken.load(address.toString(), web3j, new BcosRawTxManager(web3j, credentials, 100, 100), gasPrice, gasLimited);
			goldToken = GoldToken.load(contractAddress.toString(), web3j, credentials, gasPrice, gasLimited);
		}
		
		TransactionReceipt receipt = null;
		try {
			Address accountAddr = new Address(accountAddress);
			Uint8 bankNameUint = new Uint8(bankName);
			Uint8 investmentTypeUint = new Uint8(investmentType);
			Uint256 amountUint = new Uint256(amount);
			receipt = goldToken.buy( accountAddr, bankNameUint, investmentTypeUint, amountUint).get();
			List<GoldToken.BuyEventEventResponse> addList = goldToken.getBuyEventEvents(receipt);
			if (addList.size() > 0 )
            {
            	flag = true;	
            }
		} catch (InterruptedException | ExecutionException e) {
			logger.error("BcosAPP goldToken.buy error:", e);
			//e.printStackTrace();
		}

		return flag;
	}

	//sell
	public boolean sell(String keyStoreFileName,String keyStorePassword, String keyPassword,String contractAddress, String accountAddress, int bankName, int investmentType, int amount) throws Exception {
		boolean flag = false;
		if (web3j == null)
			return flag;
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if (contractAddress != null) {
			//goldToken = GoldToken.load(address.toString(), web3j, new BcosRawTxManager(web3j, credentials, 100, 100), gasPrice, gasLimited);
			goldToken = GoldToken.load(contractAddress.toString(), web3j, credentials, gasPrice, gasLimited);
		}
		
		TransactionReceipt receipt = null;
		try {
			Address accountAddr = new Address(accountAddress);
			Uint8 bankNameUint = new Uint8(bankName);
			Uint8 investmentTypeUint = new Uint8(investmentType);
			Uint256 amountUint = new Uint256(amount);
			receipt = goldToken.sell( accountAddr, bankNameUint, investmentTypeUint, amountUint).get();
			List<GoldToken.SellEventEventResponse> addList = goldToken.getSellEventEvents(receipt);
			if (addList.size() > 0 )
            {
            	flag = true;	
            }
		} catch (InterruptedException | ExecutionException e) {
			logger.error("BcosAPP goldToken.sell error:", e);
			//e.printStackTrace();
		}

		return flag;
	}
	//getAccountBalance
	public BigInteger getAccountBalance(String keyStoreFileName,String keyStorePassword, String keyPassword,String contractAddress, String accountAddress, int bankName, int investmentType) throws Exception {
		if (web3j == null)
			return null;
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if (contractAddress != null) {
			//goldToken = GoldToken.load(address.toString(), web3j, new BcosRawTxManager(web3j, credentials, 100, 100), gasPrice, gasLimited);
			goldToken = GoldToken.load(contractAddress.toString(), web3j, credentials, gasPrice, gasLimited);
		}
		
		BigInteger accountBalance = null;
		try {
			Address accountAddr = new Address(accountAddress);
			Uint8 bankNameUint = new Uint8(bankName);
			Uint8 investmentTypeUint = new Uint8(investmentType);
			Uint256 value = goldToken.getAccountBalance( accountAddr, bankNameUint, investmentTypeUint).get();
			accountBalance = value.getValue();
			//receipt = evidenceSignersData.newEvidence(new Utf8String(evidence_hash),new Utf8String(evidence_id),new Utf8String(evidence_id),new Uint8(signatureData.getV()),new Bytes32(signatureData.getR()),new Bytes32(signatureData.getS())).get();
			//List<EvidenceSignersData.NewEvidenceEventEventResponse> newEvidenceList = evidenceSignersData.getNewEvidenceEventEvents();
			//storedData = value.getValue();
		} catch (InterruptedException | ExecutionException e) {
			logger.error("BcosAPP goldToken.getAccountBalance error:", e);
			//e.printStackTrace();
		}

		return accountBalance;
	}
	//getBankBalance
	public BigInteger getBankBalance(String keyStoreFileName,String keyStorePassword, String keyPassword,String contractAddress, int bankName, int investmentType) throws Exception {
		if (web3j == null)
			return null;
		Credentials credentials=loadkey(keyStoreFileName,keyStorePassword,keyPassword);
		if (contractAddress != null) {
			//goldToken = GoldToken.load(address.toString(), web3j, new BcosRawTxManager(web3j, credentials, 100, 100), gasPrice, gasLimited);
			goldToken = GoldToken.load(contractAddress.toString(), web3j, credentials, gasPrice, gasLimited);
		}
		
		BigInteger bankBalance = null;
		try {
			Uint8 bankNameUint = new Uint8(bankName);
			Uint8 investmentTypeUint = new Uint8(investmentType);
			Uint256 value = goldToken.getBankBalance(bankNameUint, investmentTypeUint).get();
			bankBalance = value.getValue();
			//receipt = evidenceSignersData.newEvidence(new Utf8String(evidence_hash),new Utf8String(evidence_id),new Utf8String(evidence_id),new Uint8(signatureData.getV()),new Bytes32(signatureData.getR()),new Bytes32(signatureData.getS())).get();
			//List<EvidenceSignersData.NewEvidenceEventEventResponse> newEvidenceList = evidenceSignersData.getNewEvidenceEventEvents();
			//storedData = value.getValue();
		} catch (InterruptedException | ExecutionException e) {
			logger.error("BcosAPP goldToken.getAccountBalance error:", e);
			//e.printStackTrace();
		}

		return bankBalance;
	}

   
    public Credentials loadkey(String keyStoreFileName,String keyStorePassword, String keyPassword) throws Exception{
    	InputStream ksInputStream = null;
    	try {
    		 KeyStore ks = KeyStore.getInstance("JKS");
    		 //logger.debug("loadkey keyStoreFileName:" + keyStoreFileName);
    		 ksInputStream =  BcosApp.class.getClassLoader().getResourceAsStream(keyStoreFileName);
    		 //logger.debug("ksInputStream！" + ksInputStream);
    		 ks.load(ksInputStream, keyStorePassword.toCharArray());
    		 Key key = ks.getKey("ec", keyPassword.toCharArray());
    		 if(key == null)
    		 {
    			 logger.error("key = null！");
    		 }
    		 ECKeyPair keyPair = ECKeyPair.create(((ECPrivateKey) key).getS());
    		 Credentials credentials = Credentials.create(keyPair);	
    		 if(credentials!=null){
    		    return credentials;
    		 }else{
    			 logger.error("秘钥参数输入有误！");
    			 //System.out.println("秘钥参数输入有误！");
    		 }
		} catch (Exception e) {
			logger.error("BcosAPP Credentials loadkey error:", e);
			//e.printStackTrace();
		}finally {
			ksInputStream.close();
		}
	    return null;
    }
    
    public String getPublicKey(String keyStoreFileName,String keyStorePassword, String keyPassword) throws Exception{
    	Credentials credentials=loadkey(keyStoreFileName, keyStorePassword, keyPassword);
    	return credentials.getAddress();
    }
	
}
