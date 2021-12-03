package com.dappermoose.batchlitcal.calendar;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Properties;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 *  This is the class which makes the calendar.
 *
 *  @author heitkergm@acm.org
 */
@Log4j2
@Component
public class MakeCalendar
{
    @Inject
    private MessageSource messageSource;

    @Inject
    private Locale locale;

    @Inject
    private Inputs inputs;

    /**
     * Make an instance of the calendar maker class.
     */
    public MakeCalendar ()
    {
        LOG.debug ("Creating the MakeCalendar class");
    }

    /**
     * this is the routine which makes the calendar.
     *
     * @param args the list of program inputs from the command line.
     */
    public void makeCal (final String [] args)
    {
        String fileName = null;
        LOG.debug ("there were " + args.length + " command line arguments");
        for (String arg : args)
        {
            LOG.debug (arg);
            if (fileName == null)
            {
                fileName = arg;
            }
        }
        LOG.debug ("input fileName is " + fileName);

        boolean hasError = false;
        Properties props = new Properties ();
        if (fileName != null)
        {
            try (InputStream is =
                 Files.newInputStream (FileSystems.getDefault ()
                                       .getPath (fileName)))
            {
                props.load (is);
            }
            catch (IOException e)
            {
                hasError = true;
                Object [] arr = new Object[] {fileName};
                String msg = messageSource.getMessage ("noFileFound", 
                                                       arr,
                                                       locale);
                LOG.error (msg + " " +
                           e.getClass ().getName () + " " +
                           e.getMessage ());
            }
        }
        else
        {
            String msg = messageSource.getMessage ("noFileGiven", 
                                                       null, locale);
            LOG.error (msg);
            hasError = true;
        }
        if (hasError)
        {
            System.exit (1);
        }

        // here we continue...
        inputs.processInputs (props);
    }
}
