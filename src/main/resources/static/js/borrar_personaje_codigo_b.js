let url = "localhost:8080/personajes/borrado?id=";
let idPersonaje = 0;
let urlCompleta;
let botonEnviar = document.getElementById("botonEnviar");
let seleccionPersonaje = document.getElementById("seleccion");

function saludar(){
    alert("Hola mundo");
}

function obtenerId(){
    idPersonaje = seleccionPersonaje.value;
    urlCompleta = url + idPersonaje;
    console.log(urlCompleta);
}

async function borrarPersonaje(){
    obtenerId();
    await fetch(urlCompleta,{
        method: 'DELETE',/*
        headers:{
            'Content-Type':'application/json'
        }*/
    }).then(alert("Personaje borrado correctamente"));
}

//botonEnviar.addEventListener("click",obtenerId);
botonEnviar.addEventListener("click",borrarPersonaje);