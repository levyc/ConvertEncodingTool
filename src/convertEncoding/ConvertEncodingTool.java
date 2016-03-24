package convertEncoding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * 功能.输入一个目录和编码格式，将目录下的所有文件都转为指定的编码格式
 * 
 * @author levy 实现思路：1.循环File下的所有文件 2.是否一个目录，若是目录，递归回来 3.若是一个文件，改变编码格式 。
 */

public class ConvertEncodingTool {

    public static void main(String[] args) throws Exception {
        File file = getCurrentFile("D:\\开源\\jfinal-2.2-all\\jfinal-2.2-all\\com\\jfinal");
        new ConvertEncodingTool().convertcurrentFilesEncoding(file);
    }

    private void convertcurrentFilesEncoding(File currentFile) throws Exception {
        if (currentFile.isDirectory()) {
            System.out.println("我遇到了目录:" + currentFile.getName());
            File[] files = currentFile.listFiles();
            for (File file : files) {
                convertcurrentFilesEncoding(file);
            }
        } else if (currentFile.isFile()) {
            System.out.print(currentFile.getName() + "正在");
            convertFileEncoding(currentFile,"gbk");
        }
    }

    /**
     * 思路：1.先从原文件中按原编码读取内容出来 
     * 2.将内容按指定编码格式写入到文件中
     * 
     * @param convertedFile
     * @param charset
     * @throws Exception 
     */
    private void convertFileEncoding(File convertedFile, String charset) throws Exception {
       String content = readContent(convertedFile,"UTF-8");
       writeContent(convertedFile, content, charset);

    }

    private String readContent(File readFile, String charset) throws Exception {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(new FileInputStream(readFile), charset));
        StringBuilder content = new StringBuilder();
        String line = null;
         while((line = bufferedReader.readLine()) != null) {
            content.append(line).append("\n");
        }
        if(bufferedReader!=null)
            bufferedReader.close();
        return new String(content);
    }
    
    private void writeContent(File file,String content,String charset) throws IOException{
        Writer out =  new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),charset));
        out.write(content);
        out.flush();
        if(out!=null)
            out.close();
    }
    

    private static File getCurrentFile(String path) {
        return new File(path);
    }


}
