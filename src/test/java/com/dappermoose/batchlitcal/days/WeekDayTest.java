/*
 *  Copyright 2022-2025 G. Matthew Heitker
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

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *  Test the WeekDay class.
 * @author heitkergm@acm.org
 */
public class WeekDayTest
{
    /**
     * Dummy constructor.
     */
    public WeekDayTest ()
    {
    }

    /**
     * setup before class instantiation.
     */
    @BeforeAll
    public static void setUpClass ()
    {
    }

    /**
     * class tear down when done with all tests.
     */
    @AfterAll
    public static void tearDownClass ()
    {
    }

    /**
     * Setup before each test.
     */
    @BeforeEach
    public void setUp ()
    {
    }

    /**
     * Tear down after each test.
     */
    @AfterEach
    public void tearDown ()
    {
    }

    /**
     * Test of calcDate method, with 2011/04/24.
     */
    @Test
    public void testCalcDateWeekDaySun20110424 ()
    {
        System.out.println ("calcDate - 2011/04/24");
        Assertions.assertEquals (0, WeekDay.calcWeekDate (2011, 4, 24));
    }

    /**
     * Test of calcDate method, with 2012/04/09.
     */
    @Test
    public void testCalcDateWeekDayFri20120409 ()
    {
        System.out.println ("calcDate - 2012/04/09");
        Assertions.assertEquals (1, WeekDay.calcWeekDate (2012, 4, 9));
    }

    /**
     * Test of calcDate method, with 2016/08/12.
     */
    @Test
    public void testCalcDateWeekDayFri20160812 ()
    {
        System.out.println ("calcDate - 2016/08/12");
        Assertions.assertEquals (5, WeekDay.calcWeekDate (2016, 8, 12));
    }

    /**
     * Test of calDate method, with 2000/01/01.
     */
    @Test
    public void testCalcDateWeekDayFri20160101 ()
    {
        System.out.println ("calcDate - 2016/01/01");
        Assertions.assertEquals (5, WeekDay.calcWeekDate (2016, 1, 1));
    }
}
