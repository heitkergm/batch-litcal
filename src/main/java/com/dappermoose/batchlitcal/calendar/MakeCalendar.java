package com.dappermoose.batchlitcal.calendar;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
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
    private Locale locale;

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
     */
    public void makeCal ()
    {
        String msg = messageSource.getMessage ("startCalendar", null, locale);
        LOG.info (msg);

        // make seasons
        // 1st Advent
        // 2nd Christmas
        // 3rd Ordinary time, beginning at week 1
        // 4th Lent
        // 5th Easter
        // 6th Ordinary time, ending at week 34 (Christ the King)

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
    }
}
