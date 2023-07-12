import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    public HashMap<String, ArrayList<MonthleReportSave>> savesMonth = new HashMap<>();
    public ArrayList<MonthleReportSave> saves;

    public void loadFile(String nameMon, String path) {
        String content = readFilesContentsOrNull(path);
        String[] lines = content.split("\r?\n"); // Коньки,TRUE,50,2000
        saves = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] part = line.split(",");
            String name = part[0];
            boolean isExp = Boolean.parseBoolean(part[1]);
            int quantity = Integer.parseInt(part[2]);
            int price = Integer.parseInt(part[3]);
            MonthleReportSave monthleReportSave = new MonthleReportSave(name, isExp, quantity, price);
            saves.add(monthleReportSave);
        }
        savesMonth.put(nameMon, saves);
    }

    public String montInfo() {
        int i = 1;
        for (String list : savesMonth.keySet()) {
            int maxSum = 0; //макс прибыль
            int minus = 0;
            String nameProd ="";
            String nameProfMinus = "";
            for (MonthleReportSave isList : savesMonth.get(list)) {
                if (isList.isExp == false) {
                    int take = isList.price * isList.quantity;
                    if (take > maxSum) {
                        maxSum = take;
                        nameProd = isList.name;
                    }
                }
                if (isList.isExp){
                    int take = isList.price * isList.quantity;
                    if (take > maxSum) {
                        minus = take;
                        nameProfMinus = isList.name;
                    }
                }
            }
            System.out.println("Самый прибыльный товар за " + i + " месяц " + nameProd + " прибыль от товара: " + maxSum);
            System.out.println("Самая большая трата за " + i + " месяц " + nameProfMinus + " трата " + minus);
            i +=1;
        }
        return "";
    }
        public String readFilesContentsOrNull (String path){
            try {
                return Files.readString(Path.of(path));
            } catch (IOException e) {
                System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
                return null;
            }
        }
}
