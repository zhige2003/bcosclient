package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Fixed;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Fixed80x104 extends Fixed {
    public static final Fixed80x104 DEFAULT = new Fixed80x104(BigInteger.ZERO);

    public Fixed80x104(BigInteger value) {
        super(80, 104, value);
    }

    public Fixed80x104(int mBitSize, int nBitSize, BigInteger m, BigInteger n) {
        super(80, 104, m, n);
    }
}
