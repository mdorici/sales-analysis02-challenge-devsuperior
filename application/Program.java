package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Sale> saleList = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                saleList.add(new Sale(Integer.parseInt(fields[0]),
                        Integer.parseInt(fields[1]),
                        fields[2],
                        Integer.parseInt(fields[3]),
                        Double.parseDouble(fields[4])));
                line = br.readLine();
            }
        Set<String> uniqueNames = new HashSet<>();
        for(Sale s : saleList) {
            uniqueNames.add(s.getSeller());
        }

        for(String name : uniqueNames) {
            double sumSales = saleList.stream()
                    .filter(s -> s.getSeller().equals(name))
                    .map(Sale::getTotal)
                    .reduce(0.0, Double::sum);
            System.out.printf(name + " - R$ %.2f %n", sumSales);
        }

        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
