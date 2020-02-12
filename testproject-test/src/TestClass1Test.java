package gproc.framework.util;

import static org.junit.Assert.*;

import TestClass1;

import org.junit.Test;
import org.junit.runner.RunWith;

public class TestClass1Test {
    @Test
    public void testCloneBean() {
        
        TestClass1 test = new TestClass1();
        assertNotEquals(null, test);
    }
}
