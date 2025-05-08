package com.example.damcurvity;
import com.example.damcurvity.common.Constant;
import com.example.damcurvity.entity.BaseStationNode;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


class DamCurvityApplicationTests {

    public static void main(String[] args){
        String temp = "$HUASI,GET,DEVICE,912954,55,0,912954,1000,912811,1000,907074,1000,907075,1000,907076,1000,907077,1000,907515,1000,913866,1000,913867,1000,913868,1000,913869,1000,914209,1000,911834,1000,911835,1000,911836,1000,911837,1000,911985,1000,914171,1000,914172,1000,914173,1000,914174,1000,914168,1000,913846,1000,913847,1000,913848,1000,913849,1000,913786,1000,912951,1000,912825,1000,914471,1000,914472,1000,914473,1000,914474,1000,914640,1000,904199,1000,904200,1000,904201,1000,904202,1000,905667,1000,912563,1000,914175,1000,914176,1000,914177,1000,914178,1000,914169,1000,907086,1000,907087,1000,907088,1000,907089,1000,907570,1000,908890,1000,908891,1000,908892,1000,908893,1000,908914,1000,OK*39";
        temp = temp.substring(1, temp.length()-5);
        List<String> strings = Arrays.asList(temp.split(","));
        strings = strings.subList(6,strings.size()-1);
        for(int i = 0;i<strings.size();i+=2)
        {
            System.out.println(strings.get(i));
        }

    }

}
