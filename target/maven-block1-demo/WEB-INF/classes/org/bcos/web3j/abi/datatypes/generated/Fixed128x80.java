package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Fixed;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Fixed128x80 extends Fixed {
    public static final Fixed128x80 DEFAULT = new Fixed128x80(BigInteger.ZERO);

    public Fixed128x80(BigInteger value) {
        super(128, 80, value);
    }

    public Fixed128x80(int mBitSize, int nBitSize, BigInteger m, BigInteger n) {
        super(128, 80, m, n);
    }
}
