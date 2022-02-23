import processing.core.PApplet;
import controlP5.*;

import java.util.List;


public class Factorator extends PApplet {
    ControlP5 cp5;

    public float value = 100;

    int timeSinceChange = 0;

    Numberbox numberbox;

    static final int NODE_DISTANCE = 20;
    static final int NODE_SIZE = 35;
    static final float HEIGHT_RATIO = 1.8f;

    int height;

    public void settings() {
        size(1000, 600);
    }

    public void setup() {
        cp5 = new ControlP5(this);

        numberbox = cp5.addNumberbox("numberBox")
                .setMin(1)
                .setDirection(ControlP5Constants.HORIZONTAL)
                .setPosition(width / 2f - 60, 10)
                .setSize(120, 30)
                .setScrollSensitivity(1.1f)
                .setValue(100);

        background(100);
    }

    public void draw() {
        if (numberbox.getValue() != value) {
            value = numberbox.getValue();
            timeSinceChange = 0;
        } else {
            timeSinceChange++;
        }

        if (timeSinceChange == 50) {
            background(100);
            drawTree(new PrimeFactorTree((int) value));
        }
    }

    private void drawTree(FactorTree tree) {
        height = tree.getHeight();
        float maxWidth = (float) ((Math.pow(2, height - 1) - 1) * (NODE_DISTANCE + NODE_SIZE));
        float x0 = width / 2f - maxWidth / 2f;

        for (int i = height; i > 0; i--) {
            List<Integer> level = tree.getLevel(i);
            float y = 80 + (NODE_DISTANCE * HEIGHT_RATIO) * (i - 1);
            float xDiff = (float) (maxWidth / (Math.pow(2, i - 1) + 1));
            for (int j = 0; j < level.size(); j++) {
                if (level.get(j) > 0) {
                    rectMode(RADIUS);
                    textAlign(CENTER);
                    textSize(18);
                    fill(255);
                    float x = x0 + xDiff * j;

                    if (i > 1) {
                        if (j % 2 == 0) {
                            line(x, y, x + xDiff / 2, y - (NODE_DISTANCE * HEIGHT_RATIO));
                        } else {
                            line(x, y, x - xDiff / 2, y - (NODE_DISTANCE * HEIGHT_RATIO));
                        }
                    }

                    ellipse(x, y, NODE_SIZE, NODE_SIZE);
                    fill(51);
                    text(String.valueOf(level.get(j)), x, y);
                }
            }
            x0 += xDiff / 2;
            maxWidth += xDiff;
        }
    }

    public static void main(String[] args) {
        String[] pArgs = new String[]{"Factorator"};

        PApplet.runSketch(pArgs, new Factorator());
    }
}