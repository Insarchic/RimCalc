import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = console.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] parts = input.split("[+\\-*/]");
        if (parts.length != 2)
            throw new Exception("Должны быть два операнда");
        oper = detectOper(input);
        if(Roman.isRoman(parts[0]) && Roman.isRoman(parts[1])) {
            num1 = Roman.convertToArabian(parts[0]);
            num2 = Roman.convertToArabian(parts[1]);
            isRoman = true;
        } else if(!Roman.isRoman(parts[0]) && !Roman.isRoman(parts[1])) {
            num1 = Integer.parseInt(parts[0]);
            num2 = Integer.parseInt(parts[1]);
            isRoman = false;
        } else {
            throw new Exception("Числа должны быть в одном формате ");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть в дипазоне от 1 до 10");
        }
        int arabian = counter(num1, num2, oper);
        if (isRoman) {
            if(arabian <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }
    static String detectOper(String input) {
        if(input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("/")) return "/";
        else if (input.contains("*")) return "*";
        else return null;
    }
    static int counter(int a, int b, String oper) {
        return switch (oper) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "/" -> a / b;
            default -> a * b;
        };
    }
}