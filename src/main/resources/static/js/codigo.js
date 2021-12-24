const dni = document.querySelector("#dni");

dni.addEventListener("keypress", function (event) {
    var pattern = /^[0-9][0-9]{0,7}$/;
    var value = dni.value;
    !pattern.test(value) && (dni.value = value = "");
    dni.addEventListener("input", function () {
        var currentValue = this.value;
        if (currentValue && !pattern.test(currentValue)) this.value = value;
        else value = currentValue;
    });
    console.log(dni.value);

});