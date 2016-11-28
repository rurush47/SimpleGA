import com.sun.org.apache.bcel.internal.generic.POP;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.ArrayList;

/**
 * Created by Rurarz on 25.11.2016.
 */
public class Population {

    private ArrayList<Individual> individuals = new ArrayList<>();
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
        calculateFitness();

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

    public double getAverageFitness()
    {
        int sum = getTotalFitness();
        return sum/individuals.size();
    }

    public Individual getIndvividualFromRoulette()
    {
        int sum = getTotalFitness();
        int rouletteSpin = (int) (Math.random() * sum);
        int currentSum = 0;

        for (int i = 0; i < individuals.size(); i++)
        {
            Individual currentIndividual = individuals.get(i);
            currentSum += currentIndividual.fitness;

            if(currentSum >= rouletteSpin)
            {
                return currentIndividual;
            }
        }

        return null;
    }

    public int getTotalFitness()
    {
        int sum = 0;
        for (int i = 0; i < individuals.size(); i++)
        {
            sum += individuals.get(i).fitness;
        }

        return sum;
    }

}
