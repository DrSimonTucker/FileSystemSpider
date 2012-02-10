package uk.ac.shef.dcs.oak;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import uk.ac.shef.dcs.oak.spider.FileInfo;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
   /**
    * Create the test case
    * 
    * @param testName
    *           name of the test case
    */
   public AppTest(String testName)
   {
      super(testName);
   }

   /**
    * @return the suite of tests being tested
    */
   public static Test suite()
   {
      return new TestSuite(AppTest.class);
   }

   /**
    * Rigourous Test :-)
    */
   public void testApp()
   {
      // Test the speed of processing the md5 sum
      File f = new File("/Users/sat/local/code/audioview/.git/objects/6a/tmp_obj_KvtPQ9");
      FileInfo info = new FileInfo(f);
      System.out.println(info);
   }
}
