package com.dappermoose.batchlitcal.calendar;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 *  This is the class which makes the calendar.
 *
 *  @author heitkergm@acm.org
 */
@Log4j2
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
        LOG.debug ("making the calendar");
    }
}
