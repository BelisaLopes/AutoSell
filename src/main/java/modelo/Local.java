package modelo;

import java.util.List;
import java.util.Objects;

public abstract class Local {
    protected Distrito distrito;

    public Local(Distrito distrito) {
        this.distrito = distrito;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Local)) return false;
        Local local = (Local) o;
        return distrito == local.distrito;
    }
}
