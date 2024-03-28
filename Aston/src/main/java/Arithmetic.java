public class Arithmetic {

    public void sum(int a, int b) {
            if (a > b) {
                System.out.println("Итого: " + a + " > " + b);
            } else if (a < b) {
                System.out.println("Итого: " + a + " < " + b);
            } else
                System.out.println("Итого: " + a + " = " + b);
    }


    public void array(int[] count) {
        for (int i = 1; i <= count.length; i = i + 2) {
            System.out.println(count[i]);
        }
    }
}

