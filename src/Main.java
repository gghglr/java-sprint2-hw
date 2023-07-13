import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        // Поехали!
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        boolean checkGetMonthReport = false;
        boolean checkGetYearReport = false;
        while(true){
            printMenu();
            int command = scanner.nextInt();
            if(command == 1){
                System.out.println("Считывание произошло успешно");
                checkGetMonthReport = true;
                monthlyReport.readMonthlyReports();
                System.out.println("Хотите вывести информацию по месячным отчетам?");
                while (true) {
                    printYesNo();
                    int commandSecond = scanner.nextInt();
                    if (commandSecond == 1) {
                        monthlyReport.montInfo();
                        break;
                    } else if (commandSecond == 2) {
                        break;
                    } else {
                        System.out.println("Такой команды нет. Повторите ввод");
                    }
                }
            }
            else if (command == 2){
                System.out.println("Считывание произошло успешно");
                checkGetYearReport = true;
                yearlyReport.readYearReports();
                System.out.println("Хотите вывести информацию по годовому отчету?");
                while (true) {
                    printYesNo();
                    int commandSecond = scanner.nextInt();
                    if (commandSecond == 1) {
                        yearlyReport.profit();
                        System.out.println("Средний расход за все операции: " + yearlyReport.avarageRate());
                        System.out.println("Средний доход за все операции: " + yearlyReport.avarageProfit());
                        break;
                    } else if (commandSecond == 2) {
                        break;
                    } else {
                        System.out.println("Такой команды нет. Повторите ввод");
                    }
                }
            } else if (command == 3) {
                if (checkGetMonthReport && checkGetYearReport) {
                    Checker checker = new Checker(monthlyReport, yearlyReport);
                    if (checker.check()) {
                        System.out.println("Проверка пройдена");
                    } else {
                        System.out.println("Проверка не пройдена");
                    }
                }else if (!checkGetMonthReport){
                    System.out.println("Месячные отчеты не были считаны");
                } else if (!checkGetYearReport) {
                    System.out.println("Годовой отчет не был считан");
                }
            } else if (command == 0) {
                    System.out.println("Выход");
                    return;
                } else {
                    System.out.println("Такой команды пока нет, повторите ввод");
                }

        }
    }
    public static void  printMenu(){
        System.out.println("1 - Считать месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Проверить");
        System.out.println("0 - Выход из программы");
    }
    public static void printYesNo(){
        System.out.println("1 - Да");
        System.out.println("2 - Нет");
    }
}

