package modelo;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public static Data parseData(String data){
        String[] dataSplit = data.split("/");
        try{
            int dia = Integer.parseInt(dataSplit[0]);
            int mes = Integer.parseInt(dataSplit[1]);
            int ano = Integer.parseInt(dataSplit[2]);

            return new Data(dia,mes,ano);
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    public static boolean isFirstDateAfterSecondDate(Data first, Data second){
        if(first.getAno() > second.getAno()){
            return true;
        }
        if(first.getAno() < second.getAno()){
            return false;
        }
        //ano == ano
        if(first.getMes() > second.getMes()){
            return true;
        }
        if(first.getMes() < second.getMes()){
            return false;
        }
        //mes == mes
        return first.getDia() > second.getDia();
    }

    public int getDia(){
        return dia;
    }

    public int getMes(){
        return mes;
    }

    public int getAno(){
        return ano;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data data = (Data) o;
        return this.getDia() == data.getDia() && this.getMes() == data.getMes() && this.getAno() == data.getAno();
    }

}
