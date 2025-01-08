package StampaFigura.Forme;

public class Ellisse extends Forma {
    private double asseMaggiore;
    private double asseMinore;
    public Ellisse(double asseMaggiore, double asseMinore) {
        this.asseMaggiore = asseMaggiore;
        this.asseMinore = asseMinore;
    }

    @Override
    public double area() {              //A = π · a · b
        return (asseMaggiore / 2.0) * (asseMinore / 2.0) * Math.PI;
    }

    @Override
    public double perimetro() {
        double semiasseMaggiore = asseMaggiore / 2.0;
        double semiasseMinore = asseMinore / 2.0;
        // 2π√ ( (a^2+b^2)/ (2)),
        return 2.0 * Math.PI *Math.sqrt((Math.pow(semiasseMaggiore, 2.0) + Math.pow(semiasseMinore, 2.0)) / 2.0);
    }

    @Override
    public void disegna() {
        int asseMaggioreIntero = (int) Math.round(asseMaggiore);
        int asseMinoreIntero = (int) Math.round(asseMinore);
        double fattoreScalaX = 2.0; 
        for (int y = -asseMinoreIntero; y <= asseMinoreIntero; y++) {
            for (int x = (int) (-asseMaggioreIntero * fattoreScalaX); x <= asseMaggioreIntero * fattoreScalaX; x++) {
                if ((Math.pow(x / fattoreScalaX, 2) / Math.pow(asseMaggioreIntero, 2)) +
                    (Math.pow(y, 2) / Math.pow(asseMinoreIntero, 2)) <= 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    public double getAsseMaggiore() {
        return this.asseMaggiore;
    }
    public double getAsseMinore() {
        return this.asseMinore;
    }
}