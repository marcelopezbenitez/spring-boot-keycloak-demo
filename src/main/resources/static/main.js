let cantidadTotal=0;
let cantidadUsada=0;
let cantidadDisponible=0;
let myModal1;
let myModal2;
window.onload = function(){
    cantidadTotal = document.getElementById("cant-comprada").value;
    cantidadUsada = document.getElementById("cant-usada").value;
    cantidadDisponible = cantidadTotal - cantidadUsada;
    document.getElementById("cantidad-selec").value = 1;
    myModal1 = new bootstrap.Modal('#exampleModal', {
        keyboard: false,
        focus: true
    });
    myModal2 = new bootstrap.Modal('#exampleModalToggle2', {
        keyboard: false,
        focus: true
    });

};

function remove_value() {

    let valorActual = document.getElementById("cantidad-selec").value;
    if(parseInt(valorActual)>1){
        valorActual = valorActual-1;
        document.getElementById("cantidad-selec").value = valorActual;
    }
}

function add_value(){
    let valorActual = parseInt(document.getElementById("cantidad-selec").value);
    if(valorActual<cantidadDisponible){
        valorActual = valorActual+1;
        document.getElementById("cantidad-selec").value = valorActual;
    }
}

function paso_previo() {
    var valorSelec = parseInt(document.getElementById("cantidad-selec").value);
    if(valorSelec===0){

    }
    document.getElementById("modal-body").textContent = "Esta apunto de canjear "+valorSelec+" entradas.";
    myModal1.show();
}


function canjear_entradas(){
    let cantidad = parseInt(document.getElementById("cantidad-selec").value);
    let nroSocio = document.getElementById("nro-socio").value;
    let url = "/entradas/canje?cantidad="+cantidad+"&socio="+nroSocio;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", url, true);
    xhttp.setRequestHeader("X-CSRF-TOKEN", document.getElementById('_csrf').content);
    xhttp.send();

    xhttp.onload = function() {
        if (xhttp.status !== 200) { // analiza el estado HTTP de la respuesta
            alert(`Error ${xhttp.status}: ${xhttp.statusText}`); // ej. 404: No encontrado
        } else { // muestra el resultado
            myModal1.hide();
            myModal2.show();
            let res = JSON.parse(xhttp.response);
            if(res.code==="0"){
                //todo salio bien
                document.getElementById("modal-header-2").setAttribute('class', 'modal-header modal-success');
                document.getElementById('modal-header-2').textContent = "Cambio realizado con éxito."
                document.getElementById('modal-body-2').textContent = res.message;
            }else{
                document.getElementById("modal-header-2").setAttribute('class', 'modal-header modal-error');
                document.getElementById('modal-header-2').textContent = "Ocurrio un error."
                document.getElementById('modal-body-2').textContent = res.message;
            }

        }
    };
    xhttp.onerror = function() { // solo se activa si la solicitud no se puede realizar
        alert(`Error de red`);
    };
}

function volver_atras() {
    window.location.href= '/entradas';
}

function refresh(){
    window.location.reload();
}


