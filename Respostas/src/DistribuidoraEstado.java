public class DistribuidoraEstado {
    public static void main(String[] args) {
        double sp = 67836.43;
        double rj = 36678.66;
        double mg = 29229.88;
        double es = 27165.48;
        double outros = 19849.53;

        double total = sp + rj + mg + es + outros;

        double spPercentual = (sp / total) * 100;
        double rjPercentual = (rj / total) * 100;
        double mgPercentual = (mg / total) * 100;
        double esPercentual = (es / total) * 100;
        double outrosPercentual = (outros / total) * 100;

        System.out.printf("SP - %.2f%%\n", spPercentual);
        System.out.printf("RJ - %.2f%%\n", rjPercentual);
        System.out.printf("MG - %.2f%%\n", mgPercentual);
        System.out.printf("ES - %.2f%%\n", esPercentual);
        System.out.printf("Outros - %.2f%%\n", outrosPercentual);
    }
}
