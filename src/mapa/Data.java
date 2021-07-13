package mapa;

import java.util.HashMap;
import java.util.Map;

/**
 * Objeto ENUM com os valores constantes dos meses do ano
 */
enum Month {
    JANUARY   (1, 31),
    FEBRUARY  (2, 29),
    MARCH     (3, 31),
    APRIL     (4, 30),
    MAY       (5, 31),
    JUNE      (6, 30),
    JULY      (7, 31),
    AUGUST    (8, 31),
    SEPTEMBER (9, 30),
    OCTOBER   (10, 31),
    NOVEMBER  (11, 30),
    DECEMBER  (12, 31);

    private final int number;
    private final int maxDay;

    /**
     * Gera uma lista com os meses e sua referência e número máximo de dias
     */
    private static final Map<Integer, Integer> lookup = new HashMap<Integer, Integer>();
    static  {
        for (Month m: Month.values()) {
            lookup.put(m.getNumber(), m.getMaxDay());
        }
    }

    /**
     * Construtor
     * @param number Número de referência do mês
     * @param maxDay Número de dias máximo
     */
    Month(Integer number, int maxDay) {
        this.number = number;
        this.maxDay = maxDay;
    }

    /**
     * Obtem o número de referência de um mês
     * @return int o número de referência
     */
    public int getNumber() {
        return number;
    }

    /**
     * Obtem o número máximo de dias de um mês
     * @return int com o número máximo de dias
     */
    public int getMaxDay() {
        return  maxDay;
    }

    /**
     * Obtem o número máximo de dias de um mês
     * @param number Número do mês
     * @return int o número máximo de dias
     */
    public static int getMaxDay(int number) {
        return lookup.get(number);
    }
}

/** Classe que manipulara as datas
 * @author Diego Fernandes
 * @version 1.05
 */
public class Data {
    private Integer day;
    private Integer month;
    private Integer year;

    /**
     * Obtem o número do dia
     * @return Integer
     */
    public Integer getDay() {
        return day;
    }

    /**
     * Define o número do dia
     * @param day int
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * Obtem o número do mês
     * @return int
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * Define o número do mês
     * @param month Integer
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * Obtem o número do ano
     * @return Integer
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Define o número do ano
     * @param year Integer
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /** Método que valida se a data esta com os valores corretos
     * @param day Dia
     * @param month Mês
     * @param year Ano
     * @return novo objeto Data
     */
    public Data validate(Integer day, Integer month, Integer year) {
        if (isValid(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

        return this;
    }

    /**
     * Obtem o dia anterior
     * @param day
     * @param month
     * @param year
     * @return String com a data no formato BR
     */
    public String lastDay(Integer day, Integer month, Integer year) {
        int _day = day - 1;
        int _month = month;
        int _year = year;

        if (!isValid(day, month, year)) {
            System.out.println("Valores para dia, mês ou ano são inválidos.");
            return "Value Invalid";
        }

        // Caso o dia passado seja igual a 1 é preciso obter
        // o último dia do mês anterior, ou até mesmo do ano anterior
        if(day == 1) {
            _month = month - 1;

            if(_month < 1) {
                _month = Month.DECEMBER.getNumber();
                _year = year - 1;
            }

            _day = Month.getMaxDay(_month);

            // O mês de Fevereiro é especial pois ele é o
            // único que tem a possibilidade de ter um valor
            // diferente para o seu útimo dia
            if(_month == Month.FEBRUARY.getNumber()) {
                if(!isLeapYear(year)) {
                    _day = _day -1;
                }
            }
        }

        return formatDateBR(_day, _month, _year);
    }

    /**
     * Verifica se é possivel gerar uma data
     * @param day
     * @param month
     * @param year
     * @return boolean
     */
    private boolean isValid(Integer day, Integer month, Integer year) {
        if ((month < 1 || month > 12) || (day < 1 || day > 31) || (year <= 0 || year > 2999)) {
            return false;
        }

        boolean isLeap = isLeapYear(year);
        int maxDayOfTheCurrentMonth = Month.getMaxDay(month);

        if (day > maxDayOfTheCurrentMonth) return false;

        if (month == Month.FEBRUARY.getNumber()) {
            if(isLeap) {
                return (day <= maxDayOfTheCurrentMonth);
            } else {
                return (day <= maxDayOfTheCurrentMonth - 1);
            }
        }

        return true;
    }

    /**
     * Verifica se é um ano bissexto
     * @param year
     * @return boolean
     */
    private boolean isLeapYear (Integer year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    /**
     * Formata a data para o padrão BR (DD/MM/YYYY)
     * @param day
     * @param month
     * @param year
     * @return string com a data no formato BR
     */
    private String formatDateBR(int day, int month, int year) {
        return String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + String.format("%04d", year);
    }
}
