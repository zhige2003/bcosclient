package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Ufixed;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Ufixed8x128 extends Ufixed {
    public static final Ufixed8x128 DEFAULT = new Ufixed8x128(BigInteger.ZERO);

    public Ufixed8x128(BigInteger value) {
        super(8, 128, value);
    }

    public Ufixed8x128(int mBitSize, int nBitSize, BigInteger m, BigInteger n) {
        super(8, 128, m, n);
    }
}
