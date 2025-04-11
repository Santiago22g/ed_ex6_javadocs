package ejemplo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
/**
 * Clase que se encarga de administrar los carriles bicis en la bahia de Cádiz
 * @author 
 * version 2.4.0
 * 
 */

public class CarrilBiciManager {

    private final Map<String, Double> tramos; // nombre del tramo -> longitud en km
    private final Map<String, String> estadoTramos; // nombre del tramo -> estado
    
    /**
     * Constructor de la clase CarrilBiciManager.
     * Inicializa los mapas que gestionan los tramos y los estados de los carriles bici.
     */
    public CarrilBiciManager() {
        this.tramos = new HashMap<>();
        this.estadoTramos = new HashMap<>();
    }
    
    /**
     * Metodo de la clase que sirve para añadir tramos
     * @param nombre
     * @param longitud
     * @throws IllegalArgumentException evita que el tramo este vacio 
     * @throws IllegalArgumentException evita que su longitud sea 0
     */
    public void añadirTramo(String nombre, double longitud) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del tramo no puede estar vacío");
        }
        if (longitud <= 0) {
            throw new IllegalArgumentException("La longitud debe ser mayor que cero");
        }
        tramos.put(nombre, longitud);
        estadoTramos.put(nombre, "En servicio");
    }

    /**
     * Metodo de la clase que se encarga de actualizar el estado
     * @param nombre
     * @param nuevoEstado
     * @throws NoSuchElementException indica si el tramo a actualizar no existe
     */
    public void actualizarEstado(String nombre, String nuevoEstado) {
        if (!tramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe: " + nombre);
        }
        estadoTramos.put(nombre, nuevoEstado);
    }
    
    /**
     * Metodo de clase que cambia el estado, usa el metodo actualizarEstado
     * @param nombre
     * @param estado
     * @deprecated este metodo se debe de dejar de utilizar y utilizar directamente actualizar estado
     */
    public void cambiarEstado(String nombre, String estado) {
        actualizarEstado(nombre, estado);
    }

    /**
     * Metodo que se encarga de consultar el estado
     * @param nombre
     * @return devuelve el tramo
     */
    public String consultarEstado(String nombre) {
        if (!estadoTramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe");
        }
        return estadoTramos.get(nombre);
    }
    

    public double longitudTotal() {
        return tramos.values().stream().mapToDouble(Double::doubleValue).sum();
    }


    public Map<String, Double> obtenerTramos() {
        return Collections.unmodifiableMap(tramos);
    }


    public String generarInforme() {
        StringBuilder sb = new StringBuilder("INFORME DE CARRILES BICI - Bahía de Cádiz\n");
        sb.append("===========================================\n");
        for (String nombre : tramos.keySet()) {
            sb.append("- ").append(nombre).append(" (")
              .append(tramos.get(nombre)).append(" km): ")
              .append(estadoTramos.get(nombre)).append("\n");
        }
        sb.append("Longitud total: ").append(longitudTotal()).append(" km\n");
        return sb.toString();
    }
}
