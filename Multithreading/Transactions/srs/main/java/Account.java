public class Account {

    //у каждого счета есть два св-ва это номер счета и кол-во денег на этом счете
    private long money;
    private final String accNumber;
    //private volatile boolean isBlock;//по умолчанию задаю ей false
    private boolean isBlock = false;

    public Account(String accNumber, int money) {
        this.accNumber = accNumber;
        this.money = money;
        //isBlock = false;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        //this.accNumber = accNumber;
    }

    public void setBlock(boolean isBlock) { this.isBlock = isBlock; }//инициализировала

    public boolean isBlock() { return isBlock; }

    public void blockedAcc() {//создала метод для блокировки счетов
        isBlock = true;//так как может вернуться true с проверки метода isFraud, нужно проверять не заблокированы ли счета
    }
}
