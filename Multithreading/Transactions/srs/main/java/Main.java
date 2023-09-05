import java.util.ArrayList;

public class Main {
    static ArrayList<Account> list = new ArrayList<>();

    public static void main(String[] args) {
        Bank bank = new Bank();
        for (int i = 1; i <= 200; i++) {
            list.add(new Account(String.valueOf(i), (int) (Math.random() * 80000)));//создаю аккаунты и добавляю их в созданный список
            list.forEach(bank::addAccount);//добавила наш список аккаунтов в метод addAccount
        }

        ArrayList<Thread> threads = new ArrayList<>();//создаю множество потоков
        for (int j = 0; j < 150; j++) {//допустим это будет 2000 потоков
            threads.add(new Thread(() -> {
                bank.transfer(String.valueOf((int) (Math.random() * 200)), String.valueOf((int) (Math.random() * 200)), (int) (Math.random() * 100000));
            }));
        }
        threads.forEach(Thread::start);
        //threads.forEach(t -> t.start());

    }
}





        //создаю потоки
        //new Thread(() -> bank.transfer(String.valueOf(list), String.valueOf(list), (int) (Math.random() * 100000))).start();
        //new Thread(() -> bank.transfer("1", "2", (int) (Math.random() * 100000))).start();
        //new Thread(() -> bank.transfer(String.valueOf((int) (Math.random() * 500)), String.valueOf((int) (Math.random() * 500)), (int) (Math.random() * 1000000))).start();

