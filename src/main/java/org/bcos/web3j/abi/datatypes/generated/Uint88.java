package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Uint;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Uint88 extends Uint {
    public static final Uint88 DEFAULT = new Uint88(BigInteger.ZERO);

    public Uint88(BigInteger value) {
        super(88, value);
    }

    public Uint88(long value) {
        this(BigInteger.valueOf(value));
    }
}
