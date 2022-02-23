import java.util.ArrayList;
import java.util.List;

public abstract class FactorTree {
    public int value;

    public FactorTree left = null, right = null;

    public List<Integer> getLevel(int level) {
        List<Integer> values = new ArrayList<>();
        
        if (level == 1) {
            values.add(value);
            return values;
        }
        
        if (left != null) {
            values.addAll(left.getLevel(level - 1));
        } else {
            for (int i = 0; i < Math.pow(2, level - 2); i++) {
                values.add(-1);
            }
        }

        if (right != null) {
            values.addAll(right.getLevel(level - 1));
        } else {
            for (int i = 0; i < Math.pow(2, level - 2); i++) {
                values.add(-1);
            }
        }

        return values;
    }
    
    public int getMaxWidth() {
        int height = getHeight();
        int maxWidth = 0;

        for (int i = 1; i <= height; i++) {
            int width = getWidth(i);
            if (width > maxWidth) {
                maxWidth = width;
            }
        }

        return maxWidth;
    }

    public int getWidth(int level) {
        if (level == 1) {
            return 1;
        }

        if (left != null && right != null) {
            return left.getWidth(level - 1) + right.getWidth(level - 1);
        } else if (left != null) {
            return left.getWidth(level - 1);
        } else if (right != null) {
            return right.getWidth(level - 1);
        }

        return 1;
    }

    public int getHeight() {
        int l = 0;
        int r = 0;

        if (left != null) {
            l = left.getHeight();
        }
        if (right != null) {
            r = right.getHeight();
        }

        if (l > r) {
            return l + 1;
        }
        return r + 1;
    }

    @Override
    public String toString() {
        if (left != null) {
            return value + ": " + left + ", " + right.toString();
        }

        return String.valueOf(value);
    }
}
