var API_URL = "http://127.0.0.1:8080/customapp/api"

var team = {};
var teamObject = {};

$(document).ready(function(){
    getTeam();
    loadSchedules();
    populateVacations(); 

})



function loadSchedules() {
    $.ajax({
        url: API_URL + '/scheduler/all',
        async: true,
        success: populateSchedules,
        error: errorCallBack
    });
};



function populateSchedules (data) {

    $.each(data, function(i, schedule){

        var fullWorkers = "";
        var supportWorkers = "";

       
        var supportArray = schedule.support.split(",");
        
        
        var fullArray = schedule.full.split(",");
        fullArray.forEach(element => {
            if(fullWorkers != "") {
                fullWorkers = fullWorkers + ", "; 
                fullWorkers = fullWorkers + team[element]
            } else {
                fullWorkers = fullWorkers + team[element]
            }
        });

        supportArray.forEach(element => {
            if(supportWorkers != "") {
                supportWorkers = supportWorkers + ", ";
                supportWorkers = supportWorkers + team[element]
            } else {
                supportWorkers = supportWorkers + team[element]
            }
        });

            $("#schedulesTable").append(
                '<tr>'+
                    '<th scope="row">' + schedule.date + '</th>'+
                    '<td>' + fullWorkers + '</td>'+
                    '<td>' + supportWorkers + '</td>'+
                  '</tr>'
            )
    })
};

function getTeam() {
    $.ajax({
        url: API_URL + '/worker/all',
        async: false,
        success: populateTeam,
        error: errorCallBack
    })
};

function populateTeam(data) {
    $.each(data, function(i, worker) {


        team[worker.qxNumber] = worker.firstName + " " + worker.lastName; 
        teamObject[worker.qxNumber] = worker;


        if(worker.exp) {
        $("#Med").append(
            '<tr>'+
                '<th scope="row">' + worker.firstName + " " + worker.lastName + '</th>'+
                '<td>' + worker.rotations +'</td>'+
                '<td>' + worker.supportRotations + '</td>'+
              '</tr>'
        )
        }else if(!worker.exp) {
            $("#Led").append(
                '<tr>'+
                    '<th scope="row">' + worker.firstName + " " + worker.lastName + '</th>'+
                    '<td>' + worker.rotations +'</td>'+
                    '<td>' + worker.supportRotations + '</td>'+
                  '</tr>'
            )
        }

    })
};


function populateVacations() {
    
    var baseColor = "000000";

    for (const key in team) {

        var color = baseColor;

        if(teamObject[key].vacations) {
            color = "yellow"
        } else if (teamObject[key].inactive) {
            color = "black"
        }

        $("#userDropDown").append(
            '<li><a id="'+key+'" class="dropdown-item" href="#" onclick="vacButton(id)" style="color:'+ color + '">' + teamObject[key].firstName + " " + teamObject[key].lastName + '</a></li>'
        )
    }

}
    



function errorCallBack(request, status, error) {
    console.log('fail');
};
    



var vacButton = function(key) {

  

        $.ajax ({
            type: "PUT",
            url: API_URL + "/worker/vacation/" + key,
            async: true,
            success: location.reload(),
            error: errorCallBack
        })
    
}
