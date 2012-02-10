package uk.ac.shef.dcs.oak.spider;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Runner
{
   public static void main(String[] args)
   {
      Runner runner = new Runner();

      // Get the user root directory
      long sTime = System.currentTimeMillis();
      File root = new File(System.getProperty("user.home"));
      root = new File("/Users/sat/backup/old-backup-disk/weldon-main-linux/simon/.wine/dosdevices/");
      runner.spider(root);
      runner.close();
      System.out.println((System.currentTimeMillis() - sTime) / (1000.0 * 60));
   }

   PrintStream ps;

   public void close()
   {
      ps.close();
   }

   public Runner()
   {
      try
      {
         ps = new PrintStream("dirs.list");
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   public void spider(File root)
   {
      System.out.println(root + " and " + root.isDirectory());
      if (root.isFile())
         process(root);
      for (File f : root.listFiles())
         if (f.isDirectory())
            spider(f);
         else
            process(f);
   }

   private void process(File f)
   {
      // System.out.println(f.getAbsolutePath());
      FileInfo info = new FileInfo(f);
      ps.println(info);
   }
}
