package com.dappermoose.batchlitcal.calendar;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * This is the class which makes the calendar.
 *
 */
@Log4j2
@Component
public class MakeCalendar
{
    @Inject
    private MessageSource messageSource;

    @Inject
    private ApplicationContext context;

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
        for (int i = 0; i < args.length; i++)
        {
            LOG.debug (args[i]);
            if (fileName == null)
            {
                fileName = args[i];
            }
        }
        LOG.debug ("input fileName is " + fileName);

        if (fileName != null)
        {
            boolean hasError = false;
            Properties props = new Properties ();
            try (FileInputStream fis = new FileInputStream (fileName))
            {
                props.load (fis);
            }
            catch (IOException e)
            {
                hasError = true;
                LOG.error ("Input file error loading " +
                           e.getClass ().getName () + " " +
                           e.getMessage ());
            }
            if (!hasError)
            {
                ; // here we continue...
            }
        }
        else
        {
            LOG.error ("no file name specified.  Shutting down");
        }
    }
}
