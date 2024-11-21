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
 *  Test the LeapYear class.
 *
 * @author heitkergm@acm.org
 */
public class LeapYearTest
{
    /**
     * Dummy constructor.
     */
    public LeapYearTest ()
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
     * Test of isLeapYear method, with 1900.
     */
    @Test
    public void testIsLeapYear1900 ()
    {
        System.out.println ("isLeapYear - 1900");
        Assertions.assertFalse (LeapYear.isLeapYear (1900));
    }

    /**
     * Test of isLeapYear method, with 2000.
     */
    @Test
    public void testIsLeapYear2000 ()
    {
        System.out.println ("isLeapYear - 2000");
        Assertions.assertTrue (LeapYear.isLeapYear (2000));
    }

    /**
     * Test of isLeapYear method, with 2016.
     */
    @Test
    public void testIsLeapYear2016 ()
    {
        System.out.println ("isLeapYear - 2016");
        Assertions.assertTrue (LeapYear.isLeapYear (2016));
    }

    /**
     * Test of isLeapYear method, with 2013.
     */
    @Test
    public void testIsLeapYear2013 ()
    {
        System.out.println ("isLeapYear - 2013");
        Assertions.assertFalse (LeapYear.isLeapYear (2013));
    }

    /**
     * Test of isLeapYear method, with 2014.
     */
    @Test
    public void testIsLeapYear2014 ()
    {
        System.out.println ("isLeapYear - 2014");
        Assertions.assertFalse (LeapYear.isLeapYear (2014));
    }

    /**
     * Test of isLeapYear method, with 2015.
     */
    @Test
    public void testIsLeapYear2015 ()
    {
        System.out.println ("isLeapYear - 2015");
        Assertions.assertFalse (LeapYear.isLeapYear (2015));
    }
}
