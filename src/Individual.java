/**
 * Created by Rurarz on 25.11.2016.
 */
public class Individual {

    static int defaultGeneSize = 64;
    private byte[] genes;
    public int fitness = 0;

    public Individual()
    {
        genes = new byte[64];
    }

    public void Init()
    {
        for (int i = 0; i < defaultGeneSize; i++)
        {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }

    public byte[] getGenes()
    {
        return genes;
    }

    public void calculateFitness()
    {
        Algorithm.calculateFitness(this);
    }

    public void setGeneAt(int index, byte value)
    {
        genes[index] = value;
    }

    public byte getGeneAt(int index)
    {
        return genes[index];
    }

    public void print()
    {
        for (int i = 0; i < genes.length; i++)
        {
            System.out.print(genes[i]);
        }
    }
}
