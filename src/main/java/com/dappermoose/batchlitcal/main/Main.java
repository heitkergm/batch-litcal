package com.dappermoose.batchlitcal.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import com.dappermoose.batchlitcal.calendar.MakeCalendar;

import lombok.extern.slf4j.Slf4j;


/**
 * This is the actual main class.
 *
 * @author heitkergm@acm.org
 */
@Slf4j
public final class Main
{
    private Main ()
    {
    }

    /**
     * actual main procedure to start our app.
     * instantiates the program.
     *
     * @param args the command line arguments
     */
    public static void main (final String[] args)
    {
        LOG.debug ("starting main batch litcal program");

        // make the context
        SimpleCommandLinePropertySource ps;
        ps = new SimpleCommandLinePropertySource (args);
        String [] pnames = ps.getPropertyNames ();
        for (String pname : pnames)
        {
            LOG.debug ("property name: " + pname + ": " + ps.getProperty (pname));
        }
        LOG.debug ("nonOptionsArgs: " + ps.getProperty ("nonOptionArgs"));
        
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext ();
        context.getEnvironment ().getPropertySources ().addFirst (ps);
               
        // make sure our context shuts down when JVM wants to
        context.registerShutdownHook ();
        
        // now run SpringConfig and inject the locale bean
        context.register (SpringConfig.class);
        context.refresh ();

        MakeCalendar makeCal = context.getBean (MakeCalendar.class);
        makeCal.makeCal ();
    }
}
