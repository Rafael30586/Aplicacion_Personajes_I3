let selectObra = document.getElementById("selectObra");

function recargarPagina(){
    location.reload();
}

selectObra.addEventListener("change",recargarPagina);