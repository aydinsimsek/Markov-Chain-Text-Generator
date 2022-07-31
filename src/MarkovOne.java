import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel
{
	private HashMap<String, ArrayList<String>> map;

	public MarkovOne()  
	{
		randomNum = new Random();
		map = new HashMap<String, ArrayList<String>>(); 
	}
	
	public void setTraining(String s)
	{
		trainText = s.split("\\s+");
	}

	private int indexOf(String[] words, String target, int start)
    {
        int ret = -1;
        for(int i = start; i < words.length; i++)
        {
            if(words[i].equals(target))      
            {
                ret = i; 
				break; 
            }
        }
        return ret; 
    }

	private ArrayList<String> getFollows(String key)
	{
		return map.get(key);   
	}

	private void buildMap(String key)
    {
		ArrayList<String> follows = new ArrayList<String>(); 
		int start = 0; 
        while(true)
        {
			int idx = indexOf(trainText, key, start);
            // when the key is found in the training text and it has a following word
            if(idx != -1 && idx < trainText.length-1)    
			{
                // record the next word follows the key 
                follows.add(trainText[idx+1]);   
				start = idx+1;    
			}
			else
				break;   
        }
        map.put(key, follows);
    }

	public String getRandomText(int numWords)
	{
		String ret = ""; 
		if(numWords > trainText.length)  
		{
			System.err.println("ERROR: text size must be at most the training text size!\nTraning text size = " + trainText.length + " words");
			System.exit(1); 
		}

		StringBuilder sb = new StringBuilder();
		// get a valid random index (ensure there is a follow word)
		int index = randomNum.nextInt(trainText.length-1);
		String key = trainText[index]; 
		sb.append(key + " ");
		for(int i = 0; i < numWords-1; i++) 
		{
			if(!map.containsKey(key))
			{
				buildMap(key);
			}
			ArrayList<String> follows = getFollows(key);
			if(!follows.isEmpty())
			{
				index = randomNum.nextInt(follows.size()); 
				String next = follows.get(index); 
				sb.append(next + " ");  
				key = next; 
			}
		}
		ret = sb.toString().trim();
		return ret; 
	}

	public String toString()
    {
        return "MarkovModel of order 1...";  
    }
}

