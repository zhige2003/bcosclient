package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Uint;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Uint224 extends Uint {
    public static final Uint224 DEFAULT = new Uint224(BigInteger.ZERO);

    public Uint224(BigInteger value) {
        super(224, value);
    }

    public Uint224(long value) {
        this(BigInteger.valueOf(value));
    }
}
