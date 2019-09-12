package io.camassia.mjml;

import io.camassia.mjml.model.request.RenderRequest;
import io.camassia.mjml.model.response.RenderResponse;
import org.apache.commons.cli.*;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class MJMLClientApplication {

    private static final String APPLICATION_ID = "applicationId";
    private static final String APPLICATION_KEY = "applicationKey";

    /*
    * Test Application for the MJML Client.
    *
    */
    public static void main(String[] args) throws IOException {
        Options options = new Options();

        Option applicationIDOption = new Option(APPLICATION_ID, "The Application ID");
        applicationIDOption.setRequired(true);
        applicationIDOption.setArgs(1);
        options.addOption(applicationIDOption);

        Option applicationKeyOption = new Option(APPLICATION_KEY, "The Application ID");
        applicationKeyOption.setRequired(true);
        applicationKeyOption.setArgs(1);
        options.addOption(applicationKeyOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);
            String applicationId = cmd.getOptionValue(APPLICATION_ID);
            String applicationKey = cmd.getOptionValue(APPLICATION_KEY);

            MJMLClient client = MJMLClient.newDefaultClient()
                    .withApplicationID(applicationId)
                    .withApplicationKey(applicationKey);

            RenderRequest renderRequest = new RenderRequest(IOUtils.toString(
                    MJMLClientApplication.class.getResourceAsStream("/ifusion/mjml/mjml.xml"),
                    Charset.forName("UTF-8"))
            );

            RenderResponse render = client.render(renderRequest);
            System.out.println("MJML Version: " + render.getMJMLVersion());
            System.out.println(render.getMJML());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("MJML", options);
        }
    }
}
