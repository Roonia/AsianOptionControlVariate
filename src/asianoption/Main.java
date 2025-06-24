package asianoption;

public class Main {
    public static void main(String[] args) {
        double result = ControlVariatePricer.priceWithControlVariate(
                10000, 100.0, 0.05, 0.2, 1.0, 50, 100.0);
        System.out.println("Asian option price (with control variate): " + result);
    }
}
