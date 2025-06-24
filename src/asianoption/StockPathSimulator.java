package asianoption;

import java.util.Random;

public class StockPathSimulator {
    private final double S0, r, sigma, T;
    private final int steps;
    private final Random rand = new Random();

    public StockPathSimulator(double S0, double r, double sigma, double T, int steps) {
        this.S0 = S0;
        this.r = r;
        this.sigma = sigma;
        this.T = T;
        this.steps = steps;
    }

    public double[] simulatePath() {
        double dt = T / steps;
        double[] path = new double[steps + 1];
        path[0] = S0;

        for (int i = 1; i <= steps; i++) {
            double z = rand.nextGaussian();
            path[i] = path[i - 1] * Math.exp((r - 0.5 * sigma * sigma) * dt + sigma * Math.sqrt(dt) * z);
        }
        return path;
    }
}
