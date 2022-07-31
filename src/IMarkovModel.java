public interface IMarkovModel
{
    public void setTraining(String s); 
    public String getRandomText(int numWords);
}