import java.math.BigInteger;

public class FermatAlg {

    public static BigInteger[] fermatFactor(BigInteger n) {
        BigInteger x = sqrt(n).add(BigInteger.ONE);
        BigInteger y;
        BigInteger ySquared;

        while (true) {
            ySquared = x.pow(2).subtract(n);
            y = sqrt(ySquared);

            if (y.pow(2).equals(ySquared)) {
                BigInteger p = x.add(y);
                BigInteger q = x.subtract(y);
                return new BigInteger[]{p, q};
            }
            x = x.add(BigInteger.ONE);
        }
    }

    private static BigInteger sqrt(BigInteger n) {
        BigInteger x = n;
        BigInteger y = x.add(BigInteger.ONE).divide(BigInteger.TWO);
        while (y.compareTo(x) < 0) {
            x = y;
            y = x.add(n.divide(x)).divide(BigInteger.TWO);
        }
        return x;
    }

    public static RSAKey breakRSA(RSAKey key) {
        BigInteger e = key.e;
        BigInteger n = key.n;

        BigInteger[] factors = fermatFactor(n);
        BigInteger p = factors[0];
        BigInteger q = factors[1];
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = e.modInverse(phi);

        return new RSAKey(key.bitLength, n, d);
    }

    public static void main(String[] args) {
        RSAKey leakedKey = new RSAKey(40, new BigInteger("559413815783"), new BigInteger("108075261689"));
        RSAKey privateKey = breakRSA(leakedKey);

        BigInteger ciphertext = new BigInteger("448338948862");
        BigInteger plaintext = ciphertext.modPow(privateKey.e, privateKey.n);
        String message = new String(plaintext.toByteArray());
        message = new StringBuilder(message).reverse().toString();

        System.out.println("Decrypted Message: " + message);
    }
}
