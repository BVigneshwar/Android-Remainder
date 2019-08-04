package com.vignesh.remainder;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class) // <-- @RunWith + @BeforeClass = Error
public class ExampleInstrumentedTest {

    @BeforeClass
    public static void setup() {
        // Setting up once before all tests
    }

    @Test
    public void testing() {
        // Testing....
    }
}
