/**
 * Created by Rurarz on 24.11.2016.
 */
public class Main {

    public static void main(String[] args)
    {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Let's start!");

        Algorithm.setSolution("1101000001000000000100000000100000000010000000100000000000001101");
        int generationCounter = 1;
        Population currentPopulation = new Population(50, true);

        while (currentPopulation.getFittest().fitness != Individual.defaultGeneSize)
        {
            System.out.println("Generation: " + generationCounter + " Greatest fittnes: " + currentPopulation.getFittest().fitness
            + " Average fitness: " + currentPopulation.getAverageFitness());
            currentPopulation = Algorithm.evolve(currentPopulation);
            generationCounter++;
        }

        System.out.println("Done! In generation:" + generationCounter + " best gene fitness: " + currentPopulation.getFittest().fitness);
        System.out.println("Genes:");
        currentPopulation.getFittest().print();
    }
}
