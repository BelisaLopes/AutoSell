package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Data {
    private Calendar calendar;

    public Data(int dia, int mes, int ano){
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.YEAR, ano);
    }

    public static Data parseData(String data){
//        if(data.isEmpty()){
//            return null;
//        }

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

    public static boolean isFirstDateAfterSecondDate(Data first, Data second){
        if(first.getAno() > second.getAno()){
            return true;
        }

        if(first.getMes() > second.getMes()){
            return true;
        }

        return first.getDia() > second.getDia();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data data = (Data) o;
        return this.getDia() == data.getDia() && this.getMes() == data.getMes() && this.getAno() == data.getAno();
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendar);
    }
}
