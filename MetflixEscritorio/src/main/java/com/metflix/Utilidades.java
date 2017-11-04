package com.metflix;

public class Utilidades {

	public Utilidades() {
		
	}

	/**
     * Validacion de campos(String)
     * @param cadena
     * @return true: si es nulo o vacio
     */
    public static boolean esNuloVacio(String... cadena) {
       for (String st : cadena) {
            if  (st == null || st.equals(""))
               return true;
       } 
       return false;
    }
}
