import java.util.List;

public class Solucion {

    List<Flor> floresRecorridas;

    int cantPolen;

    public int getCantPolenRecolectado() {
        return cantPolen;
    }

    public void copiar(Estado estado) {
        this.floresRecorridas.clear();
        this.floresRecorridas.addAll(estado.getFloresVisitadas());
        this.cantPolen = estado.getCantPolenRecolectado();
    }

}
