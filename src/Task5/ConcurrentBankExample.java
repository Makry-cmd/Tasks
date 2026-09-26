package Task5;

public class ConcurrentBankExample {
    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(1000);
        BankAccount account2 = bank.createAccount(500);


        System.out.println("Баланс счетов до операций:");
        System.out.println(account1);
        System.out.println(account2);
        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, 200));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, 100));


        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Баланс счетов после операций:");
        System.out.println(account1);
        System.out.println(account2);

        // Вывод общего баланса
        System.out.println("Total balance: " + bank.getTotalBalance());
    }
}
