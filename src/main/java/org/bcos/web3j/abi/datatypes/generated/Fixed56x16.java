package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Fixed;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Fixed56x16 extends Fixed {
    public static final Fixed56x16 DEFAULT = new Fixed56x16(BigInteger.ZERO);

    public Fixed56x16(BigInteger value) {
        super(56, 16, value);
    }

    public Fixed56x16(int mBitSize, int nBitSize, BigInteger m, BigInteger n) {
        super(56, 16, m, n);
    }
}
