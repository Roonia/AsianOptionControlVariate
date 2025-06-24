package asianoption;

public class OptionPayoffCalculator {
    public static double asianCallPayoff(double[] path, double strike) {
        double sum = 0.0;
        for (double s : path) {
            sum += s;
        }
        double average = sum / path.length;
        return Math.max(average - strike, 0);
    }

    public static double europeanCallPayoff(double S_T, double strike) {
        return Math.max(S_T - strike, 0);
    }
}
