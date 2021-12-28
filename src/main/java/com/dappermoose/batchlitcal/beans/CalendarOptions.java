package com.dappermoose.batchlitcal.beans;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 *  This is the class (eventually a Bean) which holds the input values.
 *
 *  @author heitkergm@acm.org
 */
@Slf4j
@EqualsAndHashCode
@ToString
public class CalendarOptions implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /**
     * the value of the year.
     */
    @Getter
    @Setter
    private Integer year;

    /**
     * Make an instance of the calendar options class.
     */
    public CalendarOptions ()
    {
        LOG.debug ("Creating the Calendar Options class");
    }
}
