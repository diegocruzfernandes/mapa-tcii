import mapa.Data;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Demo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter date DD/MM/YYYY");

        String str = scan.next();

        DateConvert dateConvert = new DateConvert();
        dateConvert.convert(str);

        scan.close();

        Data _data = new Data();

        Data oData = _data.validate(dateConvert.day, dateConvert.month, dateConvert.year);
        System.out.println("- Objeto Data");
        System.out.println(oData);
        System.out.println(oData.getDay() + "/" + oData.getMonth() + "/" + oData.getYear());

        System.out.println("- String da data com o dia anterior");
        String lastDay = _data.lastDay(dateConvert.day, dateConvert.month, dateConvert.year);
        System.out.println(lastDay);
    }
}

class DateConvert {
    public int day;
    public int month;
    public int year;

    public void convert(String dateStr) {
        try {
            String[] dateSeparated = dateStr.split("/");

            this.day = parseInt(dateSeparated[0]);
            this.month = parseInt(dateSeparated[1]);
            this.year = parseInt(dateSeparated[2]);
        } catch (Exception ex) {
            System.out.println("Houve um erro ao tentar convertar a data!");
            System.out.println(ex.getMessage());
            System.exit(0);
            return;
        }
    }
}