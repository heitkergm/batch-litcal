/*  Copyright 2022-2025 G. Matthew Heitker
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

import java.awt.EventQueue;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import org.jspecify.annotations.NonNull;

import com.dappermoose.batchlitcal.calendar.MakeCalendar;

import lombok.extern.slf4j.Slf4j;


/**
 * This is the actual main class.
 *
 * @author heitkergm@acm.org
 */
@Slf4j
@ComponentScan
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
    public static void main (final @NonNull String[] args)
    {
        log.debug ("starting main batch litcal program");

        ApplicationContext context = new SpringApplicationBuilder (Main.class)
                .headless (false).web (WebApplicationType.NONE).registerShutdownHook (true)
                .run (args);

        EventQueue.invokeLater (() ->
        {
            MakeCalendar makeCal = context.getBean (MakeCalendar.class);
            makeCal.makeCal ();
        });
    }
}
