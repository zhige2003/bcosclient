package org.bcos.contract.source;

import org.bcos.channel.client.TransactionSucCallback;
import org.bcos.web3j.abi.TypeReference;
import org.bcos.web3j.abi.datatypes.Address;
import org.bcos.web3j.abi.datatypes.Bool;
import org.bcos.web3j.abi.datatypes.Function;
import org.bcos.web3j.abi.datatypes.Type;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.bcos.web3j.tx.Contract;
import org.bcos.web3j.tx.TransactionManager;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.bcos.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version none.
 */
public final class AuthorityFilter extends Contract {
    private static final String BINARY = "60606040526000600360006101000a81548160ff021916908315150217905550341561002a57600080fd5b611f91806100396000396000f3006060604052600436106100db576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633e118dbe146100e05780634c96a3891461016e5780635a9b0b89146101bf5780635f8d76991461032557806361dbb7c0146103b357806362617887146103e05780637726bed31461047d578063788bc78c146104a25780639614c769146104ff578063a2f16dd91461055c578063c47f002714610595578063c97f64d5146105f2578063d28d88521461064a578063dc79b176146106d8578063df018dbd14610751575b600080fd5b34156100eb57600080fd5b6100f3610866565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610133578082015181840152602081019050610118565b50505050905090810190601f1680156101605780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561017957600080fd5b6101a5600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610904565b604051808215151515815260200191505060405180910390f35b34156101ca57600080fd5b6101d2610a56565b60405180806020018060200180602001848103845287818151815260200191508051906020019080838360005b8381101561021a5780820151818401526020810190506101ff565b50505050905090810190601f1680156102475780820380516001836020036101000a031916815260200191505b50848103835286818151815260200191508051906020019080838360005b83811015610280578082015181840152602081019050610265565b50505050905090810190601f1680156102ad5780820380516001836020036101000a031916815260200191505b50848103825285818151815260200191508051906020019080838360005b838110156102e65780820151818401526020810190506102cb565b50505050905090810190601f1680156103135780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b341561033057600080fd5b610338610c53565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561037857808201518184015260208101905061035d565b50505050905090810190601f1680156103a55780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156103be57600080fd5b6103c6610cf1565b604051808215151515815260200191505060405180910390f35b34156103eb57600080fd5b61043b600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610d08565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561048857600080fd5b6104a060048080351515906020019091905050610e1b565b005b34156104ad57600080fd5b6104fd600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610e38565b005b341561050a57600080fd5b61055a600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610e52565b005b341561056757600080fd5b610593600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610e6c565b005b34156105a057600080fd5b6105f0600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610f0a565b005b34156105fd57600080fd5b610648600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610f24565b005b341561065557600080fd5b61065d610fa6565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561069d578082015181840152602081019050610682565b50505050905090810190601f1680156106ca5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156106e357600080fd5b61070f600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050611044565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561075c57600080fd5b61084c600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506110ad565b604051808215151515815260200191505060405180910390f35b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108fc5780601f106108d1576101008083540402835291602001916108fc565b820191906000526020600020905b8154815290600101906020018083116108df57829003601f168201915b505050505081565b600080600360009054906101000a900460ff1615156109265760019150610a50565b600460008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff1614156109c65760009150610a50565b8073ffffffffffffffffffffffffffffffffffffffff1663045894ab6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1515610a3257600080fd5b6102c65a03f11515610a4357600080fd5b5050506040518051905091505b50919050565b610a5e6112a7565b610a666112a7565b610a6e6112a7565b600060016002828054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b095780601f10610ade57610100808354040283529160200191610b09565b820191906000526020600020905b815481529060010190602001808311610aec57829003601f168201915b50505050509250818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ba55780601f10610b7a57610100808354040283529160200191610ba5565b820191906000526020600020905b815481529060010190602001808311610b8857829003601f168201915b50505050509150808054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c415780601f10610c1657610100808354040283529160200191610c41565b820191906000526020600020905b815481529060010190602001808311610c2457829003601f168201915b50505050509050925092509250909192565b60028054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ce95780601f10610cbe57610100808354040283529160200191610ce9565b820191906000526020600020905b815481529060010190602001808311610ccc57829003601f168201915b505050505081565b6000600360009054906101000a900460ff16905090565b600080610d136112bb565b604051809103906000f0801515610d2957600080fd5b90508073ffffffffffffffffffffffffffffffffffffffff16639614c769846040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610db3578082015181840152602081019050610d98565b50505050905090810190601f168015610de05780820380516001836020036101000a031916815260200191505b5092505050600060405180830381600087803b1515610dfe57600080fd5b6102c65a03f11515610e0f57600080fd5b50505080915050919050565b80600360006101000a81548160ff02191690831515021790555050565b8060019080519060200190610e4e9291906112cb565b5050565b8060029080519060200190610e689291906112cb565b5050565b610e746112bb565b604051809103906000f0801515610e8a57600080fd5b600460008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b8060009080519060200190610f209291906112cb565b5050565b80600460008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b60008054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561103c5780601f106110115761010080835404028352916020019161103c565b820191906000526020600020905b81548152906001019060200180831161101f57829003601f168201915b505050505081565b6000600460008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600080600360009054906101000a900460ff1615156110cf576001915061129d565b600460008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff16141561116f576000915061129d565b8073ffffffffffffffffffffffffffffffffffffffff16638586b2f086866000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b83811015611233578082015181840152602081019050611218565b50505050905090810190601f1680156112605780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b151561127f57600080fd5b6102c65a03f1151561129057600080fd5b5050506040518051905091505b5095945050505050565b602060405190810160405280600081525090565b604051610bf58061137183390190565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061130c57805160ff191683800117855561133a565b8280016001018555821561133a579182015b8281111561133957825182559160200191906001019061131e565b5b509050611347919061134b565b5090565b61136d91905b80821115611369576000816000905550600101611351565b5090565b90560060606040526000600160006101000a81548160ff02191690831515021790555060006001806101000a81548160ff021916908315150217905550341561004457600080fd5b610ba2806100536000396000f3006060604052600436106100a4576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063045894ab146100a95780632150c518146100d65780633a50f68b14610140578063644d8164146101e05780637dab61b61461026e5780638586b2f0146102935780639614c76914610327578063c878851d14610384578063e7d66cac146103a9578063ee6d84c514610473575b600080fd5b34156100b457600080fd5b6100bc6104a0565b604051808215151515815260200191505060405180910390f35b34156100e157600080fd5b6100e96104b7565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561012c578082015181840152602081019050610111565b505050509050019250505060405180910390f35b341561014b57600080fd5b610165600480803560001916906020019091905050610519565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101a557808201518184015260208101905061018a565b50505050905090810190601f1680156101d25780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156101eb57600080fd5b6101f3610622565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610233578082015181840152602081019050610218565b50505050905090810190601f1680156102605780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561027957600080fd5b610291600480803515159060200190919050506106ca565b005b341561029e57600080fd5b61030d600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506106e6565b604051808215151515815260200191505060405180910390f35b341561033257600080fd5b610382600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610830565b005b341561038f57600080fd5b6103a76004808035151590602001909190505061084a565b005b34156103b457600080fd5b610471600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919080351515906020019091905050610867565b005b341561047e57600080fd5b610486610a42565b604051808215151515815260200191505060405180910390f35b6000600160009054906101000a900460ff16905090565b6104bf610a58565b600380548060200260200160405190810160405280929190818152602001828054801561050f57602002820191906000526020600020905b815460001916815260200190600101908083116104f7575b5050505050905090565b610521610a6c565b600080836000191660001916815260200190815260200160002060009054906101000a900460ff1615610609576004600083600019166000191681526020019081526020016000208054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105fd5780601f106105d2576101008083540402835291602001916105fd565b820191906000526020600020905b8154815290600101906020018083116105e057829003601f168201915b5050505050905061061d565b602060405190810160405280600081525090505b919050565b61062a610a6c565b60028054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106c05780601f10610695576101008083540402835291602001916106c0565b820191906000526020600020905b8154815290600101906020018083116106a357829003601f168201915b5050505050905090565b806001806101000a81548160ff02191690831515021790555050565b60008060028484600060405160200152604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166c0100000000000000000000000002815260140182805190602001908083835b60208310151561076c5780518252602082019150602081019050602083039250610747565b6001836020036101000a0380198251168184511680821785525050505050509050019250505060206040518083038160008661646e5a03f115156107af57600080fd5b50506040518051905090506001809054906101000a900460ff16156107fe57600080826000191660001916815260200190815260200160002060009054906101000a900460ff16159150610829565b600080826000191660001916815260200190815260200160002060009054906101000a900460ff1691505b5092915050565b8060029080519060200190610846929190610a80565b5050565b80600160006101000a81548160ff02191690831515021790555050565b600080600060028787600060405160200152604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166c0100000000000000000000000002815260140182805190602001908083835b6020831015156108ef57805182526020820191506020810190506020830392506108ca565b6001836020036101000a0380198251168184511680821785525050505050509050019250505060206040518083038160008661646e5a03f1151561093257600080fd5b50506040518051905092508315610a075760009150600090505b60038054905081101561099957826000191660038281548110151561096d57fe5b90600052602060002090015460001916141561098c5760019150610999565b808060010191505061094c565b600015158215151415610a0657600380548060010182816109ba9190610b00565b916000526020600020900160008590919091509060001916905550846004600085600019166000191681526020019081526020016000209080519060200190610a04929190610a80565b505b5b83600080856000191660001916815260200190815260200160002060006101000a81548160ff02191690831515021790555050505050505050565b60006001809054906101000a900460ff16905090565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610ac157805160ff1916838001178555610aef565b82800160010185558215610aef579182015b82811115610aee578251825591602001919060010190610ad3565b5b509050610afc9190610b2c565b5090565b815481835581811511610b2757818360005260206000209182019101610b269190610b51565b5b505050565b610b4e91905b80821115610b4a576000816000905550600101610b32565b5090565b90565b610b7391905b80821115610b6f576000816000905550600101610b57565b5090565b905600a165627a7a723058205be74d70651e5dc32f4df52e553884b1aa31d47cb570f3a211f4d50f20aacc300029a165627a7a7230582024b765f69ec7f014c6e8db13316a1525a80d9db9680f659d6555da2d494313d70029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[],\"name\":\"_version\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"origin\",\"type\":\"address\"}],\"name\":\"deploy\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_desc\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getEnable\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"desc\",\"type\":\"string\"}],\"name\":\"newGroup\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"enable\",\"type\":\"bool\"}],\"name\":\"setEnable\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"version\",\"type\":\"string\"}],\"name\":\"setVersion\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"desc\",\"type\":\"string\"}],\"name\":\"setDesc\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"user\",\"type\":\"address\"}],\"name\":\"setUserToNewGroup\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"}],\"name\":\"setName\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"user\",\"type\":\"address\"},{\"name\":\"group\",\"type\":\"address\"}],\"name\":\"setUserToGroup\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_name\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"user\",\"type\":\"address\"}],\"name\":\"getUserGroup\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"origin\",\"type\":\"address\"},{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"func\",\"type\":\"string\"},{\"name\":\"input\",\"type\":\"string\"}],\"name\":\"process\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";

    private AuthorityFilter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, isInitByName);
    }

    private AuthorityFilter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, isInitByName);
    }

    private AuthorityFilter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    private AuthorityFilter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public Future<Utf8String> _version() {
        Function function = new Function("_version", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> deploy(Address origin) {
        Function function = new Function("deploy", 
                Arrays.<Type>asList(origin), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<List<Type>> getInfo() {
        Function function = new Function("getInfo", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<Utf8String> _desc() {
        Function function = new Function("_desc", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> getEnable() {
        Function function = new Function("getEnable", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> newGroup(Utf8String desc) {
        Function function = new Function("newGroup", Arrays.<Type>asList(desc), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void newGroup(Utf8String desc, TransactionSucCallback callback) {
        Function function = new Function("newGroup", Arrays.<Type>asList(desc), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<TransactionReceipt> setEnable(Bool enable) {
        Function function = new Function("setEnable", Arrays.<Type>asList(enable), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void setEnable(Bool enable, TransactionSucCallback callback) {
        Function function = new Function("setEnable", Arrays.<Type>asList(enable), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<TransactionReceipt> setVersion(Utf8String version) {
        Function function = new Function("setVersion", Arrays.<Type>asList(version), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void setVersion(Utf8String version, TransactionSucCallback callback) {
        Function function = new Function("setVersion", Arrays.<Type>asList(version), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<TransactionReceipt> setDesc(Utf8String desc) {
        Function function = new Function("setDesc", Arrays.<Type>asList(desc), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void setDesc(Utf8String desc, TransactionSucCallback callback) {
        Function function = new Function("setDesc", Arrays.<Type>asList(desc), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<TransactionReceipt> setUserToNewGroup(Address user) {
        Function function = new Function("setUserToNewGroup", Arrays.<Type>asList(user), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void setUserToNewGroup(Address user, TransactionSucCallback callback) {
        Function function = new Function("setUserToNewGroup", Arrays.<Type>asList(user), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<TransactionReceipt> setName(Utf8String name) {
        Function function = new Function("setName", Arrays.<Type>asList(name), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void setName(Utf8String name, TransactionSucCallback callback) {
        Function function = new Function("setName", Arrays.<Type>asList(name), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<TransactionReceipt> setUserToGroup(Address user, Address group) {
        Function function = new Function("setUserToGroup", Arrays.<Type>asList(user, group), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void setUserToGroup(Address user, Address group, TransactionSucCallback callback) {
        Function function = new Function("setUserToGroup", Arrays.<Type>asList(user, group), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<Utf8String> _name() {
        Function function = new Function("_name", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> getUserGroup(Address user) {
        Function function = new Function("getUserGroup", 
                Arrays.<Type>asList(user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> process(Address origin, Address from, Address to, Utf8String func, Utf8String input) {
        Function function = new Function("process", 
                Arrays.<Type>asList(origin, from, to, func, input), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<AuthorityFilter> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(AuthorityFilter.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<AuthorityFilter> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(AuthorityFilter.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static AuthorityFilter load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AuthorityFilter(contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    public static AuthorityFilter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AuthorityFilter(contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static AuthorityFilter loadByName(String contractName, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AuthorityFilter(contractName, web3j, credentials, gasPrice, gasLimit, true);
    }

    public static AuthorityFilter loadByName(String contractName, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AuthorityFilter(contractName, web3j, transactionManager, gasPrice, gasLimit, true);
    }
}
