package com.distribuida.principal;

import com.distribuida.entities.Autor;

public class AutorPrincipal {

    public static void main(String[] args) {

        Autor autor = new Autor(1,"Jose","Perez","Peru","Piura","3484659201","joseperez15@gmail.com");

        System.out.println(autor.toString());
    }


}
