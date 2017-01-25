
    var p = document.getElementById("greeting");
    setInterval(function () {
        var color = getNumber(111111,999999);
        function getNumber(max,min) {
            return Math.floor(Math.random() * (max - min +1))+ min;
        }
            p.style.color = "#"+color;

    }, 2000);


