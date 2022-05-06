$(document).ready(function() {
    $.ajax({
        url: "/api/country"
    }).then(function(response) {
        //console.log(response)
        var len = response.length;

        $("#country_dropdown").empty();
        for (var i = 0; i < len; i++) {
            var id = response[i]['iso2'];
            var name = response[i]['name'];
            $("#country_dropdown").append("<option value='" + id + "'>" + name + "</option>");
        }
        $('#country_dropdown option[value="US"]').attr('selected', true);

        updateCityDropdown();

    });
});

function getCurrentWeather() {
    country = $("#country_dropdown").find(":selected").val();
    city = $("#city_dropdown").find(":selected").text();
    $.ajax({
        url: "/api/currentWeather/" + city + "," + country
    }).then(function(response) {
        console.log(response)
        if (response.city == "Not Found") {
            $("#weather").text("NA")
            $("#temp").text("NA")
            $("#weather_box").css("display", "inline");
        } else {

            $("#weather").text(response.description)
            $("#temp").text(response.temp)
            $("#weather_box").css("display", "inline");
        }

    });
}

function updateCityDropdown() {

    selected_country = $("#country_dropdown").find(":selected").val();

    $(document).ready(function() {
        $.ajax({
            url: "/api/citybycountry/" + selected_country
        }).then(function(response) {
            var len = response.length;
            $("#city_dropdown").empty();
            if (len == 0) {
                $("#city_dropdown").append("<option value='NA'>No City Found</option>");

                $("#weather_box").css("display", "none");
            } else {

                for (var i = 0; i < len; i++) {

                    var id = response[i]['iso2'];
                    var name = response[i]['name'];
                    $("#city_dropdown").append("<option value='" + id + "'>" + name + "</option>");
                }
            }
        });
    });

}