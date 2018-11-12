package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Fixed;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Fixed40x56 extends Fixed {
    public static final Fixed40x56 DEFAULT = new Fixed40x56(BigInteger.ZERO);

    public Fixed40x56(BigInteger value) {
        super(40, 56, value);
    }

    public Fixed40x56(int mBitSize, int nBitSize, BigInteger m, BigInteger n) {
        super(40, 56, m, n);
    }
}
