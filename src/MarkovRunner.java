import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MarkovRunner
{ 
	private static final int LINE_SIZE = 60; 
	private static int keyLen;
	private static int textSize; 

    private void runModel(IMarkovModel markov, String text) 
	{
        markov.setTraining(text);
        System.out.println("\nRunning with " + markov);
		String st= markov.getRandomText(textSize); 
		printOut(st);
    }
    
    private void runMarkov() throws IOException
	{
		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files", "txt");
		jfc.setFileFilter(filter); 
		jfc.setDialogTitle("Training Text"); 
		jfc.showDialog(null, "Select");
		File fSelected = jfc.getSelectedFile();
		String fPath = fSelected.getPath();
		String st = new String(Files.readAllBytes(Paths.get(fPath)), StandardCharsets.UTF_8);
		st = st.replace('\n', ' ');

		if(keyLen == 1)
		{
        	MarkovOne mOne = new MarkovOne();
        	runModel(mOne, st);
		}
        
		else
		{
        	MarkovModel mm = new MarkovModel(keyLen);
        	runModel(mm, st);
		}
    }

	private void printOut(String s)
	{
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("\n\t\t\tGENERATED TEXT\n--------------------------------------------------------------------");
		for(int i = 0; i < words.length; i++)
		{
			System.out.print(words[i]+ " ");
			psize += words[i].length() + 1;
			if (psize > LINE_SIZE) 
			{
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n--------------------------------------------------------------------");
	}	

	public static void main(String[] args) 
	{  
		if(args.length < 2) 
		{
			System.err.println("Usage: java ./MarkovRunner.java <KEY_LENGTH> <TEXT_SIZE>"); 
       	    System.exit(1);
		}
		keyLen = Integer.parseInt(args[0]);  
		textSize = Integer.parseInt(args[1]);  
		if(keyLen <= 0 || textSize <= 0)
		{
			System.err.println("ERROR: key length and text size must be greater than 0");
			System.exit(1); 
		}
		MarkovRunner mr = new MarkovRunner();
		try
		{
			mr.runMarkov(); 

		} 
		catch(IOException ex)
		{
			System.out.println(ex.toString());
		}
	}
}
