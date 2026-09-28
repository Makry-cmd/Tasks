package Task6;

import java.util.concurrent.CompletableFuture;

public class DataAggregator {

    public static double fetchPrice(String productName) {
        try {
            Thread.sleep((long) (Math.random() * 2000 + 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Поток прерван во время получения цены", e);
        }

        if (Math.random() < 0.2) {
            throw new RuntimeException("Сервис цен временно недоступен для: " + productName);
        }

        return 899.99;
    }

    public static String fetchDescription(String productName) {
        try {
            Thread.sleep((long) (Math.random() * 2000 + 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Поток прерван во время получения описания", e);
        }

        if (Math.random() < 0.2) {
            throw new RuntimeException("Сервис описаний временно недоступен для: " + productName);
        }

        return "Мощный игровой ноутбук с отличной производительностью";
    }

    public static double fetchRating(String productName) {
        try {
            Thread.sleep((long) (Math.random() * 2000 + 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Поток прерван во время получения рейтинга", e);
        }

        if (Math.random() < 0.2) {
            throw new RuntimeException("Сервис рейтингов временно недоступен для: " + productName);
        }

        return 4.7;
    }

    public ProductInfo aggregateProductInfo(String productName) {

        CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(
                () -> fetchPrice(productName)
        );

        CompletableFuture<String> futureDescription = CompletableFuture.supplyAsync(
                () -> fetchDescription(productName)
        );

        CompletableFuture<Double> futureRating = CompletableFuture.supplyAsync(
                () -> fetchRating(productName)
        );

        CompletableFuture<Double> futurePriceSafe = futurePrice.exceptionally(ex -> {
            System.out.println("[Ошибка] Цена: " + ex.getMessage());
            return 0.0;
        });

        CompletableFuture<String> futureDescriptionSafe = futureDescription.exceptionally(ex -> {
            System.out.println("[Ошибка] Описание: " + ex.getMessage());
            return "Нет данных";
        });

        CompletableFuture<Double> futureRatingSafe = futureRating.exceptionally(ex -> {
            System.out.println("[Ошибка] Рейтинг: " + ex.getMessage());
            return 0.0;
        });

        CompletableFuture<ProductInfo> futureProduct = CompletableFuture
                .allOf(futurePriceSafe, futureDescriptionSafe, futureRatingSafe)
                .thenApply(v -> {
                    Double price = futurePriceSafe.join();
                    String description = futureDescriptionSafe.join();
                    Double rating = futureRatingSafe.join();
                    return new ProductInfo(productName, price, description, rating);
                });

        return futureProduct.join();
    }
}
