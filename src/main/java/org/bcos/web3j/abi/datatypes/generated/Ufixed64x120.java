package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Ufixed;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Ufixed64x120 extends Ufixed {
    public static final Ufixed64x120 DEFAULT = new Ufixed64x120(BigInteger.ZERO);

    public Ufixed64x120(BigInteger value) {
        super(64, 120, value);
    }

    public Ufixed64x120(int mBitSize, int nBitSize, BigInteger m, BigInteger n) {
        super(64, 120, m, n);
    }
}
