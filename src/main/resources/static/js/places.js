{/* <input id="autocomplete" placeholder="Enter your address" type="text" class="form-control">
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAIXJDpu_1yWdgqw-ZqFEgucp54D8HN71k&libraries=places&callback=initAutocomplete" async defer></script>  */}

//GEOBIT
// df245ee9ea61636a1120c43f86e0b324512f6e68
// 3e046ebaf08b0a5e43e450e893db1de9288e7526
const headers = {
  Accept: "application/json",
  "X-Geobit-Key": "3e046ebaf08b0a5e43e450e893db1de9288e7526",
};

fetch("https://api.geobit.dev/v1/autocomplete?query=string", {
  method: "GET",

  headers: headers,
})
  .then(function (res) {
    return res.json();
  })
  .then(function (body) {
    console.log(body);
  });



let placeSearch, autocomplete;
let countryRestriction = { country: "ar" };
function initAutocomplete() {
  // Create the autocomplete object, restricting the search to geographical
  // location types.
  autocomplete = new google.maps.places.Autocomplete(
    /** @type {!HTMLInputElement} */
    document.getElementById("autocomplete"),
    {
      types: ["geocode"],
      componentRestrictions: countryRestriction,
    }
  );
  // When the user selects an address from the dropdown, populate the address
  // fields in the form.
  autocomplete.addListener("place_changed", fillInAddress);
}

function fillInAddress() {
  // Get the place details from the autocomplete object.
  autocomplete.setFields(["geometry"]);
  var location = autocomplete.getPlace().geometry.location;
  console.log(location);
  //  document.getElementById("lat").value = location.lat();
  //  document.getElementById("long").value = location.lng();
}
