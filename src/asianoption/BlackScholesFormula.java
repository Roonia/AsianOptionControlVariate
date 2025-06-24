package asianoption;

public class BlackScholesFormula {
    public static double callPrice(double S, double K, double r, double sigma, double T) {
        double d1 = (Math.log(S / K) + (r + 0.5 * sigma * sigma) * T) / (sigma * Math.sqrt(T));
        double d2 = d1 - sigma * Math.sqrt(T);
        return S * Normal.cdf(d1) - K * Math.exp(-r * T) * Normal.cdf(d2);
    }
}
