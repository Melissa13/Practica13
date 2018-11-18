document.addEventListener('DOMContentLoaded', init);

function init(){
    if(navigator.geolocation){
        options ={
            enableHighAccuracy: true,
            timeout: 3000,      //30 segundos
            maximumAge: 36000   //1 hora
        }

        navigator.geolocation.getCurrentPosition(gotPos, posFail, options);
    }else{
        //no validado
    }
}
//var x = document.getElementById('output');

//function getlocation(){
//    if(navigator.geolocation){
//        navigator.geolocation.getCurrentPosition(gotPoss);
//    }else{
//        x.innerHTML="error con el navegador";
//    }
//}

function gotPos(position){
    var y= "AIzaSyAnpyy2mU1VR_zWhF58YOwxW0CvGidn2fI"
    //geolocalizacion.textContent = "(" + position.coords.latitude + ", "  + position.coords.longitude + ")";
    var locAPI="http://maps.googleapis.com/maps/api/geocode/json?latlng="+position.coords.latitude+","+position.coords.longitude+"&?key="+y;

    $.get({
        url: locAPI,
        success: function(data){
            console.log(data);
            document.getElementById("dato1").value = data.results[0].address_components[0].long_name;
            document.getElementById("dato2").value = data.results[0].address_components[1].long_name;
            document.getElementById("dato3").value = data.results[0].address_components[2].long_name;
            document.getElementById("dato4").value = position.coords.latitude
            document.getElementById("dato5").value = position.coords.longitude

        }
    })

}

function posFail(err){
    //err is a number
    errors = {
        1: 'Sin permiso para acceder a la localizacion',
        2: 'Imposible de determinar',
        3: 'TAgotado tiempo de espera'
    }
    document.querySelector('h1').textContent = errors[err];
}

var c=function (pos) {
    var lat=pos.coords.latitude,
        long= pos.coords.longitude,
        coords= lat+', '+long;

    document.getElementById('google_map').setAttribute('src','https://maps.google.com/?q=' +coords + '&output=embed')
}

document.getElementById('get_loc').onclick=function () {
    navigator.geolocation.getCurrentPosition(c);
    return false;
}