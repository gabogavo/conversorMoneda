package service;

import exception.ApiException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ExchangeRateService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/49284e77452eabf76edeecbe/latest/";

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) throws ApiException {
        try {
            String urlString = API_URL + fromCurrency;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new ApiException("Error al obtener los datos de la API.");
            }

            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");

            if (!conversionRates.has(toCurrency)) {
                throw new ApiException("Moneda de destino no soportada.");
            }

            double rate = conversionRates.get(toCurrency).getAsDouble();
            return amount * rate;

        } catch (Exception e) {
            throw new ApiException("Error durante la conversión: " + e.getMessage());
        }
    }
}