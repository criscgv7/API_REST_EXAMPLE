package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Producto;
import com.example.services.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    /*
     * El método siguiente va a responder a una petición (request) del tipo:
     * http://localhost:8080/productos?page=3&size=
     * debe ser capaz de deolver un listado de productos paginados o no pero en
     * cualquier caso ordenados
     * por un criterio (nombre, descripcion...)
     * y la anterior implica @RequestParam
     * 
     * /productos/id => @PathVariable
     */
    @GetMapping
    public ResponseEntity<List<Producto>> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size) {
        ResponseEntity<List<Producto>> responseEntity = null;
        List<Producto> productos = new ArrayList<>();
        Sort sortByNombre = Sort.by("nombre"); //admite lista de propiedades separadas por coma 



        if (page != null && size != null) {
            try {
                 Pageable pageable = PageRequest.of(page, size, sortByNombre);
                 Page<Producto> productosPaginados = productoService.findAll(pageable);
                 productos =productosPaginados.getContent(); 
                 responseEntity = new ResponseEntity<List<Producto>> (productos, HttpStatus.OK);
            } catch (Exception e) {
               responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
            }
        } else {
            //Sin paginación pero ordenado
            try {
                productos =productoService.findAll(sortByNombre); 
                responseEntity = new ResponseEntity<List<Producto>>(productos, HttpStatus.OK); 

            } catch (Exception e) {
                responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT); 

            }
        }
        return responseEntity;

    }

    /*
     * El metodo siguiente es de ejemplo para entender el formato JSON no tiene que
     * ver en si con el proyecto
     */
    /*
     * @GetMapping
     * public List<String> nombres(){
     * List<String> nombres = Arrays.asList("Salma", "Jusith", "Elisabet");
     * return nombres;
     * }
     */
}
