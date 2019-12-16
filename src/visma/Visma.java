/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visma;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import static visma.FileReader.getByExpDate;
import static visma.FileReader.getByQuantity;
import static visma.FileReader.getExpirationDate;
import static visma.FileReader.getFileDate;
import static visma.FileReader.mergeDuplicate;

/**
 *
 * @author Sars
 */
public class Visma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        String file = "sample.csv";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String expData = "2010-11-11";
        int qnt = 30;

//      metodas verčiantis stringą į datą
        Date expdate = getExpirationDate(expData);

        List<Item> data = new ArrayList<>(getFileDate(file));

//       mergeDuplicate(data);
//       getByQuantity(mergeDuplicate(data),qnt);
//       getByExpDate(mergeDuplicate(data), expdate)
        Scanner scanner = new Scanner(System.in);
        System.out.println("●	Peržiūrėti prekes kurių kiekis mažesnis nei *  SPAUSKITE    1");
        System.out.println("●	Patikrinti prekes, kurių galiojimo laikas pasibaigęs nurodytai datai SPAUSKITE    2 ");
        int choise = scanner.nextInt();

        if (choise == 1) {
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Įvesklte kiekį: ");
            qnt = sc1.nextInt();
            for (Item item : getByQuantity(mergeDuplicate(data), qnt)) {
                System.out.println("getByQuantity " + item.getName() + " " + item.getCode() + " " + item.getQuantity() + " " + item.getDate());
            }

        } else if (choise == 2) {
            Scanner sc2 = new Scanner(System.in);
            System.out.println("Įveskite datą nurodytu formatu: yyyy-mm-dd");
            expData = sc2.next();

            for (Item item : getByExpDate(mergeDuplicate(data), expdate)) {
                System.out.println("getByExpDate " + item.getName() + " " + item.getCode() + " " + item.getQuantity() + " " + item.getDate());
            }

        } else {
            System.out.println("Jūsų pasirinkimas netinkamas");
        }
    }

}
