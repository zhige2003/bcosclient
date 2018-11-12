package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Fixed;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Fixed72x128 extends Fixed {
    public static final Fixed72x128 DEFAULT = new Fixed72x128(BigInteger.ZERO);

    public Fixed72x128(BigInteger value) {
        super(72, 128, value);
    }

    public Fixed72x128(int mBitSize, int nBitSize, BigInteger m, BigInteger n) {
        super(72, 128, m, n);
    }
}
