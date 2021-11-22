package com.dappermoose.batchlitcal.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.log4j.Log4j2;

/**
 * This is the actual main class.
 *
 */
@Log4j2
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
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext (SpringConfig.class);

        // make sure our context shuts down when JVM wants to
        context.registerShutdownHook ();
    }
}
