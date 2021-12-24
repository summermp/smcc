$(document).ready(function () {
            $('#mitabla').DataTable({
                language: {
                    processing: "Tratamiento en curso...",
                    search: "Buscar&nbsp;:",
                    lengthMenu: "Agrupar de_MENU_",
                    info: "Mostrando de _START_ al _END_ de un total de _TOTAL_ ",
                    infoEmpty: "No existen datos.",
                    infoFiltered: "(filtrado de _MAX_ elementos en total)",
                    infoPostFix: "",
                    loadingRecords: "Cargando...",
                    zeroRecords: "No se encontraron datos con tu busqueda",
                    emptyTable: "No hay datos disponibles en la tabla.",
                    paginate: {
                        first: "Primero",
                        previous: "Anterior",
                        next: "Siguiente",
                        last: "Ultimo"
                    },
                    aria: {
                        sortAscending: ": active para ordenar la columna en orden ascendente",
                        sortDescending: ": active para ordenar la columna en orden descendente"
                    }
                },
                scrollY: 400,
                scroller:    true,
                // "scrollX": true,
                // "scrollY": 200,
                // "scrollX": true,
                lengthMenu: [ [10, 25, -1], [10, 25, "All"] ],
            });
});