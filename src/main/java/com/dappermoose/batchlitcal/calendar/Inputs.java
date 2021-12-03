package com.dappermoose.batchlitcal.calendar;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;

import javax.inject.Inject;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 *  This is the class which processes the properties.
 *
 *  @author heitkergm@acm.org
 */
@Log4j2
@Component
public class Inputs
{
    @Inject
    private MessageSource messageSource;

    @Inject
    private Locale locale;

    @Inject
    private ApplicationContext context;

    /**
     * Make an instance of the calendar maker class.
     */
    public Inputs ()
    {
        LOG.debug ("Creating the Inputs class");
    }

    /**
     * this is the routine which makes the calendar.
     *
     * @param props the list of program inputs from the command line.
     */
    public void processInputs (final Properties props)
    {
        CalendarOptions opts = new CalendarOptions ();
        boolean hasErrors = false;
        for (Enumeration<?> iter = props.propertyNames ();
             iter.hasMoreElements ();)
        {
            String name = (String) iter.nextElement ();
            String value = props.getProperty (name);
            LOG.debug ("Property: " + name + " = " + value);
            if (opts.getYear () == null &&
                name.equals ("year"))
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
                    hasErrors = true;
                }
                if (!hasErrors)
                { 
                    opts.setYear (yr);
                }
            }
        }

        if (hasErrors)
        {
            System.exit (1);
        }

        ConfigurableListableBeanFactory beanFactory =
            ((ConfigurableApplicationContext) context).getBeanFactory ();
        beanFactory.registerSingleton (opts.getClass ()
                                       .getCanonicalName (), opts);
    }
}
