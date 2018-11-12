package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Fixed;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Fixed32x72 extends Fixed {
    public static final Fixed32x72 DEFAULT = new Fixed32x72(BigInteger.ZERO);

    public Fixed32x72(BigInteger value) {
        super(32, 72, value);
    }

    public Fixed32x72(int mBitSize, int nBitSize, BigInteger m, BigInteger n) {
        super(32, 72, m, n);
    }
}
