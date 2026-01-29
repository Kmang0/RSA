import java.util.BitSet;
import java.math.BigInteger;

public class RSA {

    public static RSAKey parseKey(String keyString) {
        String[] parts = keyString.split(";");

        int bitLength = Integer.parseInt(parts[0]);
        BigInteger n = new BigInteger(parts[1]);
        BigInteger exp = new BigInteger(parts[2]);

        return new RSAKey(bitLength, n, exp);
    }

    public static String encrypt(String msg, RSAKey privKey) {
        byte[] msgBytes = msg.getBytes();
        BitSet bSet = BitSet.valueOf(msgBytes);
        long[] longArray = bSet.toLongArray();

        StringBuilder encryptedMsg = new StringBuilder();
        for (int i = 0; i < longArray.length; i++) {
            BigInteger m = BigInteger.valueOf(longArray[i]);
            BigInteger c = m.modPow(privKey.e, privKey.n);
            encryptedMsg.append(c.toString());
            if (i < longArray.length - 1) {
                encryptedMsg.append(",");
            }
        }
        return encryptedMsg.toString();
    }

    public static String decrypt(String encryptedMessage, RSAKey privKey) {
        String[] encryptedNumbers = encryptedMessage.split(",");
        BigInteger[] decryptedBigIntegers = new BigInteger[encryptedNumbers.length];
        for (int i = 0; i < encryptedNumbers.length; i++) {
            BigInteger encryptedBigInt = new BigInteger(encryptedNumbers[i]);
            decryptedBigIntegers[i] = encryptedBigInt.modPow(privKey.e, privKey.n); // RSA decryption
        }
        long[] decryptedLongs = new long[decryptedBigIntegers.length];
        for (int i = 0; i < decryptedBigIntegers.length; i++) {
            decryptedLongs[i] = decryptedBigIntegers[i].longValueExact();
        }
        BitSet bitSet = BitSet.valueOf(decryptedLongs);
        byte[] byteArray = bitSet.toByteArray();
        return new String(byteArray);
    }


}
