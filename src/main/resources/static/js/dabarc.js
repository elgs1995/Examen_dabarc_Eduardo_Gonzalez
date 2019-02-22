/**
 * @author Eduardo Luis González Santiago
 * @version 1.0
 * Script personalizado para la validación del texto de entrada.
 */

/**
 * Inicialización de DataTables.
 */
$(document).ready( function () {
    $('#permutationsTable').DataTable({
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
        }
    });
} );

/**
 * Función que se encarga de validar que se cumplan las siguientes reglas
 * 1.- El campo de texto no debe de estar vacio
 * 2.- El campo deberá de tener por lo menos 3 caracteres para realizar la permutación
 * 3.- Los caracteres deberán de ser diferentes.
 */
$('#btnPermute').click(function(){
    if($.trim($('#inputPermutations').val()) == ''){
        swal("¡Error!", "EL campo a permutar no puede estar vacio", "error");
        return;
    }
    if($('#inputPermutations').val().length < 3) {
        swal("¡Error!", "Debe de tener por lo menos 3 caracteres", "error");
        return;
    }
    if(checkCharInput($('#inputPermutations').val())){
        swal("¡Error!", "Debe de tener caracteres diferentes", "error");
        return;
    }

    $('form').submit();

});

/**
 * Función que se encarga de remover espacios en blanco dejados por el usuario al escribir una cadena a permutar.
 */
$('#inputPermutations').keyup(function() {
    $(this).val($(this).val().replace(/ +?/g, ''));
});

/**
 * Función qué se encarga de validar qué los caracteres ingresados sean diferentes
 * @returns {boolean}
 */
function checkCharInput(input) {
    return input.split('').every(char => char === input[0]);
}