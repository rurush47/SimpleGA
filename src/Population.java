import com.sun.org.apache.bcel.internal.generic.POP;

import java.util.ArrayList;

/**
 * Created by Rurarz on 25.11.2016.
 */
public class Population {

    ArrayList<Individual> individuals = new ArrayList<>();
    private int populationSize;

    public Population(int size, boolean init)
    {
        populationSize = size;

        if (init)
        {
            Init();
        }
    }

    public void Init()
    {
        for (int i = 0; i < populationSize; i++)
        {
            Individual ind = new Individual();
            ind.Init();
            individuals.add(ind);
        }
    }

    public void calculateFitness()
    {
        for(int i = 0; i < individuals.size(); i++)
        {
            individuals.get(i).calculateFitness();
        }
    }

    public int getSize()
    {
        return individuals.size();
    }

    public void addIndividual(Individual ind)
    {
        individuals.add(ind);
    }

    public Individual getFittest()
    {
        if(!individuals.isEmpty())
        {
            Individual fittest = individuals.get(0);
            fittest.calculateFitness();

            for (int i = 0; i < individuals.size(); i++)
            {
                Individual ind = individuals.get(i);
                if (ind.fitness >= fittest.fitness)
                {
                    fittest = ind;
                }
            }

            return fittest;
        }
        else
        {
            return null;
        }
    }

    public Individual getIndividualAt(int index)
    {
        return individuals.get(index);
    }

}
