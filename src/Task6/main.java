package Task6;

import java.util.concurrent.CompletableFuture;

public class main {
    static void main() {
        DataAggregator aggregator = new DataAggregator();

        System.out.println("Начинаем сбор данных о товаре 'Ноутбук'...");
        long startTime = System.currentTimeMillis();

        // Метод сам дождётся результата внутри — блокирует главный поток
        ProductInfo product = aggregator.aggregateProductInfo("Ноутбук");

        long endTime = System.currentTimeMillis();

        System.out.println("\nРезультат получен за " + (endTime - startTime) + " мс");
        System.out.println(product);
    }
}
