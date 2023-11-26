package org.example;

import org.jgap.Chromosome;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;


public class Main {
    public static void main(String[] args) throws Exception  {

        // TODO Auto-generated method stub
        DefaultConfiguration VConfig = new DefaultConfiguration();
        VConfig.setPreservFittestIndividual(true);

        FAptitud vfunapto = new FAptitud();
        VConfig.setFitnessFunction(vfunapto);


        // Definir genes y cromosoma
        int numReinas = 3; // Por ejemplo, para el problema de las 8 reinas
        Gene[] vgene = new Gene[numReinas];

        for (int i = 0; i < numReinas; i++) {
            vgene[i] = new IntegerGene(VConfig, 1, numReinas); // Rango de 1 a numReinas
        }

        //definir el cromosoma
        IChromosome vcromosoma = new Chromosome(VConfig,vgene);
        VConfig.setSampleChromosome(vcromosoma);

        // Configurar la población inicial
        VConfig.setPopulationSize(200);

        //Llenado aleatorio de los 200 individuos
        Genotype poblacion = Genotype.randomInitialGenotype(VConfig);

        for(int genera=1; genera<=2200; genera++)
            poblacion.evolve();

        IChromosome Indmasapto = poblacion.getFittestChromosome();

        imprimirCromosoma(Indmasapto);

    }

    private static void imprimirCromosoma(IChromosome cromosoma) {
        int longitud = cromosoma.getGenes().length;
        int[] posiciones = new int[longitud];

        // Obtener las posiciones de las reinas desde el cromosoma
        for (int i = 0; i < longitud; i++) {
            posiciones[i] = (Integer) cromosoma.getGene(i).getAllele();
        }

        // Imprimir las posiciones de las reinas en el tablero
        System.out.println("Posiciones de las reinas:");
        for (int i = 0; i < longitud; i++) {
            System.out.println("Columna " + (i + 1) + ": Fila " + posiciones[i]);
        }
    }
}
