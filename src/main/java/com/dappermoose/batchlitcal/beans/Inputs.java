package com.dappermoose.batchlitcal.beans;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.MessageSource;

import lombok.extern.slf4j.Slf4j;

/**
 *  This is the class which processes the properties.
 *
 *  @author heitkergm@acm.org
 */
@Slf4j
public final class Inputs
{
    private Inputs ()
    {
        LOG.debug ("Creating the Inputs class");
    }

    /**
     * this is the routine which makes the calendar bean.
     *
     * @param props the list of program inputs from the command line.
     * @param locale the locale
     * @param messageSource the messages
     * 
     * @return the CalendarOptions bean
     * 
     */
    public static CalendarOptions processInputs (final Properties props,
                                                 final Locale locale,
                                              final MessageSource messageSource)
    {
        CalendarOptions opts = new CalendarOptions ();
        for (Enumeration<?> iter = props.propertyNames ();
             iter.hasMoreElements ();)
        {
            String name = (String) iter.nextElement ();
            String value = props.getProperty (name);
            LOG.debug ("Property: " + name + " = " + value);
            if (opts.getYear () == null &&
                "year".equals (name))
            {
                Integer yr = null;
                try
                {
                    yr = Integer.valueOf (value);
                }
                catch (NumberFormatException e)
                {
                    Object[] arr = {value};
                    String msg = messageSource.getMessage ("yearNotNumber",
                                                           arr,
                                                           locale);
                    LOG.error (msg + " " +
                               e.getClass ().getName () + " " +
                               e.getMessage ());
                    throw new RuntimeException (msg, e);      
                }

                opts.setYear (yr);
                LOG.debug ("year was an integer " + yr);
            }
        }
        LOG.debug ("calendar options bean = " + opts);
        return opts;
    }
}
