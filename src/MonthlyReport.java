import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    public HashMap<Integer, ArrayList<MonthleReportSave>> savesMonth = new HashMap<>();
    public ArrayList<MonthleReportSave> saves;
    public ArrayList<String> lines;
    FileReader fileReader = new FileReader();
    public void readMonthlyReports(){
        int nameMon = 1;
        for (int i = 1; i < 4; i++) {
            String fileName = "m.20210" + i + ".csv";
            lines = fileReader.readFileContents(fileName);
            saves = new ArrayList<>();
            for (int j = 1; j < lines.size(); j++) {
                String line = lines.get(j);
                String[] part = line.split(",");
                String name = part[0];
                boolean isExp = Boolean.parseBoolean(part[1]);
                int quantity = Integer.parseInt(part[2]);
                int price = Integer.parseInt(part[3]);
                MonthleReportSave monthleReportSave = new MonthleReportSave(name, isExp, quantity, price);
                saves.add(monthleReportSave);
            }
            savesMonth.put(nameMon, saves);
            nameMon +=1;
        }
    }
    public String montInfo() {
        int i = 1;
        for (Integer list : savesMonth.keySet()) {
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
}
