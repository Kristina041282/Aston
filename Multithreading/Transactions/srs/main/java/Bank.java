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
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {//метод в котором переводим заданное кол-во денег между счетами клиентов
        Account fromAcc = accounts.get(fromAccountNum);//accounts.get(fromAccountNum) содержит в себе номер счета и сумму
        Account toAcc = accounts.get(toAccountNum);
        //synchronized (this) {//так как у нас обычный класс не со статическим методом будем синхронизироваться по целому объекту (прописав this)


            //fromAcc.getAccNumber().compareTo(fromAcc.getAccNumber());
        //synchronized (compareTo(fromAcc.getAccNumber()) )
            synchronized (fromAcc) {//таким образом синхронизируется блок кода
                synchronized (toAcc) {

                    boolean check = false;
                    if (amount > 0 && amount < 50000 && fromAcc.getMoney() >= amount) {
                        if (!fromAcc.isBlock() && !toAcc.isBlock()) {
                            //System.out.println("Общая сумма на всех счетах до перевода: " + getSumAllAccounts());
                            //System.out.println("На данный момент на счету: " + fromAcc.getMoney() + " " + "Переводим сумму: " + amount);
                            fromAcc.setMoney(fromAcc.getMoney() - amount);
                            toAcc.setMoney(toAcc.getMoney() + amount);
                            //System.out.println(" Остаток после перевода на счету: " + fromAcc.getMoney());
                            //System.out.println("Остаток на всех счетах после перевода: " + getSumAllAccounts());
                            //System.out.println(" переходим к следующей транзакции ->");
                        } else {
                            //System.out.println("Операция отменена, Ваши счета заблокированы:");
                            //System.out.println(" переходим к следующей транзакции ->");
                        }
                    }

                    if (amount >= 50000 && fromAcc.getMoney() >= amount) {
                        if (!fromAcc.isBlock() && !toAcc.isBlock()) {
                            //System.out.println("Для перевода суммы с выше 50_000 нам требуется не много времени на проверку мошенничества");
                            try {
                                check = isFraud(fromAccountNum, toAccountNum, amount);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if (check) {//в случае если проверка выдаст true
                                //System.out.println("Операция прервана, блокируем Ваши счета");
                                fromAcc.blockedAcc();//то заблокируем оба счета
                                toAcc.blockedAcc();
                                //System.out.println(" переходим к следующей транзакции ->");
                            } else {
                                //System.out.println("Проверка успешно прошла, перечисляем деньги");
                                fromAcc.setMoney(fromAcc.getMoney() - amount);
                                toAcc.setMoney(toAcc.getMoney() + amount);
                                //System.out.println(" переходим к следующей транзакции ->");
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

