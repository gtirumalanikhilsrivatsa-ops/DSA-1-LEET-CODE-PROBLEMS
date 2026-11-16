class Solution {

    private static final long MOD = 1000000007;

    public int monkeyMove(int n) {
        long ans = fastPow(2, n);

        ans = (ans - 2 + MOD) % MOD;

        return (int) ans;
    }

    private long fastPow(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }

            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }
}
