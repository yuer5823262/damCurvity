package com.example.damcurvity.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatUtils {
    public  static List<List<Double>> getLinesDouble(String fileName) throws IOException
    {
        List<String> stringList = getStringLines(fileName);
        return getDouDataLines(stringList);
    }
    public  static List<String> getStringLines(String fileName) throws IOException
    {
        List<String> stringList = new ArrayList<>();
        //BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = null;
        while((str = bufferedReader.readLine()) != null)
        {
            stringList.add(str);
        }
        inputStream.close();
        bufferedReader.close();
        return stringList;
    }

    public static List<List<String>> getStringLinesSpilt(String fileName) throws IOException
    {
        List<String> stringList = getStringLines(fileName);
        List<List<String>> result = new ArrayList<>();
        for(String str:stringList)
        {
            result.add(Arrays.asList(str.split("\\s+")));
        }
        return result;
    }

    public  static List<List<Integer>> getLinesInt(String fileName) throws IOException
    {
        List<String> stringList = getStringLines(fileName);
        return getIntDataLines(stringList);
    }


    public  static List<List<Integer>> getIntDataLines(List<String> stringList)
    {
        List<List<Integer>> result = new ArrayList<>();
        for(String str:stringList)
        {
            result.add(getInt(str));
        }
        return result;

    }
    public static List<Integer> getInt(String str)
    {
        String regex ="\\d*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        List<Integer> resultList = new ArrayList<>();
        while (m.find()) {
            if (!"".equals(m.group()))
                resultList.add(Integer.parseInt(m.group()));
        }
        return resultList;
    }

    public  static List<List<Double>> getDouDataLines(List<String> stringList)
    {
        List<List<Double>> result = new ArrayList<>();
        for(String str:stringList)
        {
            result.add(getDouble(str));
        }
        return result;

    }
    public static List<Double> getDouble(String str)
    {

        String regex= "[+-]?\\d+(?:\\.\\d+)?" ;
        Matcher m= Pattern.compile(regex, Pattern.MULTILINE).matcher(str);
        List<Double> result= new  ArrayList<>();
        while (m.find()){
            result.add(Double.parseDouble(m.group()));
        }
        return result;
    }


    public  static void writeFile(List<String> stringList,String fileName) throws IOException
    {
        FileWriter fileWriter = new FileWriter(fileName);
        for (String str:stringList)
        {
            fileWriter.write(str+"\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public static Double strToDouVal(String str)
    {
        try {
            if(str.equals("NaN")) return null;
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static List<String> getFileNameList(String path)
    {
        File f = new File(path);
        List<String> fileNameList = new ArrayList<>();
        File result[] = f.listFiles();
        for(int i = 0; i<result.length; i++){
            File fs = result[i];
            String fileName = fs.getName();
            if (fs.isFile()&&(fileName.endsWith(".dat")||fileName.endsWith(".out")||fileName.endsWith(".txt"))) {
                fileNameList.add(fs.getName());
            }
        }
        return fileNameList;
    }
}
