package Task5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentBank {
    private List<BankAccount> accounts = new CopyOnWriteArrayList<>();

    public BankAccount createAccount(double balance) {
        BankAccount account = new BankAccount(balance);
        this.accounts.add(account);
        return account;
    }

    public boolean transfer(BankAccount accountOne, BankAccount accountTwo, double amounte) {
        if (amounte <= 0 || accountOne == accountTwo) return false;

        BankAccount firstLock = accountOne.getId() < accountTwo.getId() ? accountOne : accountTwo;
        BankAccount secondLock = accountOne.getId() < accountTwo.getId() ? accountTwo : accountOne;

        firstLock.getLock().lock();
        try {
            secondLock.getLock().lock();
            try {

                if (accountOne.withdraw(amounte)) {
                    accountTwo.deposit(amounte);
                    return true;
                } else return false;
            } finally {
                secondLock.getLock().unlock();
            }
        } finally {
            firstLock.getLock().unlock();
        }

    }

    public double getTotalBalance() {
        double total = 0;
        for (BankAccount account : accounts) {
            total += account.getBalance();
        }
        return total;
    }
}
