package com.example.components;

import java.util.Scanner;

public class ATM {
    private static double balance = 1000.0; // Начальный баланс
    private static final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Добро пожаловать в банкомат!");

        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.print("Введите PIN-код: ");
            String inputPin = scanner.nextLine();
            // Уникальный PIN-код для пользователя
            String pinCode = "1234";
            if (inputPin.equals(pinCode)) {
                System.out.println("Аутентификация прошла успешно.");
                break;
            } else {
                System.out.println("Неверный PIN-код. Попробуйте снова.");
            }
            if (attempts == 2) {
                System.out.println("Превышено количество попыток. Выход из системы.");
                scanner.close();
                return;
            }
        }

        // Основное меню
        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    checkBalance();
                    break;
                case "2":
                    withdrawMoney();
                    break;
                case "3":
                    System.out.println("Спасибо за использование нашего банкомата. До свидания!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Некорректный выбор. Повторите попытку.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nВыберите операцию:");
        System.out.println("1. Проверить баланс");
        System.out.println("2. Снять деньги");
        System.out.println("3. Выйти");
        System.out.print("Введите номер операции: ");
    }

    private static void checkBalance() {
        System.out.println("Ваш текущий баланс: " + balance + " рублей");
    }

    private static void withdrawMoney() {
        System.out.print("Введите сумму для снятия: ");
        String amountStr = scanner.nextLine();
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                System.out.println("Некорректная сумма.");
            } else if (amount > balance) {
                System.out.println("Недостаточно средств.");
            } else {
                balance -= amount;
                System.out.println("Вы сняли " + amount + " рублей. Оставшийся баланс: " + balance);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода суммы.");
        }
    }
}
