import java.util.ArrayList;
import java.util.HashMap;

public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;

    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }
    public boolean check() {
        boolean сheck1 = true;
        HashMap<Integer, HashMap<Boolean, Integer>> report = new HashMap<>();//мапа где лежит ключ - Номер месяца и еще мапа где ключ - true/false + деньги
        // мапа по годам
        for (YearleReportSave save : yearlyReport.saves) {
            if(!report.containsKey(save.monthNumber)){
                report.put(save.monthNumber, new HashMap<>());
            }
            HashMap<Boolean, Integer> yearToCount = report.get(save.monthNumber);
            yearToCount.put(save.isExp, save.amount);
        }
        HashMap<Integer, HashMap<Boolean, Integer>> reportMonth = new HashMap<>();
        // мапа по месяцам
        int name1 = 0;
        for(String name : monthlyReport.savesMonth.keySet()){
            name1 = Integer.parseInt(name);
            for(MonthleReportSave reportM : monthlyReport.savesMonth.get(name)){
                if(!reportMonth.containsKey(name1)){
                    //парсим ключ и получем инт(число месяца)
                    reportMonth.put(name1, new HashMap<>());
                }
                HashMap<Boolean, Integer> monthToCount = reportMonth.get(name1);
                int sumProduct = reportM.price * reportM.quantity;
                monthToCount.put(reportM.isExp, monthToCount.getOrDefault(reportM.isExp , 0) + sumProduct);

            }
        }
        for (Integer montNum : report.keySet()) {
            HashMap<Boolean, Integer> titleToKnowTrueOrFalseForYear = report.get(montNum); //год
            HashMap<Boolean, Integer> titleToKnowTrueOrFalseForMonth = reportMonth.get(montNum); //месяцы
            if(titleToKnowTrueOrFalseForMonth == null){
                System.out.println("Месяц " + montNum + " есть в годовом, но нет в месячном");
                сheck1 = false;
                continue;
            }
            for (Boolean isBoolean : titleToKnowTrueOrFalseForYear.keySet()) {
                int countMoneyYear = titleToKnowTrueOrFalseForYear.get(isBoolean);
                int countMoneyMonth = titleToKnowTrueOrFalseForMonth.getOrDefault(isBoolean, 0);
                if(countMoneyYear != countMoneyMonth){
                    if(isBoolean){
                        System.out.println("В месяце " + montNum + " сумма расходов в годовом отчете: " + countMoneyYear + " ,а в месячном: " + countMoneyMonth);
                        сheck1 = false;
                    }
                    if(isBoolean == false){
                        System.out.println("В месяце " + montNum + " сумма доходов в годовом отчете: " + countMoneyYear + " ,а в месячном: " + countMoneyMonth);
                        сheck1 = false;
                    }
                }
            }
        }

        return сheck1;
    }
}
