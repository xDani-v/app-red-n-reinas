package org.example;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import java.util.HashSet;
import java.util.Set;

public class FAptitud extends FitnessFunction {

    public double evaluate(IChromosome cromosoma) {
        int longitud = cromosoma.getGenes().length;
        int[] posiciones = new int[longitud];

        // Obtener las posiciones de las reinas desde el cromosoma
        for (int i = 0; i < longitud; i++) {
            posiciones[i] = (Integer) cromosoma.getGene(i).getAllele();
        }

        // Calcular la aptitud evaluando las restricciones del problema
        double aptitud = calcularAptitud(posiciones);

        return aptitud;
    }

    private double calcularAptitud(int[] posiciones) {
        int longitud = posiciones.length;
        int conflictos = 0;

        for (int i = 0; i < longitud - 1; i++) {
            for (int j = i + 1; j < longitud; j++) {
                // Verificar conflictos en la misma fila o diagonales
                if (posiciones[i] == posiciones[j] || Math.abs(posiciones[i] - posiciones[j]) == Math.abs(i - j)) {
                    conflictos++;
                }
            }
        }

        // Verificar restricción vertical
        Set<Integer> posicionesUnicas = new HashSet<>();
        for (int posicion : posiciones) {
            posicionesUnicas.add(posicion);
        }

        // La aptitud es inversamente proporcional al número de conflictos
        return posicionesUnicas.size() == longitud ? 1.0 / (conflictos + 1) : 0.0;
    }
}
