## Background Information and Description 
A Markov Chain is a stochastic model that the next state in the chain depends on the probability distribution of the previous state.   
Based on that idea, this text generator chooses the next word from a list of the succeeding words of the already chosen ones that are determined from a training text.  
Since this model lacks the ability to produce content that depends on the context, generated text may not be semantically correct but it will be syntactically correct for sure.

## Usage 
* Clone this repository
  
`$ git clone https://github.com/aydinsimsek/Markov-Chain-Text-Generator.git`  

* Open the command prompt in the folder and run these commands:        
```
> cd src  
> javac *.java
> java ./MarkovRunner.java <KEY_LENGTH> <TEXT_SIZE>
```
Note that:  
<KEY_LENGTH> is the number of words that are used to choose the next word.     
<TEXT_SIZE> is the number of words in the generated text.  

* Select the training text from the appeared dialog box
     
![dialog-box](https://user-images.githubusercontent.com/43919074/182049438-9b4c7b47-17ac-46ce-8f31-af0b811d394f.png)

## Sample Output
![generated-text](https://user-images.githubusercontent.com/43919074/182049525-1da186ea-b453-423e-8542-01a3cf6368f9.png)
