import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel 
{
    protected String[] trainText;
    protected Random randomNum;
    
    public AbstractMarkovModel() 
    {
        randomNum = new Random();
    }
    
    abstract public String getRandomText(int numChars);
}
