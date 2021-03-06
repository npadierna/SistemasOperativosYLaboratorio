package co.edu.udea.os.ahorcado.domain.util;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public class ConverterUtillity {

    public static final String CORRECT_OPERATION = "correct";

    public static String convertName(String text) {
        String name = text.toUpperCase().trim();

        name = name.replace('Á', 'A');
        name = name.replace('É', 'E');
        name = name.replace('Í', 'I');
        name = name.replace('Ó', 'O');
        name = name.replace('Ú', 'U');

        return (name);
    }
}
