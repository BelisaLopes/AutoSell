package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Data {
    private Calendar calendar;

    public Data(int dia, int mes, int ano){
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.YEAR, ano);
    }

    public static Data parseData(String data){

        var formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        try{
            var d = formato.parse(data);
            var cal = new GregorianCalendar();
            cal.setTime(d);

            int dia = cal.get(Calendar.DAY_OF_MONTH);
            int mes = cal.get(Calendar.MONTH) + 1;
            int ano = cal.get(Calendar.YEAR);

            return new Data(dia,mes,ano);
        }catch (ParseException e){
            return null;
        }
    }
    public int getDia(){
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getMes(){
        return calendar.get(Calendar.MONTH);
    }

    public int getAno(){
        return calendar.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);
        return dia + "/" + mes + "/" + ano;
    }
}
