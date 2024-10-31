package principal;



import java.util.List;
import java.util.Scanner;

import Fachada.Fachada;
import dao.PlantaDAO;
import dao.EjemplarDAO;
import modelo.Planta;

public class Principal {

    public static void main(String[] args) {
    	
        Scanner in = new Scanner(System.in);
        EjemplarDAO ejemplarDao = new EjemplarDAO(ConexionBD.getInstance().getConnection());
        
 Fachada fachada = new Fachada(ConexionBD.getInstance().getConnection());
 
 fachada.mostrarMenuGestionPrincipal();
        	
       
        
    }
    
}

