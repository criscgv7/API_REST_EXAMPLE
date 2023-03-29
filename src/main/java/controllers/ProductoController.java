package controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/productos")
public class ProductoController {


    /*
     * El metodo siguiente es de ejemplo para entender el formato JSON no tiene que ver en si con el proyecto
     */
  
    public List<String> nombres(){
        List<String> nombres = Arrays.asList("Salma", "Jusith", "Elisabet"); 
        return nombres; 
    }
}
