package fr.whitefox.hera.utils;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;


public class APICall {
        public static JSONObject getIP(String args) {

            URL url = null;
            try {
                url = new URL("http://ip-api.com/json/" + args + "?fields=status,message,country,isp,mobile,proxy,hosting,query");
            } catch (MalformedURLException e) {
                return null;
            }

            if(url == null){
                return null;
            }

            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                return null;
            }

            if(conn == null){
                return null;
            }

            try {
                conn.setRequestMethod("GET");
            } catch (ProtocolException e) {
                return null;
            }

            try {
                conn.connect();
            } catch (IOException e) {
                return null;
            }

            //Getting the response code
            int responsecode = 0;
            try {
                responsecode = conn.getResponseCode();
            } catch (IOException e) {
                return null;
            }

            if(responsecode == 0){
                return null;
            }


            if (responsecode != 200) {
                System.out.println("HttpResponseCode: " + responsecode);
                return null;
            }

            StringBuilder inline = new StringBuilder();
            Scanner scanner = null;
            try {
                scanner = new Scanner(url.openStream());
            } catch (IOException e) {
                return null;
            }

            if(scanner == null){
                return null;
            }

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }

            //Close the scanner
            scanner.close();

            //Using the JSON simple library parse the string into a json object
            JSONParser jsonParser = new JSONParser();
            JSONObject data_obj = null;
            try {
                data_obj = (JSONObject) jsonParser.parse(inline.toString());
            } catch (ParseException e) {
                return null;
            }

            if(data_obj == null){
                return null;
            }

            return data_obj;


    }
}
