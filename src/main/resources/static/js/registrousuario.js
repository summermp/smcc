const form = document.querySelector("#form");
const btnenviar = document.querySelector("#enviar");
const nombre = document.querySelector("#nombre");
const apellido = document.querySelector("#apellido");
const fechanac = document.querySelector("#fechanac");
const correo = document.querySelector("#correo");
const celular = document.querySelector("#celular");

//form.addEventListener("submit ", handleSubmit);
//Evita mas de un espacio
function ignoreSpaces(e) {
  var ignoreChars = " \r\n" + String.fromCharCode(0);
  var lastkey;
  e = e || window.event;
  var char = String.fromCharCode(e.charCode);
  if (ignoreChars.indexOf(char) >= 0 && ignoreChars.indexOf(lastkey) >= 0) {
    lastkey = char;
    return false;
  } else {
    lastkey = char;
    return true;
  }
}

nombre.addEventListener("keypress", function (event) {
  nombre.value = nombre.value.replace(
    /(\s{2,})|[^a-zA-Z']/g,
    " "
  );
  nombre.value = nombre.value.replace(/^\s*/, "");
  console.log(nombre.value);
});
apellido.addEventListener("keypress", function (event) {
  apellido.value = apellido.value.replace(
    /(\s{2,})|[^a-zA-Z']/g,
    " "
  );
  apellido.value = apellido.value.replace(/^\s*/, "");
  console.log(apellido.value);
});
correo.addEventListener("keypress", function (event) {
  correo.value = correo.value.replace(
    /(\s{2,})|[^@.a-zA-Z']/g,
    ""
  );
  correo.value = correo.value.replace(/^\s*/, "");
  console.log(correo.value);
});
celular.addEventListener("keypress", function (event) {
   var pattern = /^[9][0-9]{0,8}$/;
   var value = celular.value;
   !pattern.test(value) && (celular.value = value = "");
   celular.addEventListener("input", function () {
     var currentValue = this.value;
     if (currentValue && !pattern.test(currentValue)) this.value = value;
     else value = currentValue;
   });
  console.log(celular.value);
  
});
/*
ESCUCHAR MULTIPLES EVENTOS
function mouseMoveHandler(event) {
  if (event.keyCode === 13) {
    console.log('enter');
  } else {
    console.log('other aciton');
  }
}
"keypress mouseenter".split(" ").forEach(function (e) {
  celular.addEventListener(e, mouseMoveHandler, false);
});
*/

// direccion.addEventListener("keypress", function (event) {
//   direccion.value = direccion.value.replace(
//     /(\s{2,})|[^a-zA-Z']/g,
//     " "
//   );
//   direccion.value = direccion.value.replace(/^\s*/, "");
//   console.log(direccion.value);
// });

clave.addEventListener("input", function (event) {
  console.log(clave.value);
});
claveverificar.addEventListener("change", function (event) {
  if (clave.value === claveverificar.value) {
    console.log("match");
  } else {
    console.log("doesn't match");
    claveverificar.style.borderColor = 'red';
    claveverificar.value="";
  }
  console.log(claveverificar.value);
});


fechanac.addEventListener("change", function () {
  console.log(fechanac.value);
});

// Execute a function when the user releases a key on the keyboard
form.addEventListener("keyup", function (event) {
  // Number 13 is the "Enter" key on the keyboard
  if (event.keyCode === 13) {
    console.log("youpres enter");
    ignoreSpaces(this);
    // Cancel the default action, if needed
    event.preventDefault();
    if (nombreCultivo.value === "") {
      nombreCultivo.focus();
    } else {
      if (nombreCultivo.value && nombreCultivo.value > 2) {
        btnenviar.click();
      }
    }
  }
});

function mySubmit(e) {
  e.preventDefault();
  try {
    validarEntrada(e.value);
  } catch (e) {
    throw new Error(e.message);
  }
  return false;
}

window.addEventListener("keydown", function (event) {
  if (event.keyCode === 13) {
    if (nombreCultivo.value) {
      console.log("enter page");
      // console.log(nombreCultivo.value);
    }
  }
});
// http://jsfiddle.net/qvxg6ok4/14/
//https://www.webtrickshome.com/forum/how-to-display-uploaded-image-in-html-using-javascript


function validarEntrada(e) {
  if (e.value.length === 0) {
    e.focus();
    console.log("entrada is empty");
    e.style.borderColor = "red";
  } else if (e.value.length < 3) {
    e.style.borderColor = "red";
    console.log("entrada is coming...");
    e.focus();
  } else {
    console.log("entrada is ok");
    handleSubmit(e);
  }
}




