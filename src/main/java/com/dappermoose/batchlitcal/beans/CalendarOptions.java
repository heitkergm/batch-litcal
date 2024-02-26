/*  Copyright 2022-2024 G. Matthew Heitker
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
    private static final long serialVersionUID = -5578278990768465845L;

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
        log.debug ("Creating the Calendar Options class");
    }
}
