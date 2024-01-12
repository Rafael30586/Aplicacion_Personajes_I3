let selectPersonaje = document.getElementById("selectPersonaje");

function recargarPagina(){
    location.reload();
}

selectPersonaje.addEventListener("change",recargarPagina);