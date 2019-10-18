
// Adjunta manejador Submit al Form
$( "#blogbusterform" ).submit(function( event ) {
 
  // Quitar al botón submit su función de enviar normalmente
  event.preventDefault();
 
  // Coge valores de los elementos de la página:
  var $form = $( this ),
  id = $()
    term = $form.find( "input[name='s']" ).val(),
    url = $form.attr( "action" );
 
  // Send the data using post
  var posting = $.post( url, { s: term } );
 
  // Put the results in a div
  posting.done(function( data ) {
    var content = $( data ).find( "#content" );
    $( "#result" ).empty().append( content );
  });
});