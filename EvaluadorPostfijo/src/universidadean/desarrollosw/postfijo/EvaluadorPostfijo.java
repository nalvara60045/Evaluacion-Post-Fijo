/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogot� - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Evaluador de Expresiones Postfijas
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.desarrollosw.postfijo;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Esta clase representa una clase que eval�a expresiones en notaci�n polaca o
 * postfija. Por ejemplo: 4 5 +
 */
public class EvaluadorPostfijo {

    /**
     * Realiza la evaluaci�n de la expresi�n postfijo utilizando una pila
     * @param expresion una lista de elementos con n�meros u operadores
     * @return el resultado de la evaluaci�n de la expresi�n.
     */
    static int evaluarPostFija(List<String> expresion) {
        Stack<Integer> pila = new Stack<>();
        //Declaraci�n de las pilas
        Stack < String > entrada = new Stack < String > (); //Pila entrada
        Stack < String > operandos = new Stack < String > (); //Pila de operandos
        
       //A�adir post (array) a la Pila de entrada (E)
        for (int i = expresion.size() - 1; i >= 0; i--) {
        	entrada.push(expresion.get(i));
        }

        // TODO: Realiza la evaluaci�n de la expresi�n en formato postfijo   
        String operadores = "+-*/%";
        while (!entrada.isEmpty()) {
          if (operadores.contains("" + entrada.peek())) {
            operandos.push(evaluar(entrada.pop(), operandos.pop(), operandos.pop()) + "");
          } else {
            operandos.push(entrada.pop());
          }
        } 
        
        return new Integer(operandos.peek()).intValue();
    }
    
    private static int evaluar(String op, String n2, String n1) {
        int num1 = Integer.parseInt(n1);
        int num2 = Integer.parseInt(n2);
        if (op.equals("+")) return (num1 + num2);
        if (op.equals("-")) return (num1 - num2);
        if (op.equals("*")) return (num1 * num2);
        if (op.equals("/")) return (num1 / num2);
        if (op.equals("%")) return (num1 % num2);
        return 0;
      }

    /**
     * Programa principal
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("> ");
        String linea = teclado.nextLine();

        try {
            List<String> expresion = Token.dividir(linea);
            System.out.println(evaluarPostFija(expresion));
        }
        catch (Exception e) {
            System.err.printf("Error grave en la expresi�n: %s", e.getMessage());
        }

    }
}
