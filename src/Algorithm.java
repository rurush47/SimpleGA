import java.util.Random;

/**
 * Created by Rurarz on 25.11.2016.
 */
public class Algorithm {

    static byte[] solution;
    static double crossoverRate = 0.5;
    static double mutationRate = 0.03;
    static int tournamentSelectionSize = 5;


    static void setSolution(String newSolution)
    {

        solution = new byte[newSolution.length()];

        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }

    public static void calculateFitness(Individual ind)
    {
        byte[] genes = ind.getGenes();
        ind.fitness = 0;

        for (int i = 0; i < solution.length; i++)
        {
            if (solution[i] == genes[i])
            {
                ind.fitness++;
            }
        }
    }

    public static Individual crossoverIndividuals(Individual ind1, Individual ind2)
    {
        Individual newBorn = new Individual();

        for(int i=0; i < Individual.defaultGeneSize; i++)
        {
            if(Math.random() <= crossoverRate)
            {
                newBorn.setGeneAt(i, ind1.getGeneAt(i));
            }
            else
            {
                newBorn.setGeneAt(i, ind2.getGeneAt(i));
            }
        }

        return newBorn;
    }

    private static void mutate(Individual individual)
    {
        for (int i=0; i < Individual.defaultGeneSize; i++)
        {
            if(Math.random() <= mutationRate)
            {
                byte gene = (byte) Math.round(Math.random());
                individual.setGeneAt(i, gene);
            }
        }
    }

    public static Population evolve(Population population)
    {
        int populationSize = population.getSize();
        Population newPopulation = new Population(populationSize, false);

        for (int i = 0; i < populationSize; i++)
        {
            Individual ind1 = tournamentSelection(population);
            Individual ind2 = tournamentSelection(population);
            Individual newIndividual = crossoverIndividuals(ind1, ind2);

            newPopulation.addIndividual(newIndividual);
        }

        //mutate population
        for (int i = 0; i < populationSize; i++)
        {
            Individual ind = newPopulation.getIndividualAt(i);
            mutate(ind);
        }

        return newPopulation;
    }

    public static Individual tournamentSelection(Population population)
    {
        Population tournament = new Population(tournamentSelectionSize, false);

        for (int i = 0; i < tournamentSelectionSize; i++) {
            int randomId = (int) (Math.random() * population.getSize());
            tournament.addIndividual(population.getIndividualAt(randomId));
        }

        return tournament.getFittest();
    }

}
