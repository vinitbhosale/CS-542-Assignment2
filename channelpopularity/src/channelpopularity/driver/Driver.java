package channelpopularity.driver;

import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;
import channelpopularity.userException.VideoAlreadyPresent;
import channelpopularity.userException.VideoDoesNotExist;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Arrays;

import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.processor.InputDataProcessor;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.userException.AdLengthException;
import channelpopularity.userException.EmptyInputFileException;
import channelpopularity.userException.InvalidInputException;
import channelpopularity.userException.MissingInputFile;
import channelpopularity.userException.NegativeViewException;
import channelpopularity.userException.NoVideoForAdException;

/**
 * Driver class start point.
 * @author Vinit Surendra Bhosale
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) {
		try {
			/*
			 * As the build.xml specifies the arguments as input,output or metrics, in case
			 * the argument value is not given java takes the default value specified in
			 * build.xml. To avoid that, below condition is used
			 */
			if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
				System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
						REQUIRED_NUMBER_OF_CMDLINE_ARGS);
				System.exit(0);
			}
			if (args[0].isEmpty()) {
				throw new MissingInputFile("Missing Input file parameter!");

			}

			// FileProcessor object
			FileProcessor fp = new FileProcessor(args[0]);
			// SimpleStateFactoryI object
			SimpleStateFactoryI stateFacInf = new SimpleStateFactory();
			// Results object
			Results result = new Results();

			// ContextI object with SimpleStateFactoryI object, List of Statenames and Results object
			// as parameter.
			ContextI channelCntxt = new ChannelContext(stateFacInf, Arrays.asList(StateName.values()), result);
			// InputDataProcessor object with FileProcessor object and ContextI object as parameter.
			InputDataProcessor iDp = new InputDataProcessor(fp, channelCntxt);

			// call of the process method in the InputDataprocessor.
			iDp.process();
			
			/**
			 * Displaying the final result store in Results on
			 * StdOut and in output file.
			 */
			result.writeToStdout();
			result.writeToFile(args[1]);

		} catch (InvalidPathException | IOException | EmptyInputFileException | MissingInputFile | VideoAlreadyPresent
				| VideoDoesNotExist | NegativeViewException | NoVideoForAdException | AdLengthException
				| InvalidInputException e) {
			System.err.println(e.getMessage());
		}catch(NumberFormatException e){
			System.err.println("Video properties values need to be Integer!");
		}

	}
}
