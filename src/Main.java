import java.util.*;

//решение должно содержать данный класс
class Main {

    public static void main(String [] arg) {
        Scanner scanner = new Scanner(System.in);

        // считываем строку
        System.out.println("Введите простую математическую строку для вычисления");
        System.out.println("из 2 римских или 2 арабских чисел от 1 до 10");
        System.out.println("по формату 'число1' 'операция (+,-,*,/)' 'число2' ");
        System.out.println("например:  3 * 6  или  II + V  ");
        String sCalc = scanner.nextLine();

        if (sCalc.length() > 2) {
            System.out.println("Результат : ");
            System.out.println(calc(sCalc));
            System.out.println("Работа калькулятора завершена");

        } else {
            System.out.println("throws Exception //т.к. строка не является математической операцией12 ");
        }
    }

    //решение должно содержать данный метод
    public static String calc(String input) {
        String [] arabicArray = {"1","2","3","4","5","6","7","8","9","10"};
        String [] romesArray = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        String [] actions = {"+", "-", "/", "*"};

        //Определяем арифметическое действие:
        int actionIndex=-1, actionInclude = 0;
        for (int i = 0; i < actions.length; i++) {
            for (int k = 0; k < input.length(); k++) {
                if(actions[i].equals(input.substring(k, k+1))) {
                    actionIndex = i;
                    actionInclude++;
                }
            }
        }

//        System.out.println(actionIndex);
//        System.out.println(actionInclude);

        //Если 2 операции и более, завершаем программу
        if(actionInclude > 1) {
            return "throws Exception // 2 и более математические операции в выражении";
        }

        String  str1, str2;  // выделяем 1 и 2 числа из строки
        int posToValue1, posToValue2;      // позиция в массиве чисел
        boolean isPos = false; // числа в одном массиве
        boolean isRoman = false; // числа римское

//        System.out.println(input.indexOf(actions[actionIndex]));

        //Если не нашли арифметического действия, завершаем программу
        if(actionIndex == -1) {
            return "throws Exception //Нет математической операции в выражении";
        } else {
            //Делим строчку по найденному арифметическому знаку
 //       if (input.indexOf(actions[actionIndex]) > 0) {
            str1 = input.substring(0, input.indexOf(actions[actionIndex]));
            str1 = str1.trim();
            str2 = input.substring(input.indexOf(actions[actionIndex]) + 1);
            str2 = str2.trim();

            posToValue1 = findRomesArabic (arabicArray, str1) + 1;
            posToValue2 = findRomesArabic (arabicArray, str2) + 1;

            //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
            if ((posToValue1 >=1) && (posToValue2 >=1)) {
                isPos = true;
            }  else {
                posToValue1 = findRomesArabic (romesArray, str1) + 1;
                posToValue2 = findRomesArabic (romesArray, str2) + 1;

                if ((posToValue1 >=1) && (posToValue2 >=1)) {
                    isRoman = true;
                    isPos = true;
                }
            }

            if (!isPos) {
                return "throws Exception //т.к. не правильно введены числа. Надо 2 арабских или 2 римских ";
            } else {
                //выполняем с числами арифметическое действие
                int resultAction = switch (actions[actionIndex]) {
                    case "+" -> posToValue1 + posToValue2;
                    case "-" -> posToValue1 - posToValue2;
                    case "*" -> posToValue1 * posToValue2;
                    case "/" -> posToValue1 / posToValue2;
                    default -> 0;
                };

                if(isRoman) {
                    //если числа были римские, возвращаем результат в римском числе
                    return arabicToRomes(resultAction);
                } else{
                    //если числа были арабские, возвращаем результат в арабском числе
                    return "" + resultAction;
                }
            }
        }
    }

    // проверка на ввод римских или арабских чисел
    private static int findRomesArabic(String[] array, String element) {
        if (Objects.isNull(array))
            return -1;

        for (int i = 0; i < array.length; i++) {
           if (array[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    // конвертируем результат в римское число
    private static String arabicToRomes(int input) {

        if (input < 1 || input > 3999) {
            return "throws Exception //т.к. в Римской системе счисления нет 0 и отрицательных чисел ";
        }
        String s = "";
        while (input >= 1000) {
            s = s.concat("M");
            input -= 1000;        }
        while (input >= 900) {
            s = s.concat("CM");
            input -= 900;
        }
        while (input >= 500) {
            s = s.concat("D");
            input -= 500;
        }
        while (input >= 400) {
            s = s.concat("CD");
            input -= 400;
        }
        while (input >= 100) {
            s = s.concat("C");
            input -= 100;
        }
        while (input >= 90) {
            s = s.concat("XC");
            input -= 90;
        }
        while (input >= 50) {
            s = s.concat("L");
            input -= 50;
        }
        while (input >= 40) {
            s = s.concat("XL");
            input -= 40;
        }
        while (input >= 10) {
            s = s.concat("X");
            input -= 10;
        }
        while (input >= 9) {
            s = s.concat("IX");
            input -= 9;
        }
        while (input >= 5) {
            s = s.concat("V");
            input -= 5;
        }
        while (input >= 4) {
            s = s.concat("IV");
            input -= 4;
        }
        while (input >= 1) {
            s = s.concat("I");
            input -= 1;
        }
        return s;
    }
}