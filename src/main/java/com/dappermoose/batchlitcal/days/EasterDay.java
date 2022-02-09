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

 package com.dappermoose.batchlitcal.days;

import lombok.extern.slf4j.Slf4j;

/**
 * easter day utility.
 *
 * <p>This is Gauss's algorithm for Easter.</p>
 *
 * @author heitkergm@acm.org
 */
@Slf4j
public final class EasterDay
{
    private EasterDay ()
    {
    }

    /**
     * determines the date of Easter given the input year.
     *
     * @param year the year to evaluate
     * @return an integer. If less than or equal to 31, then in March on that date;
     *                     if greater than 31, then in April on date - 31.
     */
    public static int calcEaster (final int year)
    {
        int retVal;

        int bigC = year / 100 + 1;
        int g = 3 * bigC / 4 - 12;
        int bigG = year % 19 + 1;
        int c = (8 * bigC + 5) / 25 - 5 - g;
        int e = 5 * year / 4 - g - 10;
        int bigE = (11 * bigG + 20  + c) % 30;

        if (bigE != 25 && bigG > 11)
        {
            bigE++;
        }

        if (bigE == 24)
        {
            bigE++;
        }

        int d = 44 - bigE;

        if (d < 21)
        {
            d += 30;
        }

        retVal = d + 7 - ((d + e) % 7);
        LOG.debug ("EasterDay.retVal " + retVal);

        return retVal;
    }
}
