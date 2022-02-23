public class PrimeFactorTree extends FactorTree {
    public PrimeFactorTree(int n) {
        value = n;

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                left = new PrimeFactorTree(i, true);
                right = new PrimeFactorTree(n / i);
                break;
            }
        }
    }

    public PrimeFactorTree(int value, boolean prime) {
        this.value = value;
    }
}
