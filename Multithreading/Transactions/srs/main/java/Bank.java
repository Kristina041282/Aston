import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class Bank implements Comparable<Bank> {

    private Map<String, Account> accounts;//кол-ция в которой хранятся счета клиентов (ключ = номер счета, значение = номер счета и деньги)
    private final Random random = new Random();

    public Bank () {
        this.accounts = new HashMap<>();
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {//метод вернет либо true либо false
        Thread.sleep(1000);//Thread.sleep()используется для приостановки выполнения основного потока на 1 секунду (1000 миллисекунд):
        return random.nextBoolean();//метод nextBoolean() возвращает следующее псевдослучайное, равномерно распределенное логическое значение
    }


    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccount, String toAccount, long amount) {//метод в котором переводим заданное кол-во денег между счетами клиентов
        Account fromAccountNumber;
        Account toAccountNumber;

        //if (fromAcc.getAccNumber().equals(toAcc.getAccNumber())) {
        //if (fromAcc.getAccNumber().hashCode() > toAcc.getAccNumber().hashCode()) {
        Account fromAcc = accounts.get(fromAccount);
        Account toAcc = accounts.get(toAccount);
        if (fromAcc.hashCode() > toAcc.hashCode()) {
            fromAccountNumber = toAcc;
            toAccountNumber = fromAcc;
        } else {
            fromAccountNumber = fromAcc;
            toAccountNumber = toAcc;
        }
        //synchronized (this) {//так как у нас обычный класс не со статическим методом будем синхронизироваться по целому объекту (прописав this)
        synchronized (fromAccountNumber) {//таким образом синхронизируется блок кода
            synchronized (toAccountNumber) {
                boolean check = false;

                if (fromAcc.getMoney() < amount) {
                    return;
                }

                if (amount > 0 && amount < 50000) {
                    if (!fromAcc.isBlock() && !toAcc.isBlock()) {
                        fromAcc.setMoney(fromAcc.getMoney() - amount);
                        toAcc.setMoney(toAcc.getMoney() + amount);
                        return;
                    }
                    return;
                }

                if (amount >= 50000 && fromAcc.getMoney() >= amount) {
                    if (!fromAcc.isBlock() && !toAcc.isBlock()) {
                        try {
                            check = isFraud(fromAccount, toAccount, amount);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (check) {//в случае если проверка выдаст true
                            fromAcc.blockedAcc();//то заблокируем оба счета
                            toAcc.blockedAcc();
                        } else {
                            fromAcc.setMoney(fromAcc.getMoney() - amount);
                            toAcc.setMoney(toAcc.getMoney() + amount);
                        }
                    }
                }
            }
        }
    }



        @Override
        public int compareTo(Bank o) {//создала метод для сравнения, чтобы в дальнейшем наши переводы были потокобезопасными
        return this.accounts.get("1").getAccNumber().compareTo(o.accounts.get("1").getAccNumber());
        }

        public Map<String, Account> getAccounts() {//создала геттер, чтобы суметь вытащить для сравнения ключи
        return this.accounts;
        }





    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */

    public void addAccount(Account account) {//создала метод для добавления аккаунтов в наш Банк
        if (!accounts.containsKey(account.getAccNumber())) {
            accounts.put(account.getAccNumber(), account);
        }
    }

    public long getBalance(String accountNum) {//этот метод возвращает остаток на счете конкретного клиента
        return accounts.get(accountNum).getMoney();
    }


     public long getSumAllAccounts() {//этот метод вернет полученную сумму всех счетов
            long sum = 0;
            for (Map.Entry<String, Account> acc : accounts.entrySet()) {
                sum = sum + acc.getValue().getMoney();
            }
            return sum;
        }

    @Override
    public String toString() {
        return "Bank{" +
                "accounts=" + accounts +
                '}';
    }
}

