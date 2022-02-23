public class BalancedFactorTree extends FactorTree {
    public BalancedFactorTree(int value) {
        this.value = value;

        for (int i = (int) Math.sqrt(value); i > 1; i--) {
            if (value % i == 0) {
                left = new BalancedFactorTree(i);
                right = new BalancedFactorTree(value / i);
                break;
            }
        }
    }
}
