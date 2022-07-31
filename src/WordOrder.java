public class WordOrder 
{
    private String[] wordsList;    

    public WordOrder(String[] src, int start, int size)
    {
        wordsList = new String[size];  
        System.arraycopy(src, start, wordsList, 0, size); 
    }

    public String wordAt(int index)
    {
        return wordsList[index]; 
    }

    public int length()
    {
        return wordsList.length; 
    }

    public boolean equals(Object o)
    {
        boolean ret = true; 
        WordOrder other = (WordOrder) o; 
        if(length() != other.length())
            ret = false; 
        else 
        {
            for(int i = 0; i < length(); i++)
            {
                if(!wordAt(i).equals(other.wordAt(i)))
                {
                    ret = false;
                    break; 
                }
            }
        }
        return ret; 
    } 

    public WordOrder shiftAdd(String word)
    {
        WordOrder temp = this;   
        System.arraycopy(wordsList, 1, temp.wordsList, 0, length()-1); 
        temp.wordsList[length()-1] = word; 
        return temp; 
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder(); 
        for(int i = 0; i < length(); i++)
        {
            sb.append(wordAt(i) + " ");  
        }
        return sb.toString().trim(); 
    }
}
