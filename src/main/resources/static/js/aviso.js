let operacion=0;
function eliminar(id,opcion) {
    console.log(id,opcion);
    let ruta="";
    let redirigir="";
    let registro="";
    switch (opcion) {
        case 1:
            registro="Categoria";
            ruta="/eliminarcategoria/";
            redirigir="/categoria";
            break;
        case 2:
            registro="Producto";
            ruta="/eliminarproducto/";
            redirigir="/producto";
            break;
        case 3:
            registro="Cultivo";
            ruta="/eliminarcultivo/";
            redirigir="/cultivo";
            break;
        case 4:
            operacion=4;
            registro="Usuario";
            ruta="/eliminar/";
            redirigir="/usuarios";
            break;
        case 5:
            registro="Dispositivo";
            ruta="/eliminardispositivo/";
            redirigir="/dispositivo";
            break;
        default:
            console.log('none');
    }


    if(operacion===4 && id===21){
        operacion=0;
        swal({
            title: "No puedes eliminar a un usuario administrador",
            text: "                      ",
            icon: "warning",
            buttons: false,
            timer:2000,
            dangerMode: true,
        });
        setTimeout(evitarEliminarUsuarioAdmin(),2000);
    }else{
        swal({
            title: "¿Desea Eliminar?",
            text: "¡Una vez eliminado no se prodrá restablecer!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((OK) => {
                if (OK) {
                    $.ajax({
                        url:ruta+id,
                        success: function(res) {
                            console.log(res);
                        },
                    });
                    swal(registro+" eliminado!", {
                        icon: "success",
                    })
                        .then((ok)=>{
                            if(ok){
                                window.location.href=redirigir;
                            }
                        });
                }
            });
    }

}

function evitarEliminarUsuarioAdmin(){
    window.location.href="/usuarios";
}
