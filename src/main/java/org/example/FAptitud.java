package org.example;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

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

        // La aptitud es inversamente proporcional al número de conflictos
        return 1.0 / (conflictos + 1);
    }
}
