/**
 * Created by postgres on 24.05.2017.
 */
/*$(document).ready(function(){
var arrData=[];
    // code to read selected table row cell data (values).
    $("#myTable").on('click','.btnSelect',function(){
        // get the current row
        var currentRow=$(this).closest("tr");

        var col1=currentRow.find("td:eq(1)").text(); // get current row 1st TD value
        var col2=currentRow.find("td:eq(2)").text(); // get current row 2nd TD
        var col3=currentRow.find("td:eq(3)").text();
        var col4=currentRow.find("td:eq(4)").text();
        var col5=currentRow.find("td:eq(5)").text();
        var col6=currentRow.find("td:eq(6)").text();// get current row 3rd TD
        var data=col1+"\n"+col2+"\n"+col3+"\n"+col4+"\n"+col5+"\n"+col6;

        alert(data);
        var obj={};
        obj.col1_value=col1;
        obj.col2_value=col2;
        obj.col3_value=col3;
        obj.col4_value=col4;
        obj.col5_value=col5;
        obj.col6_value=col6;
        arrData.push(obj);
        sessionStorage.setItem('dannye',arrData);
    });
    console.log(arrData);
    $('#get').on('click',function () {
        var dannye = sessionStorage.getItem('dannye');
        alert(dannye);

    });
});

function testJS() {
    var b = document.getElementById('tittle').value,
        url = 'http://localhost:8080/goToProject?tittle=.....' + encodeURIComponent(b);

    document.location.href = url;
}
window.onload = function () {
    var url = document.location.href,
        params = url.split('?')[1].split('&'),
        data = {}, tmp;
    for (var i = 0, l = params.length; i < l; i++) {
        tmp = params[i].split('=');
        data[tmp[0]] = tmp[1];
    }
    document.getElementById('here').innerHTML = data.name;
}


/**     $(document).ready(function() {
            $('#prigl').change(
                function() {
                    $.getJSON('{getModer}', {
                        moderName : $(this).val(),
                        ajax : 'true'
                    }, function(data) {
                        var html = '{data.modFamily}';
                        html += '<br>';
                        $('#textfield_input').html(html);
                    });
                });
        });**/
/*$( "input" )
    .keyup(function() {
        var value = $( this ).val();
        $( "p" ).text( value );
    })
    .keyup();*/
/*function changeFunc() {
    var selectBox = document.getElementById("prigl");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    var selectTextField = document.getElementById("textfield_input");

    alert(selectedValue);
}*/

