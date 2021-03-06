/*  Copyright 2022 G. Matthew Heitker
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

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
        if (LOG.isDebugEnabled ())
        {
            for (String pname : pnames)
            {
                LOG.debug ("property name: " + pname + ": " + ps.getProperty (pname));
            }
        }
        LOG.debug ("nonOptionsArgs: " + ps.getProperty ("nonOptionArgs"));

        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext ();
        context.getEnvironment ().getPropertySources ().addFirst (ps);

        // now run SpringConfig and inject the locale bean
        context.register (SpringConfig.class);
        context.refresh ();

        // make sure our context shuts down when JVM wants to
        context.registerShutdownHook ();

        MakeCalendar makeCal = context.getBean (MakeCalendar.class);
        makeCal.makeCal ();

        context.close ();
    }
}
