document.addEventListener("DOMContentLoaded", function () {
var splide = new Splide(".splide", {
        type: "fade",
        rewind: true,
        type: "loop",
        autoplay: true,
        interval:3000,
    });
splide.mount();
});