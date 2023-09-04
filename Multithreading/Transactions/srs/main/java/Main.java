import java.util.ArrayList;

public class Main {
    static ArrayList<Account> list = new ArrayList<>();

    public static void main(String[] args) {
        Bank bank = new Bank();
        for (int i = 1; i <= 200; i++) {
            list.add(new Account(String.valueOf(i), (int) (Math.random() * 80000)));//создаю аккаунты и добавляю их в созданный список
            //list.forEach(account -> {
            //    bank.addAccount(account);//});
            list.forEach(bank::addAccount);//добавила наш список аккаунтов в метод addAccount
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                list.forEach(account -> {
                    bank.transfer(String.valueOf((int) (Math.random() * 500)), String.valueOf((int) (Math.random() * 500)), (int) (Math.random() * 1000000));
                });
                }
        });
        t.start();

 //       ArrayList<Thread> threads = new ArrayList<>();//создаю множество потоков
 //       for (int j = 0; j < 10000; j++) {//допустим это будет 2000 потоков
  //          threads.add(new Thread(() -> {
  //              bank.transfer(String.valueOf((int) (Math.random() * 200)), String.valueOf((int) (Math.random() * 200)), (int) (Math.random() * 100000));
                //bank.transfer(getRandom());
  //          }));
  //      }
  //      threads.forEach(Thread::start);
        //threads.forEach(t -> t.start());


    }
}





        //создаю потоки
        //new Thread(() -> bank.transfer(String.valueOf(list), String.valueOf(list), (int) (Math.random() * 100000))).start();
        //new Thread(() -> bank.transfer("1", "2", (int) (Math.random() * 100000))).start();
        //new Thread(() -> bank.transfer(String.valueOf((int) (Math.random() * 500)), String.valueOf((int) (Math.random() * 500)), (int) (Math.random() * 1000000))).start();






//метод для возврата числа рандом
    //public static int getRandom (int count) {
    //    for (int i = 0; i < list.size(); i++) {
    //        count = (int) (Math.random() * 5000);
    //    }
    //    return count;
    //}

