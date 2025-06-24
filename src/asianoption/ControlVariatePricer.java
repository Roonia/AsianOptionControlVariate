package asianoption;

public class ControlVariatePricer {
    public static double priceWithControlVariate(
            int nPaths, double S0, double r, double sigma, double T,
            int steps, double strike) {

        StockPathSimulator simulator = new StockPathSimulator(S0, r, sigma, T, steps);
        double[] asianPayoffs = new double[nPaths];
        double[] europeanPayoffs = new double[nPaths];

        for (int i = 0; i < nPaths; i++) {
            double[] path = simulator.simulatePath();
            asianPayoffs[i] = OptionPayoffCalculator.asianCallPayoff(path, strike);
            europeanPayoffs[i] = OptionPayoffCalculator.europeanCallPayoff(path[steps], strike);
        }

        double meanAsian = mean(asianPayoffs);
        double meanEuropean = mean(europeanPayoffs);
        double cov = covariance(asianPayoffs, europeanPayoffs);
        double var = variance(europeanPayoffs);
        double beta = cov / var;

        double europeanExact = BlackScholesFormula.callPrice(S0, strike, r, sigma, T);
        return Math.exp(-r * T) * (meanAsian + beta * (europeanExact - meanEuropean));
    }

    private static double mean(double[] arr) {
        double sum = 0.0;
        for (double x : arr) sum += x;
        return sum / arr.length;
    }

    private static double variance(double[] arr) {
        double m = mean(arr);
        double sum = 0.0;
        for (double x : arr) sum += (x - m) * (x - m);
        return sum / (arr.length - 1);
    }

    private static double covariance(double[] x, double[] y) {
        double meanX = mean(x), meanY = mean(y);
        double sum = 0.0;
        for (int i = 0; i < x.length; i++) {
            sum += (x[i] - meanX) * (y[i] - meanY);
        }
        return sum / (x.length - 1);
    }
}
