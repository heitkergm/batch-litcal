package com.dappermoose.batchlitcal.calendar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.dappermoose.batchlitcal.beans.CalendarOptions;

import lombok.extern.slf4j.Slf4j;

/**
 * contains utilities for printing.
 * 
 * @author heitkergm@acm.org
 */
@Slf4j
@Component
public class OutputUtils
{
    @Inject
    private BufferedWriter writer;

    @Inject
    private Locale locale;

    @Inject
    private MessageSource messageSource;

    @Inject
    private CalendarOptions calendarOptions;

    /**
     * make an instance of the OutputUtils class.
     */
    public OutputUtils ()
    {
        LOG.debug ("Creating the OutputUtils class");
    }

    /**
     * create the HTML header.
     */
    public void writeHeader ()
    {
        try
        {
            writer.write ("<!DOCTYPE html>\n<html lang=\"en\">\n");
            String year = calendarOptions.getYear ().toString ();
            String message = messageSource.getMessage ("title", new Object [] {year}, locale);
            writer.write ("<head>\n<title>" + message + "</title>\n");
            writer.write ("<style>\n");
            writer.write ("body {font-family: 'DejaVu Sans', sans-serif;}\n");
            writer.write (".center {text-align: center;}\n");
            writer.write (".bold {font-weight: bold;}\n");
            writer.write ("</style>\n");
            writer.write ("</head>\n<body>\n<h1 class='center'>" +
                          message + "</h1>\n");
            writer.flush ();
        }
        catch (IOException e)
        {
            LOG.error (e.getClass ().getName () + " " + e.getMessage ());
            throw new RuntimeException (e);
        }
    }

    /**
     * create the HTML footer.
     */
    public void writeFooter ()
    {
        try
        {
            writer.write ("</body>\n</html>\n");
            writer.flush ();
        }
        catch (IOException e)
        {
            LOG.error (e.getClass ().getName () + " " + e.getMessage ());
            throw new RuntimeException (e);
        }
    }
}
