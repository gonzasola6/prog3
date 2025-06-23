import java.util.Iterator;

public class PolenBacktracking {

    Jardin jardin;

    int distanciaMaxima;

    int cantFloresAVisitar;
    Solucion mejorSolucion;

    public PolenBacktracking(Jardin jardin, int distanciaMaximaint, int cantFloresAVisitar) {
        this.jardin = jardin;
        this.distanciaMaxima = distanciaMaxima;
        this.cantFloresAVisitar = cantFloresAVisitar;
        mejorSolucion = null;

    }

    // alternativa

    // public Solucion resolver(Coordenada posInicial) {
    // Estado inicial = new Estado(posInicial);
    // return resolver(inicial);

    // }

    // private Solucion resolver(Estado estado) {
    // return null;
    // }

    public Solucion resolver(Coordenada posInicial) {
        Estado inicial = new Estado(posInicial);
        resolver(inicial);
        return this.mejorSolucion; // resolver(estado e) es void
    }

    private void resolver(Estado estado) { // explora todo el arbol
        // llegue a un estado final?
        if (estado.cantidadFloresVisitadas() == cantFloresAVisitar
                || jardin.floresCercanas(estado.getPosicionActualAbeja(), distanciaMaxima).isEmpty()) {
            // es solucion? => todo estado final es solucion en este ejercicio
            if (mejorSolucion == null || estado.getCantPolenRecolectado() > mejorSolucion.getCantPolenRecolectado())
                mejorSolucion.copiar(estado); // copia la info necesaria del estado actual a ala mejor solucion
        } else {
            // generar hijos para el sig nivel de exploracion
            // obtener las flores al alcance de la abeja y recorreralas una por una
            Iterator<Flor> floresCercanas = jardin.floresCercanas(estado.getPosicionActualAbeja(), distanciaMaxima)
                    .iterator();
            while (floresCercanas.hasNext()) {
                Flor siguienteFlor = floresCercanas.next(); // ahi genere los hijos
                estado.moverAbeja(siguienteFlor); // muevo la abeja a la siguiente flor
                estado.acumularPolen(siguienteFlor.getCantPolen()); // esta linea y la anterior generan el sigestado
                estado.visitarFlor(siguienteFlor); // agrega siguiente flor a la lista de flores visitadas
                // como modifique el estado que recibo, le paso el estado modificado
                resolver(estado);
                // como esta modificado, cuando salgo lo tengoq que deshacer

                estado.retrocederAbeja(siguienteFlor); // muevo la abeja a la actual flor (que perdi)
                estado.restarPolen(siguienteFlor.getCantPolen());
                estado.desvisitarUltimaFlor(); // quitar le flor a la lista de flores visitadas

            }
        }

    }

}
