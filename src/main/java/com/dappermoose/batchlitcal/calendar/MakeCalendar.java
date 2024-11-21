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

package com.dappermoose.batchlitcal.calendar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Locale;

import jakarta.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 *  This is the class which makes the calendar.
 *
 *  @author heitkergm@acm.org
 */
@Slf4j
@Component
public class MakeCalendar
{
    @Inject
    private MessageSource messageSource;

    @Inject
    @NonNull
    private Locale locale;

    @Inject
    private BufferedWriter writer;

    @Inject
    private OutputUtils outputUtils;

    /**
     * Make an instance of the calendar maker class.
     */
    public MakeCalendar ()
    {
        log.debug ("Creating the MakeCalendar class");
    }

    /**
     * this is the routine which makes the calendar.
     */
    public void makeCal ()
    {
        String msg = messageSource.getMessage ("startCalendar", null, locale);
        log.info (msg);

        // make the 6 "seasons"
        // 1 Advent
        // 2 Christmas
        // 3 Ordinary time, beginning at week 1
        // 4 Lent
        // 5 Easter
        // 6 Ordinary time, ending at week 34 (Christ the King)

        // for each, put the seasonal dates.
        // when done with seasons, as a multi-stage processor, do the following:
        //    override seasonal entries for Solemnities and Feasts
        //    put in memorials and optional memorials

        // along the way, add options to CalendarOptions.
        // e.g. observe Ascension on Thursday or following Sunday,
        //      observe Epiphany on Jan 6 or first Sunday after New Year,
        //      country-specific Solemnities, Feasts, Memorials, and Optional
        //          Memorials...
        // etc.
        outputUtils.writeHeader ();

        // THE END
        outputUtils.writeFooter ();

        try
        {
            // close does a flush, then closes
            writer.close ();
        }
        catch (IOException e)
        {
            log.error (e.getClass ().getName () + " " + e.getMessage ());
        }
    }
}
