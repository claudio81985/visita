document.addEventListener("DOMContentLoaded", function () {
    const searchBar = document.getElementById("search-bar");
    const searchInput = searchBar.querySelector("input");
    const tableRows = document.querySelectorAll("#tablaHome tbody tr");
    const filasPorPagina = 8;
    let paginaActual = 1;

    searchInput.addEventListener("input", function () {
        const searchTerm = this.value.trim().toLowerCase();
        mostrarFilasConFiltro(searchTerm);
    });

    function mostrarFilasConFiltro(searchTerm) {
        tableRows.forEach(function (row) {
            const internoName = row.cells[1].innerText.toLowerCase();
            const internoDni = row.cells[2].innerText.toLowerCase();
            const internoModulo = row.cells[3].innerText.toLowerCase();
            const showRow = searchTerm.length >= 3 && (internoName.includes(searchTerm) || internoDni.includes(searchTerm) || internoModulo.includes(searchTerm));
            row.style.display = showRow ? "" : "none";
        });

        if (searchTerm === "") {
            irAPagina(paginaActual); 
        }
    }

    function irAPagina(pagina) {
        if (pagina < 1) {
            pagina = 1;
        } else if (pagina > totalPaginas) {
            pagina = totalPaginas;
        }
        paginaActual = pagina;
        mostrarFilasPaginadas(pagina);
    }

    function mostrarFilasPaginadas(pagina) {
        const inicio = (pagina - 1) * filasPorPagina;
        const fin = inicio + filasPorPagina;
        tableRows.forEach(function (row, index) {
            if (index >= inicio && index < fin) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        });
        document.getElementById('paginaActual').textContent = pagina;
    }

    // Resto de tu código para la paginación
    const tabla = document.getElementById('tablaHome');
    const totalFilas = tabla.getElementsByTagName('tbody')[0].getElementsByTagName('tr').length;
    const totalPaginas = Math.ceil(totalFilas / filasPorPagina);

    mostrarFilasPaginadas(paginaActual);

    document.getElementById('anterior').addEventListener('click', function () {
        irAPagina(paginaActual - 1);
    });

    document.getElementById('siguiente').addEventListener('click', function () {
        irAPagina(paginaActual + 1);
    });
});

document.getElementById('siguiente').addEventListener('click', function () {
    irAPagina(paginaActual + 1);
});
