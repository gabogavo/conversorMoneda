
import service.ExchangeRateService;
import util.ConsoleHelper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeRateService exchangeRateService = new ExchangeRateService();

        ConsoleHelper.printWelcomeMessage();

        try {
            System.out.print("Ingrese la moneda de origen (por ejemplo, USD): ");
            String fromCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Ingrese la moneda de destino (por ejemplo, EUR): ");
            String toCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Ingrese el monto a convertir: ");
            double amount = scanner.nextDouble();

            double convertedAmount = exchangeRateService.convertCurrency(fromCurrency, toCurrency, amount);

            System.out.printf("El monto convertido es: %.2f %s%n", convertedAmount, toCurrency);

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }

        scanner.close();
    }
}