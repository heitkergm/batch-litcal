/*  Copyright 2022-2023 G. Matthew Heitker
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

/**
 * leap year utility.
 *
 * @author heitkergm@acm.org
 */
public final class LeapYear
{
    private LeapYear ()
    {
    }

    /**
     * determines whether the give year is a leap year.
     *
     * @param year the year to evaluate
     * @return true/false, depending on whether a leap year or not.
     */
    public static boolean isLeapYear (final int year)
    {
        boolean retVal = false;

        if (year % 400 == 0)
        {
            retVal = true;
        }
        else
        {
            if ((year % 100 != 0) && (year % 4 == 0))
            {
                retVal = true;
            }
        }

        return retVal;
    }
}
