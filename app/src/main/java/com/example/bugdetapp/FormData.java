package com.example.bugdetapp;


public class FormData {
    public static String[] gm = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    public static String[] gpd = {"Daily","Weekly","Monthly","Yearly"};
    public static String response = "";

    public static String[] bill_data = {"", "", "", "", "", ""};
    public static String[] victim_data = {"", "", "", "", ""};
    public static String[] incident_data = {"", "", "", "", ""};

    public static String[] axisData = {""};
    public static int[] yAxisData = {0};

    public static String[] pieBillName;
    public static String[] pieBillPrice;

    public static String[] thisMonthPrice;
    public static String[] thisMonthName;

    public static String[] month;
    public static String[] linePaymentDuration;
    //public static String[] linePaymentDuration;

    public static String[] hexColor = {"#f8b195","#f67280","#c06c84","#6c5b7b","#355c7d","99B898","FECEAB","FF847C","E84A5F","A8E6CE"};

    public static int FilledSus = 0;
    public static int FilledVic = 0;
    public static int FilledInc = 0;

    public static String[] en = {""}, ia = {""}, toi = {""}, dt = {""}, tm = {""};

    public static void ClearData(){
        bill_data[0] = "";
        bill_data[1] = "";
        bill_data[2] = "";
        bill_data[3] = "";
        bill_data[4] = "";

        victim_data[0] = "";
        victim_data[1] = "";
        victim_data[2] = "";
        victim_data[3] = "";
        victim_data[4] = "";

        incident_data[0] = "";
        incident_data[1] = "";
        incident_data[2] = "";
        incident_data[3] = "";
        incident_data[4] = "";

        FilledSus = 0;
        FilledInc = 0;
        FilledVic = 0;
    }

    public static int mt = 0;
    public static int mb = 0;
    public static int ms = 0;
    public static int me = 0;

    public static int rd = 0;

    public static String[] personal_data = {"","","","",""};
    public static String st = "";
    public static String username = "";

    public static void saveList(){


    }
}
