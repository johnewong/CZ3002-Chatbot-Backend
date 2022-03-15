package cz3002.backend.chatbot.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
    public List readFile(String fileName){

        List fileList= new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));//换成你的文件名
            //reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split("，");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                String last = item[item.length-1];//这就是你要的数据了

                fileList.add(last);
                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                System.out.println(last);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList;
    }

    public List getColByName(String colName, List fileList){
        List col= new ArrayList<>();

        return fileList;

    }

    public List getRow(String rowName, List fileList){
        List col= new ArrayList<>();
        return fileList;

    }



}
