package com.example.nutritiontrackerguiv4;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    MainActivity M = mock(MainActivity.class);
    File fileStartTest = mock(File.class);

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void subtraction_isCorrect(){assertEquals(4, 8 - 4);}

    @Test
    public void handleStartPage_False(){
        boolean created = M.handleStartPage();
        assertFalse(created);
    }


}