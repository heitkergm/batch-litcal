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

package com.dappermoose.batchlitcal.days;

import lombok.extern.slf4j.Slf4j;

/**
 * week day utility.
 *
 * <p>This is often known as Zeller's Congruence.</p>
 *
 * @author heitkergm@acm.org
 */
@Slf4j
public final class WeekDay
{
    private WeekDay ()
    {
    }

    /**
     * determines the index (0-6) as the day of week for a given date.
     *
     * @param year the year to evaluate
     * @param month the month to evaluate
     * @param day the day of month to evaluate
     * @return 0-6, as the day of week.
     */
    public static int calcWeekDate (final int year, final int month,
            final int day)
    {
        int retVal;

        int m = month;
        int y = year;
        if (m <= 2)
        {
            m += 10;
            y--;
        }
        else
        {
            m -= 2;
        }
        // deepcode ignore LogLevelCheck:
        log.debug ("WeekDay.m " + m);
        int c = y / 100;
        // deepcode ignore LogLevelCheck:
        log.debug ("WeekDay.c " + c);
        int a = y % 100;
        // deepcode ignore LogLevelCheck:
        log.debug ("WeekDay.a " + a);
        int b = (13 * m - 1) / 5 + a / 4 + c / 4;
        // deepcode ignore LogLevelCheck:
        log.debug ("WeekDay.b" + b);

        retVal = (b + a + day - (2 * c)) % 7;
        if (retVal < 0)
        {
            retVal += 7;
        }
        // deepcode ignore LogLevelCheck:
        log.debug ("WeekDay.retVal " + retVal);

        return retVal;
    }
}
