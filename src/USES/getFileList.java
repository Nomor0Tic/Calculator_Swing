package USES;
import java.io.File;

public class getFileList {
    public void getFileList(String addr){
        File file = new File(addr);

        File[] fileList = file.listFiles();

         for (int i = 0; i < fileList.length; i++) {
               if (fileList[i].isFile()) {
                      String fileName = fileList[i].getPath();
                      System.out.println("�ļ���" + fileName);
               }

                if (fileList[i].isDirectory()) {
                       String fileName = fileList[i].getName();
                        System.out.println("Ŀ¼��" + fileName);
                  }
             }
        }
        public static void main(String[] args){
            getFileList getFileList = new getFileList();
            getFileList.getFileList("C:/Users/23923/Pictures/");
        }

    }

