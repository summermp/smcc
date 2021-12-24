// let eliminar=document.getElementById("eliminar");
// let editar=document.getElementById("editar");
/*
function alerta(){
    let estado=false;
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: 'Estas segur@?',
        text: "No sera capaz de revertir!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Si, eliminar!',
        cancelButtonText: 'No, cancelar!',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            estado=true;
            console.log("estado ok: ",estado)
            swalWithBootstrapButtons.fire(
                'Eliminado!',
                'Se elimino el registro.',
                'success'
            )
        } else if (
            result.dismiss === Swal.DismissReason.cancel
        ) {
            estado=false;
            console.log("estado nope: ",estado)
            swalWithBootstrapButtons.fire(
                'Cancelado',
                'Su registro esta bien :)',
                'error'
            )
        }
    })
    console.log("Estado: ",estado);
}
*/

function eliminar(id) {
    console.log(id);
    swal({
        title: "Esta seguro de Eliminar?",
        text: "Una vez eliminado no se prodra restablecer!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((OK) => {
            if (OK) {
                $.ajax({
                    url:"/eliminarcategoria/"+id,
                    success: function(res) {
                        console.log(res);
                    },
                });
                swal("Poof! Registro eliminado!", {
                    icon: "success",
                })
                .then((ok)=>{
                    if(ok){
                        location.href="/categoria";
                    }
                });
            }
        });
}
