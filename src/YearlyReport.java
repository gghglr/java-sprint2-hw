
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
     public ArrayList<YearleReportSave> savesYear = new ArrayList<>();
     FileReader fileReader = new FileReader();
     public ArrayList<String> linesYear;
     public void readYearReports(){
          String fileName = "y.2021.csv";
          linesYear = fileReader.readFileContents(fileName);
          for (int i = 1; i < linesYear.size(); i++) {
               String line = linesYear.get(i); //01,1593150,false
               String[] parts = line.split(",");
               int monthNumber = Integer.parseInt(parts[0]);
               int amount = Integer.parseInt(parts[1]);
               boolean isExp = Boolean.parseBoolean(parts[2]);
               YearleReportSave yearleReportSave = new YearleReportSave(monthNumber, amount, isExp);
               savesYear.add(yearleReportSave);
          }
     }
     public String profit(){
          HashMap<Integer, Integer> profitMonth = new HashMap<>();
          int monthNum = 1;
          for (YearleReportSave yearleReportSave : savesYear) {
               if(yearleReportSave.isExp) {
                    profitMonth.put(yearleReportSave.monthNumber, yearleReportSave.amount + profitMonth.getOrDefault(yearleReportSave.monthNumber , 0));
               } else{
                    profitMonth.put(yearleReportSave.monthNumber, yearleReportSave.amount + profitMonth.getOrDefault(yearleReportSave.monthNumber , 0));
               }
          }
          for (Integer integer : profitMonth.keySet()) {
               System.out.println("Прибыль за " + monthNum + " месяц: " + profitMonth.get(integer));
               monthNum += 1;
          }
          return "";
     }
     public int avarageRate(){
          int rate = 0;
          int numFalse = 0;
          for(YearleReportSave yearleReportSave : savesYear) {
               if (!yearleReportSave.isExp) {
                    rate += yearleReportSave.amount;
                    numFalse +=1;
               }
          }
          if(numFalse == 0){
               return 0;
          }
          else{ return rate / numFalse;}
     }
     public int avarageProfit(){
          int sum = 0;
          int numTrue = 0;
          for(YearleReportSave yearleReportSave : savesYear){
               if(yearleReportSave.isExp){
                    sum += yearleReportSave.amount;
                    numTrue += 1;
               }
          }
          if (numTrue == 0) {
               return 0;
          }
          else {
               return sum / numTrue;
          }
     }

}
