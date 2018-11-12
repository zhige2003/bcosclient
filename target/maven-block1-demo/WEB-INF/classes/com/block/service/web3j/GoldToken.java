package com.block.service.web3j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.bcos.web3j.abi.EventEncoder;
import org.bcos.web3j.abi.EventValues;
import org.bcos.web3j.abi.TypeReference;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.Bool;
import org.bcos.web3j.abi.datatypes.Event;
import org.bcos.web3j.abi.datatypes.Function;
import org.bcos.web3j.abi.datatypes.Type;
import org.bcos.web3j.abi.datatypes.generated.Uint256;
import org.bcos.web3j.abi.datatypes.generated.Uint8;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.bcos.web3j.protocol.core.methods.request.EthFilter;
import org.bcos.web3j.protocol.core.methods.response.Log;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.bcos.web3j.tx.Contract;
import org.bcos.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.bcos.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version none.
 */
public final class GoldToken extends Contract {
    private static final String BINARY = "60606040525b60007fb2bcda062ff29500167ad5acc2536e49b41417fbabf61a0bd9a80fe77be207c333604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a133905060008173ffffffffffffffffffffffffffffffffffffffff161415156100cf5780600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5b505b610f59806100e16000396000f300606060405236156100ad576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806303152429146100af578063093efb3d146100fd5780631824e9011461016c578063212ee80a146101ce5780634fb2e45d1461023d5780636059145814610273578063712732b0146102c55780638da5cb5b14610308578063a99d70721461035a578063df32754b146103af578063e724529c146103c1575bfe5b34156100b757fe5b6100e3600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061041a565b604051808215151515815260200191505060405180910390f35b341561010557fe5b610152600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803560ff1690602001909190803560ff16906020019091908035906020019091905050610599565b604051808215151515815260200191505060405180910390f35b341561017457fe5b6101b8600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803560ff1690602001909190803560ff16906020019091905050610926565b6040518082815260200191505060405180910390f35b34156101d657fe5b610223600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803560ff1690602001909190803560ff169060200190919080359060200190919050506109db565b604051808215151515815260200191505060405180910390f35b341561024557fe5b610271600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610c1c565b005b341561027b57fe5b610283610cc0565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156102cd57fe5b6102f2600480803560ff1690602001909190803560ff16906020019091905050610ceb565b6040518082815260200191505060405180910390f35b341561031057fe5b610318610d34565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561036257fe5b61038e600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610d5a565b60405180838152602001821515151581526020019250505060405180910390f35b34156103b757fe5b6103bf610d8b565b005b34156103c957fe5b610400600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919080351515906020019091905050610dcf565b604051808215151515815260200191505060405180910390f35b6000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156104795760006000fd5b60008273ffffffffffffffffffffffffffffffffffffffff1614151561058b576040604051908101604052806000815260200160001515815250600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000820151816000015560208201518160010160006101000a81548160ff0219169083151502179055509050507f740d6c29f1d2e26abf8115cc1d53e86d7e0f8efce863d065abe4fd21fdea8d4382604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a160019050610592565b60006000fd5b5b5b5b919050565b600060008573ffffffffffffffffffffffffffffffffffffffff16141580156105c3575060008211155b801561061c5750600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160009054906101000a900460ff16155b80156106a4575081600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600201600086600381111561067457fe5b8152602001908152602001600020600001600085600181111561069357fe5b815260200190815260200160002054105b156106e85760006000fd5b60ff1681526020018360018111156106c357fe5b60ff16815260200182815260200194505050505060405180910390a16000905061091e565b81600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600201600086600381111561073957fe5b8152602001908152602001600020600001600085600181111561075857fe5b81526020019081526020016000205410156107ac5760006000fd5b60ff16815260200183600181111561078757fe5b60ff16815260200182815260200194505050505060405180910390a16000905061091e565b81600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160008660038111156107fd57fe5b8152602001908152602001600020600001600085600181111561081c57fe5b815260200190815260200160002060008282540392505081905550816002600086600381111561084857fe5b8152602001908152602001600020600001600085600181111561086757fe5b8152602001908152602001600020600082825403925050819055507fada777ff7414414dd75b79c4fea841764ddf83b43f3882ad9a8572776ca1e1d385858585604051808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018460038111156108e957fe5b60ff1681526020018360018111156108fd57fe5b60ff16815260200182815260200194505050505060405180910390a1600190505b949350505050565b600060008473ffffffffffffffffffffffffffffffffffffffff161415156109cd57600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600201600084600381111561099857fe5b815260200190815260200160002060000160008360018111156109b757fe5b81526020019081526020016000205490506109d4565b60006000fd5b5b9392505050565b600060008573ffffffffffffffffffffffffffffffffffffffff1614158015610a05575060008211155b8015610a5e5750600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160009054906101000a900460ff16155b15610aa25760006000fd5b60ff168152602001836001811115610a7d57fe5b60ff16815260200182815260200194505050505060405180910390a160009050610c14565b81600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206002016000866003811115610af357fe5b81526020019081526020016000206000016000856001811115610b1257fe5b8152602001908152602001600020600082825401925050819055508160026000866003811115610b3e57fe5b81526020019081526020016000206000016000856001811115610b5d57fe5b8152602001908152602001600020600082825401925050819055507f330b803f09b52ce49ccbd6aa67944e6f37b8a21af5bd94e7d1aaefbc38ea72b985858585604051808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001846003811115610bdf57fe5b60ff168152602001836001811115610bf357fe5b60ff16815260200182815260200194505050505060405180910390a1600190505b949350505050565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610c795760006000fd5b80600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5b5b50565b6000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b90565b600060026000846003811115610cfd57fe5b81526020019081526020016000206000016000836001811115610d1c57fe5b81526020019081526020016000205490505b92915050565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60016020528060005260406000206000915090508060000154908060010160009054906101000a900460ff16905082565b33600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b565b6000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610e2e5760006000fd5b60008373ffffffffffffffffffffffffffffffffffffffff16141515610f205781600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160006101000a81548160ff0219169083151502179055507ff48001ce37efbe57cbee154ad08e57de1885bf7dbd80b1e3145f66a0dbfa62178383604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001821515151581526020019250505060405180910390a160019050610f25565b600090505b5b5b929150505600a165627a7a7230582089657026b4c5a5d6c6f78227e609a2b351d083c8b7c1a94fd545106bffc49c1e0029";

    private GoldToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private GoldToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<GoldTokeninitEventResponse> getGoldTokeninitEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("goldTokeninit", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<GoldTokeninitEventResponse> responses = new ArrayList<GoldTokeninitEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            GoldTokeninitEventResponse typedResponse = new GoldTokeninitEventResponse();
            typedResponse.sender = (Address) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<GoldTokeninitEventResponse> goldTokeninitEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("goldTokeninit", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, GoldTokeninitEventResponse>() {
            @Override
            public GoldTokeninitEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                GoldTokeninitEventResponse typedResponse = new GoldTokeninitEventResponse();
                typedResponse.sender = (Address) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<BuyEventEventResponse> getBuyEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("buyEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<BuyEventEventResponse> responses = new ArrayList<BuyEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            BuyEventEventResponse typedResponse = new BuyEventEventResponse();
            typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.bankName = (Uint8) eventValues.getNonIndexedValues().get(1);
            typedResponse.investmentType = (Uint8) eventValues.getNonIndexedValues().get(2);
            typedResponse.amount = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BuyEventEventResponse> buyEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("buyEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, BuyEventEventResponse>() {
            @Override
            public BuyEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                BuyEventEventResponse typedResponse = new BuyEventEventResponse();
                typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.bankName = (Uint8) eventValues.getNonIndexedValues().get(1);
                typedResponse.investmentType = (Uint8) eventValues.getNonIndexedValues().get(2);
                typedResponse.amount = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public List<ErrorBuyEventEventResponse> getErrorBuyEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("errorBuyEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ErrorBuyEventEventResponse> responses = new ArrayList<ErrorBuyEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ErrorBuyEventEventResponse typedResponse = new ErrorBuyEventEventResponse();
            typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.bankName = (Uint8) eventValues.getNonIndexedValues().get(1);
            typedResponse.investmentType = (Uint8) eventValues.getNonIndexedValues().get(2);
            typedResponse.amount = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ErrorBuyEventEventResponse> errorBuyEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("errorBuyEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ErrorBuyEventEventResponse>() {
            @Override
            public ErrorBuyEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ErrorBuyEventEventResponse typedResponse = new ErrorBuyEventEventResponse();
                typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.bankName = (Uint8) eventValues.getNonIndexedValues().get(1);
                typedResponse.investmentType = (Uint8) eventValues.getNonIndexedValues().get(2);
                typedResponse.amount = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public List<SellEventEventResponse> getSellEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("sellEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<SellEventEventResponse> responses = new ArrayList<SellEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            SellEventEventResponse typedResponse = new SellEventEventResponse();
            typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.bankName = (Uint8) eventValues.getNonIndexedValues().get(1);
            typedResponse.investmentType = (Uint8) eventValues.getNonIndexedValues().get(2);
            typedResponse.amount = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SellEventEventResponse> sellEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("sellEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SellEventEventResponse>() {
            @Override
            public SellEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SellEventEventResponse typedResponse = new SellEventEventResponse();
                typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.bankName = (Uint8) eventValues.getNonIndexedValues().get(1);
                typedResponse.investmentType = (Uint8) eventValues.getNonIndexedValues().get(2);
                typedResponse.amount = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public List<ErrorSellEventEventResponse> getErrorSellEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("errorSellEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ErrorSellEventEventResponse> responses = new ArrayList<ErrorSellEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ErrorSellEventEventResponse typedResponse = new ErrorSellEventEventResponse();
            typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.bankName = (Uint8) eventValues.getNonIndexedValues().get(1);
            typedResponse.investmentType = (Uint8) eventValues.getNonIndexedValues().get(2);
            typedResponse.amount = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ErrorSellEventEventResponse> errorSellEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("errorSellEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ErrorSellEventEventResponse>() {
            @Override
            public ErrorSellEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ErrorSellEventEventResponse typedResponse = new ErrorSellEventEventResponse();
                typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.bankName = (Uint8) eventValues.getNonIndexedValues().get(1);
                typedResponse.investmentType = (Uint8) eventValues.getNonIndexedValues().get(2);
                typedResponse.amount = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public List<NewAccountEventEventResponse> getNewAccountEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("newAccountEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<NewAccountEventEventResponse> responses = new ArrayList<NewAccountEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            NewAccountEventEventResponse typedResponse = new NewAccountEventEventResponse();
            typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewAccountEventEventResponse> newAccountEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("newAccountEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewAccountEventEventResponse>() {
            @Override
            public NewAccountEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                NewAccountEventEventResponse typedResponse = new NewAccountEventEventResponse();
                typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<ErrorNewAccountEventEventResponse> getErrorNewAccountEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("errorNewAccountEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ErrorNewAccountEventEventResponse> responses = new ArrayList<ErrorNewAccountEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ErrorNewAccountEventEventResponse typedResponse = new ErrorNewAccountEventEventResponse();
            typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ErrorNewAccountEventEventResponse> errorNewAccountEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("errorNewAccountEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ErrorNewAccountEventEventResponse>() {
            @Override
            public ErrorNewAccountEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ErrorNewAccountEventEventResponse typedResponse = new ErrorNewAccountEventEventResponse();
                typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<FreezeAccountEventEventResponse> getFreezeAccountEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("freezeAccountEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<FreezeAccountEventEventResponse> responses = new ArrayList<FreezeAccountEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            FreezeAccountEventEventResponse typedResponse = new FreezeAccountEventEventResponse();
            typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse._bool = (Bool) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<FreezeAccountEventEventResponse> freezeAccountEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("freezeAccountEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, FreezeAccountEventEventResponse>() {
            @Override
            public FreezeAccountEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                FreezeAccountEventEventResponse typedResponse = new FreezeAccountEventEventResponse();
                typedResponse.target = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse._bool = (Bool) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Future<TransactionReceipt> newAccount(Address target) {
        Function function = new Function("newAccount", Arrays.<Type>asList(target), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> sell(Address target, Uint8 bankName, Uint8 investmentType, Uint256 amount) {
        Function function = new Function("sell", Arrays.<Type>asList(target, bankName, investmentType, amount), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> getAccountBalance(Address target, Uint8 bankName, Uint8 investmentType) {
        Function function = new Function("getAccountBalance", 
                Arrays.<Type>asList(target, bankName, investmentType), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> buy(Address target, Uint8 bankName, Uint8 investmentType, Uint256 amount) {
        Function function = new Function("buy", Arrays.<Type>asList(target, bankName, investmentType, amount), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> transferOwner(Address newOwner) {
        Function function = new Function("transferOwner", Arrays.<Type>asList(newOwner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> getOwer() {
        Function function = new Function("getOwer", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getBankBalance(Uint8 bankName, Uint8 investmentType) {
        Function function = new Function("getBankBalance", 
                Arrays.<Type>asList(bankName, investmentType), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<List<Type>> accountOfGold(Address param0) {
        Function function = new Function("accountOfGold", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> owned() {
        Function function = new Function("owned", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> freezeAccount(Address target, Bool _bool) {
        Function function = new Function("freezeAccount", Arrays.<Type>asList(target, _bool), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<GoldToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(GoldToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<GoldToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(GoldToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static GoldToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new GoldToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static GoldToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new GoldToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class GoldTokeninitEventResponse {
        public Address sender;
    }

    public static class BuyEventEventResponse {
        public Address target;

        public Uint8 bankName;

        public Uint8 investmentType;

        public Uint256 amount;
    }

    public static class ErrorBuyEventEventResponse {
        public Address target;

        public Uint8 bankName;

        public Uint8 investmentType;

        public Uint256 amount;
    }

    public static class SellEventEventResponse {
        public Address target;

        public Uint8 bankName;

        public Uint8 investmentType;

        public Uint256 amount;
    }

    public static class ErrorSellEventEventResponse {
        public Address target;

        public Uint8 bankName;

        public Uint8 investmentType;

        public Uint256 amount;
    }

    public static class NewAccountEventEventResponse {
        public Address target;
    }

    public static class ErrorNewAccountEventEventResponse {
        public Address target;
    }

    public static class FreezeAccountEventEventResponse {
        public Address target;

        public Bool _bool;
    }
}
