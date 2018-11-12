package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Ufixed;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Ufixed40x120 extends Ufixed {
    public static final Ufixed40x120 DEFAULT = new Ufixed40x120(BigInteger.ZERO);

    public Ufixed40x120(BigInteger value) {
        super(40, 120, value);
    }

    public Ufixed40x120(int mBitSize, int nBitSize, BigInteger m, BigInteger n) {
        super(40, 120, m, n);
    }
}
