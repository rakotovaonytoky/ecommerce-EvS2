import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static String calculPourcentage(String qteIngredients, String qteProduitsEnVente) {
        double result = 0;
        double qteIngredients1 = Double.parseDouble(qteIngredients);
        // System.out.println("qteIngredients :" + qteIngredients1);
        double qteProduits1 = Double.parseDouble(qteProduitsEnVente);
        // System.out.println("qteProduits :" + qteProduitsEnVente);

        result = (qteIngredients1 * 100) / qteProduits1;
        result = (Math.round(result * 1000.0) / 1000.0);
        System.out.println("pourcentage :" + (int) result);

        return Double.toString((int) result);
    }

    public static String[] calculQuantiteProduitNecessaire(String qteIngredients, String qteProduitsEnVente) {

        String testconversion = calculPourcentage(qteIngredients, qteProduitsEnVente);
        double calculPourcentage = Double.parseDouble(testconversion);
        // System.out.println("pourcentage :" + calculPourcentage);
        double modulo = calculPourcentage / 100;
        double qteIngredients1 = Float.parseFloat(qteIngredients);
        double qteProduits1 = Float.parseFloat(qteProduitsEnVente);
        double resultatOp = qteIngredients1 - qteProduits1;
        String quantite = "";
        String reste = "";
        if ((modulo % 1) == 0) {
            // System.out.print("xzcxvsdvsdvsdvsdvsdbvsdsdfdfb");
            quantite = String.valueOf(Math.round(calculPourcentage / 100));
            reste = "0";
        } else {
            if (resultatOp > 0) {
                quantite = String.valueOf(Math.round(calculPourcentage / 100));
                double temp = modulo - (int) modulo;
                reste = Double.toString(temp);
            } else {
                quantite = String.valueOf(Math.round(calculPourcentage / 100));
                double temp = modulo - (int) modulo;
                BigDecimal bd = new BigDecimal(1 - temp).setScale(2, RoundingMode.HALF_UP);
                double newInput = bd.doubleValue();
                reste = Double.toString(newInput);
            }
        }
        String[] quantityWithReste = new String[2];
        quantityWithReste[0] = quantite;
        quantityWithReste[1] = reste;
        return quantityWithReste;
    }

    public static void main(String[] args) {
        String[] qte = calculQuantiteProduitNecessaire("0.2", "0.25");
        System.out.println("quantite produit necessaire" + qte[0]);
        System.out.println("reste" + qte[1]);
    }
}