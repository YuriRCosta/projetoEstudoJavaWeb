import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Distribuidora {

    public static void main(String[] args) {

        // Lê o arquivo JSON contendo os valores de faturamento diário
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("faturamento.json"));
            JSONArray faturamento = (JSONArray) obj;

            // Cria uma lista com os valores de faturamento diário
            List<Double> valores = new ArrayList<Double>();
            for (int i = 0; i < faturamento.size(); i++) {
                JSONObject dia = (JSONObject) faturamento.get(i);
                double valor = Double.parseDouble(dia.get("valor").toString());
                valores.add(valor);
            }

            // Calcula o menor valor de faturamento diário
            double menorValor = Collections.min(valores);

            // Calcula o maior valor de faturamento diário
            double maiorValor = Collections.max(valores);

            // Calcula a média mensal de faturamento diário
            double media = 0;
            int diasComFaturamento = 0;
            for (int i = 0; i < faturamento.size(); i++) {
                JSONObject dia = (JSONObject) faturamento.get(i);
                double valor = Double.parseDouble(dia.get("valor").toString());
                boolean temFaturamento = (boolean) dia.get("temFaturamento");
                if (temFaturamento) {
                    media += valor;
                    diasComFaturamento++;
                }
            }
            media /= diasComFaturamento;

            // Conta o número de dias em que o valor de faturamento diário foi superior à média mensal
            int diasAcimaDaMedia = 0;
            for (int i = 0; i < faturamento.size(); i++) {
                JSONObject dia = (JSONObject) faturamento.get(i);
                double valor = Double.parseDouble(dia.get("valor").toString());
                boolean temFaturamento = (boolean) dia.get("temFaturamento");
                if (temFaturamento && valor > media) {
                    diasAcimaDaMedia++;
                }
            }

            // Formata a saída dos resultados
            DecimalFormat df = new DecimalFormat("#0.00");
            System.out.println("Menor valor de faturamento diário: R$ " + df.format(menorValor));
            System.out.println("Maior valor de faturamento diário: R$ " + df.format(maiorValor));
            System.out.println("Número de dias com faturamento acima da média mensal: " + diasAcimaDaMedia);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
