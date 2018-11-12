package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Fixed;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Fixed120x64 extends Fixed {
    public static final Fixed120x64 DEFAULT = new Fixed120x64(BigInteger.ZERO);

    public Fixed120x64(BigInteger value) {
        super(120, 64, value);
    }

    public Fixed120x64(int mBitSize, int nBitSize, BigInteger m, BigInteger n) {
        super(120, 64, m, n);
    }
}
