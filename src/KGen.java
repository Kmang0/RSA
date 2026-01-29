import java.math.BigInteger;
import java.security.SecureRandom;

public class KGen {
    private BigInteger n, e, d;
    private int bitLength;
    private static final SecureRandom random = new SecureRandom();

    public KGen(int bitLength) {
        this.bitLength = bitLength;
        generateKeys();
    }

    private void generateKeys() {
        BigInteger p, q, phi;
        do {
            p = BigInteger.probablePrime(bitLength / 2, random);
            q = BigInteger.probablePrime(bitLength / 2, random);
            n = p.multiply(q);
        } while (n.bitLength() != bitLength);

        phi = n.subtract(p).subtract(q).add(BigInteger.ONE);

        do {
            e = new BigInteger(bitLength - 1, random);
        } while (e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(phi) >= 0 || !e.gcd(phi).equals(BigInteger.ONE));

        d = e.modInverse(phi);
    }

    public String getPublicKey() {
        return bitLength + ";" + n + ";" + e;
    }

    public String getPrivateKey() {
        return bitLength + ";" + n + ";" + d;
    }

    public static KGen parseKey(String keyString) {
        String[] parts = keyString.split(";");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid key format");
        }

        int bitLength = Integer.parseInt(parts[0]);
        BigInteger n = new BigInteger(parts[1]);
        BigInteger keyPart = new BigInteger(parts[2]);

        KGen kgen = new KGen(bitLength);
        kgen.n = n;
        if (keyString.contains(",")) {
            kgen.e = keyPart;
        } else {
            kgen.d = keyPart;
        }

        return kgen;
    }
}
