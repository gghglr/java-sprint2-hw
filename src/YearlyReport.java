import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
     public ArrayList<YearleReportSave> saves = new ArrayList<>();
     public void loadFile (String path){
          String content = readFilesContentsOrNull(path);
          String[] lines = content.split("\r?\n");
          for (int i = 1; i < lines.length; i++) {
               String line = lines[i]; //01,1593150,false
               String[] parts = line.split(",");
               int monthNumber = Integer.parseInt(parts[0]);
               int amount = Integer.parseInt(parts[1]);
               boolean isExp = Boolean.parseBoolean(parts[2]);
               YearleReportSave yearleReportSave = new YearleReportSave(monthNumber, amount, isExp);
               saves.add(yearleReportSave);
          }
     }
     public String profit(){
          HashMap<Integer, Integer> profitMonth = new HashMap<>();
          int monthNum = 1;
          for (YearleReportSave yearleReportSave : saves) {
               if(yearleReportSave.isExp) {
                    profitMonth.put(yearleReportSave.monthNumber, yearleReportSave.amount + profitMonth.getOrDefault(yearleReportSave.monthNumber , 0));
               } else if (!yearleReportSave.isExp) {
                    profitMonth.put(yearleReportSave.monthNumber, (-1) * yearleReportSave.amount + profitMonth.getOrDefault(yearleReportSave.monthNumber , 0));
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
          for(YearleReportSave yearleReportSave : saves) {
               if (!yearleReportSave.isExp) {
                    rate += yearleReportSave.amount;
                    numFalse +=1;
               }
          }
          return rate / numFalse;
     }
     public int avarageProfit(){
          int sum = 0;
          int numTrue = 0;
          for(YearleReportSave yearleReportSave : saves){
               if(yearleReportSave.isExp){
                    sum += yearleReportSave.amount;
                    numTrue += 1;
               }
          }
          return sum / numTrue;
     }
     public String readFilesContentsOrNull(String path){
          try {
               return Files.readString(Path.of(path));
          } catch (IOException e) {
               System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
               return null;
          }
     }

}
