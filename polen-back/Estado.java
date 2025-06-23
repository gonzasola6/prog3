import java.util.ArrayList;
import java.util.List;

public class Estado {

    List<Flor> floresVisitadas; // para no tener ciclo

    int cantPolenRecolectado;

    Coordenada posicionActualAbeja;

    public Estado(Coordenada posicionActualAbeja) { // si creo un estado solo apartir de la pos actual de la abeja, el
                                                    // polen es 0 y la lista de lofres visitadas empieza vacia
        this.floresVisitadas = new ArrayList<>();
        this.cantPolenRecolectado = 0;
        this.posicionActualAbeja = posicionActualAbeja;
    }

    public Estado(List<Flor> floresVisitadas, int cantPolenRecolectado, Coordenada posicionActualAbeja) {
        this(posicionActualAbeja);
        this.cantPolenRecolectado = cantPolenRecolectado;
        this.floresVisitadas.addAll(floresVisitadas);

    }

    public int cantidadFloresVisitadas() {
        return this.floresVisitadas.size(); // no hace falta implementar pq es una pavada
    }

    public Coordenada getPosicionActualAbeja() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosicionActualAbeja'");
    }

    public int getCantPolenRecolectado() {
        return cantPolenRecolectado;
    }

    public List<Flor> getFloresVisitadas() {
        return new ArrayList<Flor>(floresVisitadas);
    }

    public void moverAbeja(Flor siguienteFlor) {
        posicionActualAbeja = siguienteFlor.getPosicion();
    }

    public void acumularPolen(int cantPolen) {
        this.cantPolenRecolectado += cantPolen;
    }

    public void visitarFlor(Flor siguienteFlor) {
        this.floresVisitadas.add(siguienteFlor);
    }

    public void restarPolen(int cantPolen) {
        this.cantPolenRecolectado -= cantPolen;
    }

    public void desvisitarUltimaFlor() {
        this.floresVisitadas.remove(this.floresVisitadas.size() - 1);
    }

}
