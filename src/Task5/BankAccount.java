package Task5;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    private final int id;
    private double balance;
    private final Lock lock = new ReentrantLock();

    public BankAccount(double initialBalance) {
        this.id = idGenerator.incrementAndGet();
        this.balance = initialBalance;
    }

    public int getId() {
        return id;
    }

    public Lock getLock() {
        return lock;
    }

    public void deposit(double amount) {
        if (amount <= 0) return;
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) return false;

        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString(){
        return "id = "+ id+
                " balance = "+ balance;
    }
}