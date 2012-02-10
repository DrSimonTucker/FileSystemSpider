package uk.ac.shef.dcs.oak.spider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;

public class FileInfo
{

   static MessageDigest digest;
   static
   {
      try
      {
         digest = MessageDigest.getInstance("md5");
      }
      catch (NoSuchAlgorithmException e)
      {
         e.printStackTrace();
      }
   }

   long fSize;
   String md5Name;
   long lastUpdateDate;
   String[] md5Parents;
   String md5Contents;
   String name;
   private final int BUFFER_SIZE = 4096;

   public FileInfo(File f)
   {
      runExtract(f);
   }

   public static void main(String[] args)
   {
      FileInfo info2 = new FileInfo(new File(
            "/Users/sat/local/code/fsspider/target/classes/uk/ac/shef/dcs/oak/spider/Runner.class"));
      System.out.println(info2);
      FileInfo info = new FileInfo(new File(
            "/Users/sat/local/code/fsspider/target/test-classes/uk/ac/shef/dcs/oak/AppTest.class"));
      System.out.println(info);

   }

   private void runExtract(File f)
   {
      fSize = f.length();
      md5Name = Hex.encodeHexString(digest.digest(f.getName().getBytes()));
      lastUpdateDate = f.lastModified();

      List<String> parents = new LinkedList<String>();
      File tf = f;
      while (tf.getParentFile() != null)
      {
         parents.add(tf.getParentFile().getName());
         tf = tf.getParentFile();
      }

      md5Parents = new String[parents.size()];
      for (int i = parents.size() - 1; i >= 0; i--)
         md5Parents[md5Parents.length - i - 1] = Hex.encodeHexString(digest.digest(parents.get(i)
               .getBytes()));

      if (f.isFile())
      {
         try
         {
            MessageDigest tempDigest = MessageDigest.getInstance("md5");
            tempDigest.reset();
            DigestInputStream is = new DigestInputStream(new FileInputStream(f), tempDigest);
            is.on(true);
            byte[] buffer = new byte[BUFFER_SIZE];
            while (is.read(buffer) > 0)
               ;
            md5Contents = Hex.encodeHexString(tempDigest.digest());
            is.close();

         }
         catch (NoSuchAlgorithmException e)
         {
            e.printStackTrace();
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }

      }
      else
         md5Contents = "DIR";

      name = f.getAbsolutePath();
   }

   @Override
   public String toString()
   {
      String outPars = "";
      if (md5Parents.length > 0)
         outPars = md5Parents[0];
      for (int i = 1; i < md5Parents.length; i++)
         outPars += "|" + md5Parents[i];

      // return md5Contents + "/" + name;
      return md5Name + "~" + fSize + "~" + lastUpdateDate + "~" + outPars + "~" + md5Contents + "~"
            + name;
   }

}
