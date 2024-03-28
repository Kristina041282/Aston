import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static int sum;

    public static void main(String[] args) {

        Arithmetic arithmetic = new Arithmetic();
        //arithmetic.array(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        //arithmetic.sum(3, 5);


        //todo
        // - сравнивает эти два числа и возвращает результат сравнения путем вывода в консоль одного из вариантов: "a > b", "a < b" или "a = b";

        for (; ; ) {
            System.out.println("Введите первое число");
            int a = scanner.nextInt();
            System.out.println("Введите второе число");
            int b = scanner.nextInt();

            if (a > b) {
                System.out.println(a + " > " + b);
            }
            if (a < b) {
                System.out.println(a + " < " + b);
            }
            if (a == b) {
                System.out.println(a + " = " + b);
            }


            //todo
            // - совершает с этими числами операции сложения, вычитания, деления и умножения и результат выводит в консоль.

                System.out.println("Введите первое  число");
                int x = scanner.nextInt();
                System.out.println("Введите второе число");
                int y = scanner.nextInt();

                sum = x + y;
                System.out.println(x + " + " + y + " = " + sum);

                sum = x - y;
                System.out.println(x + " - " + y + " = " + sum);

                sum = x * y;
                System.out.println(x + " * " + y + " = " + sum);

                sum = x / y;
                System.out.println(x + " / " + y + " = " + sum);


                //todo  Написать программу, которая принимает на вход две строки (a и b) и сравнивает их.
                // В результате сравнения в консоль должно быть выведено одно из сообщений: "Строки неидентичны" или "Строки идентичны"

                scanner = new Scanner(System.in);
                System.out.println("Введите первое сообщение");
                String str1 = scanner.nextLine();
                System.out.println("Введите второе сообщение");
                String str2 = scanner.nextLine();
                if (str1.equals(str2)) {
                    System.out.println("Строки идентичны");
                } else
                    System.out.println("Строки неидентичны");


                //todo   Задан массив целых чисел: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]. Необходимо написать программу, которая выведет в консоль все чётные числа.

                int[] massiv = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                for (int i = 1; i <= massiv.length; i = i + 2) {
                    System.out.println(massiv[i]);

            }
        }
    }
}

