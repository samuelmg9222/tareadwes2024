package principal;



import java.util.List;
import java.util.Scanner;

import dao.PlantaDAO;
import modelo.Planta;

public class Principal {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        	
        	
 PlantaDAO plantaDao = new PlantaDAO();
        
        
        plantaDao.insertarPlanta();
        	plantaDao.verplantas();
        	
       
        
    }
    
}

