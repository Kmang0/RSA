import java.math.BigInteger;

public class RSAKey {
    public int bitLength;
    public BigInteger n;
    public BigInteger e;

    public RSAKey (int bitLength, BigInteger n, BigInteger exp) {
        this.bitLength = bitLength;
        this.n = n;
        this.e = exp;
    }
    public String getKey () {
        return bitLength + ";" + n + ";" + e;
    }


}
