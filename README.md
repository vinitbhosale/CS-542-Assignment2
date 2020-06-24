# CSX42: Assignment 2
**Name:Vint Surendre Bhosale** 

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [channelpopularity/src](./channelpopularity/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile channelpopularity/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile channelpopularity/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile channelpopularity/src/build.xml run -Dinput="input.txt" -Doutput="output.txt"
```
Note: Arguments accept the absolute path of the files.


## Description:
1. Assumption:
 - Format of input file should be as given format:
   Adding a video to the channel. Input format: ADD_VIDEO::video name.

   Removing a video from the channel. Input format: REMOVE_VIDEO::video name.

   Views, Likes and Dislikes. Input format: METRICS__video name::[VIEWS=#views,LIKES=#likes,DISLIKES=#dislikes].
   Views, Likes and Dislikes MUST be integers.
   There are no spaces before or after the comma character.
   Unlike likes and dislikes, the delta in the number of views cannot be negative.

   Advertisement requests. Input format: AD_REQUEST__video name::LEN=length.
 - Order of input arguments to run command is input.txt file, output.txt file path.

2. Data Structures:
 - HashMap are used to store the key value pair of video properties and Ad length.
 - String are used to append the results that need to display.

3. External Materials:
 - Used regex for the format of metrics input format.

4. Code Working:
 - Input file is read line by line in the inputDataProcessor and values are split based on the special charachter
   and are stored in HashMap.
 - Based on the operation calls goes to ChannelContext and accordingly calls the operation by the current state
   of the machine in the AbstractState class and calculates the popularity score of video also average popularity
   score of channel and accordingly changes the current state of the machine.
 - Parallel it stores the result in the Results file.
 - After everything is processed it writes the result to Stdout and the output.txt file.


## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 06/24/2020


