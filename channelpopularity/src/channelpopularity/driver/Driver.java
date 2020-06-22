package channelpopularity.driver;

import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;

import java.util.Arrays;

import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.processor.InputDataProcessor;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;

/**
 * @author John Doe
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) throws Exception {

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
		

		FileProcessor fp = new FileProcessor(args[0]);

		SimpleStateFactoryI stateFacInf = new SimpleStateFactory();

		Results result = new Results();

		ContextI channelCntxt = new ChannelContext(stateFacInf, Arrays.asList(StateName.values()), result);

		InputDataProcessor iDp = new InputDataProcessor(fp, channelCntxt);

		iDp.process();

		result.writeToStdout();

		result.writeToFile(args[1]);

	}
}
