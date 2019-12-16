/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visma;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sars
 */
public class FileReader {

    public static Date getExpirationDate(String date) throws ParseException {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date expDay = sdfDate.parse(date);
//        String strDate = sdfDate.format(expDay);
        return expDay;
    }
// Metodas nuskaito CSV failo duomenis ir gražina objektų sąrašą

    static List getFileDate(String file) throws ParseException {

        List<Item> itemsList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
            // Read file line by line
            String line = reader.readLine();
            while (line != null) {
                while ((line = reader.readLine()) != null) {
                    String[] a = {"", "", "", ""};
                    int cnt = 0;
                    for (String item : line.split(",")) {
                        a[cnt++] = item;
                    }
                    Long b = new Long(a[1]);
                    Integer c = new Integer(a[2]);
                    String d = a[3];
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                    Date date = sdf.parse(d);
                    Item item = new Item();
                    item.setName(a[0]);
                    item.setCode(b);
                    item.setQuantity(c);
                    item.setDate(date);
                    itemsList.add(item);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemsList;
    }

//    Metodas atrenka visas prekes kurių likutis yra mažesnis negu paduotas
    public static List<Item> getByQuantity(List<Item> data, int qnt) {
        List<Item> sortedList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getQuantity() < qnt) {
                sortedList.add(data.get(i));
            }
        }
        sortedList.sort(Comparator.comparing(Item :: getName));
        return sortedList;
    }
//    Metodas atrenka visas prekes kurių galiojimo laikas yra mažesnis negu paduotas
    public static List<Item> getByExpDate(List<Item> data, Date expdate) {
        List<Item> expDateList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getDate().before(expdate)) {
                expDateList.add(data.get(i));
            }
        }
        expDateList.sort(Comparator.comparing(Item :: getName));
        return expDateList;
    }

//    Metodas tikrina PAVADINIMĄ, KODĄ, DATĄ ir susumavęs kiekį grąžina naują Objektų sarašą 
    public static List<Item> mergeDuplicate(List<Item> data) {
        List<Item> uniqueItemsList = new ArrayList<>();
        int replaceCounter = 0;
        for (int i = 0; i < data.size(); i++) {
            Item it = data.get(i);
            if (uniqueItemsList.size() == 0) {
                replaceCounter = 1;
            }
            String name = data.get(i).getName();
            Long code = data.get(i).getCode();
            Date exDay = data.get(i).getDate();

            for (int x = 0; x < uniqueItemsList.size(); x++) {
                if (name.equals(uniqueItemsList.get(x).getName()) && code.equals(uniqueItemsList.get(x).getCode()) && exDay.equals(uniqueItemsList.get(x).getDate())) {
                    int sum = uniqueItemsList.get(x).getQuantity() + data.get(i).getQuantity();
                    uniqueItemsList.get(x).setQuantity(sum);
                    replaceCounter = 0;
                    break;
                } else {
                    replaceCounter = 1;
                }
            }
            if (replaceCounter == 1) {
                uniqueItemsList.add(it);
            }
        }
        return uniqueItemsList;
    }

}
