/**
 * @author Eduardo Luis González Santiago
 * @version 1.0
 *
 */
package com.test.dabarc.Test_Dabarc_ELGS.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainTestController {
    private  List<String> permuted;

    /**
     * Método qué se activa al ingresar a la ruta principal del servidor.
     * @param model modelo de datos qué se utiliza para renderizar en front-end
     * @return index nombre de la vista principal
     */
    @GetMapping("/")
    public String mainPermuted(Model model) {
        permuted = new ArrayList();
        permuted.add("000");
        model.addAttribute("textPermuted", "");
        model.addAttribute("numberpermutation", 0);
        model.addAttribute("permutedlist", permuted);
        return "index";
    }

    /**
     * Método qué se encarga de obtener la palabra a permutar e invoca al método permutar.
     * @param permute String a permutar, si no se proporciona ningun string se permuta la cadena por defecto dabarc
     * @param model modelo de datos a renderizar en la vista
     * @return index nombre de la vista principal.
     */
    @GetMapping("/permuted")
    public String permutedModel(@RequestParam(name = "permute", required = false, defaultValue = "dabarc")
                                String permute,Model model){
        permuted = new ArrayList();
        int length = permute.length();
        permuteString(permute,0, length -1);
        model.addAttribute("textPermuted", "para la palabra: " + permute);
        model.addAttribute("numberpermutation", permuted.size());
        model.addAttribute("permutedlist", permuted);
        return "index";
    }

    /**
     * Función recursiva que realiza la permutación de cada uno de los caracteres.
     * @param permuteString cadena  a permutar
     * @param start posición de inicion dentro del arreglo de caracteres
     * @param end posición final del arreglo de caracteres (se obtiene mediante el tamaño del arreglo de caracteres)
     */
    private  void permuteString(String permuteString, int start, int end){

        if (start == end) {
            permuted.add(permuteString);
        }
        else
        {
            for (int i = start; i <= end; i++)
            {
                permuteString = swapCharacters(permuteString,start,i);
                permuteString(permuteString, start+1, end);
                permuteString = swapCharacters(permuteString,start,i);
            }
        }

    }

    /**
     * Función que es invocada por el metodo permute String, la cual se encarga de intercambiar la posición
     * de dos caracteres dentro del arreglo de characteres proporcionado
     * @param permuteString cadena propocionada para realizar intercambio de caracteres
     * @param index posición de inicio del caracter a cambiar
     * @param newPosition posición por la cual se cambiará el caracter.
     * @return
     */
    private String swapCharacters(String permuteString, int index, int newPosition){
        char temporaryChar;
        char[] charArray = permuteString.toCharArray();
        temporaryChar = charArray[index] ;
        charArray[index] = charArray[newPosition];
        charArray[newPosition] = temporaryChar;
        return String.valueOf(charArray);
    }


}