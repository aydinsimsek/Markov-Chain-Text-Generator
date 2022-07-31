import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel
{
    private int keyLen; 
    private HashMap<String, ArrayList<String>> map;
	
	public MarkovModel(int length)     
	{
		randomNum = new Random();
        keyLen = length; 
        map = new HashMap<String, ArrayList<String>>(); 
	}

	public void setTraining(String s)
	{
		trainText = s.split("\\s+");
	}

    private int indexOf(String[] words, WordOrder target, int start)
    {
        int ret = -1;
        for(int i = start; i < words.length; i++)
        {
            if(words[i].equals(target.wordAt(0)) && i + target.length() <= words.length)       
            {
                WordOrder wo = new WordOrder(words, i, target.length());
                if(target.equals(wo))
                {
                    ret = i; 
                    break;
                }
            }
        }
        return ret; 
    }

    private ArrayList<String> getFollows(WordOrder key)
    {
        return map.get(key.toString());   
    }

    private void buildMap(WordOrder key)
    {
        ArrayList<String> follows = new ArrayList<String>(); 
		int start = 0; 
        while(true)
        {
			int idx = indexOf(trainText, key, start);
            // when the key is found in the training text and it has a following word
            if(idx != -1 && idx < trainText.length-keyLen)    
			{
                // record the next word follows the key 
                follows.add(trainText[idx+keyLen]);   
				start = idx+keyLen;     
			}
			else
				break;   
        }  
        map.put(key.toString(), follows);
    }

	public String getRandomText(int numWords)
	{
		String ret = ""; 

        if(keyLen >= trainText.length)  
		{
			System.err.println("ERROR: key length must be less than the training text size!\nTraning text size = " + trainText.length + " words");
			System.exit(1); 
		}

        if(numWords > trainText.length)  
		{
			System.err.println("ERROR: text size must be at most the training text size!\nTraning text size = " + trainText.length + " words");
			System.exit(1); 
		}

        // get a valid random index (ensure there is a follow word)  
        int index = randomNum.nextInt(trainText.length-keyLen);  
        String[] from = new String[keyLen]; 
        for(int i = 0; i < keyLen; i++)
            from[i] = trainText[index++]; 
        WordOrder key =  new WordOrder(from, 0, keyLen); 
        ret += key.toString();  
        for(int i = 0; i < numWords-keyLen; i++) 
        {
            if(!map.containsKey(key.toString()))
            {
                buildMap(key);
            }
            ArrayList<String> follows = getFollows(key);
            if(!follows.isEmpty())
            { 
                index = randomNum.nextInt(follows.size()); 
                String next = follows.get(index);
                ret += " " + next; 
                // update the key 
                key = key.shiftAdd(next);  
            }
        }
		return ret; 
	}

    public String toString()
    {
        return "MarkovModel of order " + keyLen + "..."; 
    }
}
