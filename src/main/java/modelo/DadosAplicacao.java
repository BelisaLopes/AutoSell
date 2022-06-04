package modelo;

import java.util.ArrayList;
import java.util.List;

public class DadosAplicacao {
    public static DadosAplicacao INSTANCE = new DadosAplicacao();

    private List<Filial> filiais;
    private Sede sede;

    public DadosAplicacao() {
        sede = new Sede(Local.LISBOA);
        filiais = new ArrayList<>(18);
        filiais.add(new Filial(Local.LISBOA));
        filiais.add(new Filial(Local.LEIRIA));
        filiais.add(new Filial(Local.PORTO));
        filiais.add(new Filial(Local.COIMBRA));
        filiais.add(new Filial(Local.SETUBAL));
        filiais.add(new Filial(Local.FARO));
        filiais.add(new Filial(Local.VIANA_DO_CASTELO));
        filiais.add(new Filial(Local.VISEU));
        filiais.add(new Filial(Local.VILA_REAL));
        filiais.add(new Filial(Local.AVEIRO));
        filiais.add(new Filial(Local.COIMBRA));
        filiais.add(new Filial(Local.CASTELO_BRANCO));
        filiais.add(new Filial(Local.BEJA));
        filiais.add(new Filial(Local.PORTALEGRE));
        filiais.add(new Filial(Local.EVORA));
        filiais.add(new Filial(Local.BRAGA));
        filiais.add(new Filial(Local.GUARDA));
        filiais.add(new Filial(Local.BRAGANCA));
    }

    public List<Filial> getFiliais() {
        return filiais;
    }

    public Filial getFilial(Local local) {
        for (Filial f : filiais) {
            if (f.getLocal().equals(local)) {
                return f;
            }
        }
        return null;
    }

    public Sede getSede() {
        return sede;
    }
}
