package com.dappermoose.batchlitcal.calendar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Locale;

import jakarta.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.lang.NonNull;
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
    @NonNull
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
        log.debug ("Creating the OutputUtils class");
    }

    /**
     * create the HTML header.
     */
    public void writeHeader ()
    {
        try
        {
            writer.write ("<!DOCTYPE html>\n<html lang=\"" + locale.getLanguage () + "\">\n");
            String year = calendarOptions.getYear ().toString ();
            String message = messageSource.getMessage ("title", new Object [] {year}, locale);
            writer.write ("<head>\n<title>" + message + "</title>\n");
            writer.write ("<style>\n");
            writer.write ("body {font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif;}\n");
            writer.write (".center {text-align: center;}\n");
            writer.write (".bold {font-weight: bold;}\n");
            writer.write ("</style>\n");
            writer.write ("</head>\n<body>\n<h1 class='center'>" +
                          message + "</h1>\n");
            writer.flush ();
        }
        catch (IOException e)
        {
            log.error (e.getClass ().getName () + " " + e.getMessage ());
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
            writer.write ("<p class='center'>original program &copy; dappermoose.com</p>\n");
            writer.write ("</body>\n</html>\n");
            writer.flush ();
        }
        catch (IOException e)
        {
            log.error (e.getClass ().getName () + " " + e.getMessage ());
            throw new RuntimeException (e);
        }
    }
}
