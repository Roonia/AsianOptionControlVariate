package asianoption;

public class Normal {
    public static double cdf(double x) {
        return 0.5 * (1 + erf(x / Math.sqrt(2)));
    }

    public static double erf(double x) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(x));
        double tau = t * Math.exp(-x*x - 1.26551223 +
               t * (1.00002368 +
               t * (0.37409196 +
               t * (0.09678418 +
               t * (-0.18628806 +
               t * (0.27886807 +
               t * (-1.13520398 +
               t * (1.48851587 +
               t * (-0.82215223 +
               t * 0.17087277)))))))));
        return x >= 0 ? 1 - tau : tau - 1;
    }
}
