package java;

/**
 * Created by kevintrogant on 15.12.16.
 */
public class FNV {

    private static final long FNV_BASIS = 0xcbf29ce484222325L;
    private static final long FNV_PRIME = 0x100000001b3L;

    public static long FNV1a(byte[] data) {
        long hash = FNV_BASIS;
        for (int i = 0; i < data.length; i++) {
            hash = hash ^ (long)data[i];
            hash = hash * FNV_PRIME;
        }
        return hash;
    }

    public static String FNV1aStr(byte[] data) {
        long hash = FNV1a(data);
        return String.format("%x", hash);
    }
}
