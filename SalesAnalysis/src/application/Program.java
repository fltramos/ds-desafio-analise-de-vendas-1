package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Entre o caminho do arquivo: ");
        String path = sc.nextLine();
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        	
        	List<Sale> list = new ArrayList<>();
        	
        	String line = br.readLine();
        	while (line != null) {
        		String[] fields = line.split(",");
        		list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
        		line = br.readLine();
        	}
        	
        	Stream<Sale> sale = list.stream().filter(lists -> lists.getYear().equals(2016)).sorted((s1, s2) -> s2.averagePrice().compareTo(s1.averagePrice()));
        	
        	System.out.println();        	
        	System.out.println("Cinco primeiras vendas de 2016 de maior preço médio");
        	sale.limit(5).forEach(System.out::println);
        	
        	Stream<Sale> sale1 = list.stream().filter(lists -> lists.getSeller().equals("Logan")).filter(lists -> lists.getMonth() ==1 || lists.getMonth()==7);        	
        	System.out.println();        	
        	double val = sale1.map(lists -> lists.getTotal()).reduce(0.0, (x, y) -> x+y);
        	
        	System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = " + val);
        	
        }catch (IOException e) {
        	System.out.println("Error: " + e.getMessage());
        }        
        
        sc.close();

	}

}
