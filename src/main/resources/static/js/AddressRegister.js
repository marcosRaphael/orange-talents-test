
  var currentUrl = window.location.href;


    var splitURl = currentUrl.split("/");

    var userId = splitURl[4];

$(document).ready(function () {

$('#inputCep').on('input', function() {
    var cep = $('#inputCep')[0].value;
    cep.replace("-", "");
    if(cep.length == 9) {
        $.ajax({
                type: 'GET',
                url: "http://localhost:8080/viacep/consult/" + cep,
                success: function(data) {

                                $('#inputLogradouro')[0].value = data['logradouro'] ?? "";
                                $('#inputComplemento')[0].value = data['complemento'] ?? "";
                                $('#inputBairro')[0].value = data['bairro'] ?? "";
                }
            });
    }
});

 $('#inputCep').mask('00000-000');

    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/user/" + userId + "/address",
        success: function(data) {

                        $('#inputName')[0].value = data['name'];
                        $('#inputEmail')[0].value = data['email'];
                        $('#inputcpf')[0].value = data['cPF'];
                        $('#inputbirthDate')[0].value = data['birthDate'];


                         var i;

                         for (i = 0; i < data['addresses'].length; i++) {
                           $( "#addresses" ).append( ` <tr>
                                                            <td>${data['addresses'][i]['cep']}</td>
                                                            <td>${data['addresses'][i]['estado']}</td>
                                                            <td>${data['addresses'][i]['cidade']}</td>
                                                            <td>${data['addresses'][i]['bairro']}</td>
                                                            <td>${data['addresses'][i]['logradouro']}</td>
                                                            <td>${data['addresses'][i]['numero']}</td>
                                                            <td>${data['addresses'][i]['complemento']}</td>
                                                     </tr>`);
                         }

        },
        error: function(error) {
            alert("Usu??rio n??o encontrado");
            $('#inputName')[0].value = "";
            $('#inputEmail')[0].value = "";
            $('#inputcpf')[0].value = "";
            $('#inputbirthDate')[0].value = "";
        }
    });
 });

 function onFormClick() {

         <!-- Passando os valores dos campos para vari??veis-->

         var cep = $('#inputCep')[0].value;
         var estado = $('#inputEstado')[0].value;
         var cidade = $('#inputCidade')[0].value;
         var bairro = $('#inputBairro')[0].value;
         var numero = $('#inputNumero')[0].value;
         var logradouro = $('#inputLogradouro')[0].value;
         var complemento = $('#inputComplemento')[0].value;


         <!-- Validando dados localmente-->

         if(cep == null || cep.length != 9) {
             alert("Cep inv??lido");
         }
         else if(estado == null || estado.length < 1) {
             alert("Estado inv??lido");
         }
         else if(cidade == null || cidade.length < 1) {
            alert("Cidade Inv??lida");
         }
         else if(bairro == null || bairro.length < 1) {
            alert("Bairro Inv??lido");
         }
         else if(numero == null || numero.length < 1) {
            alert("N??mero Inv??lido");
         }
         else if(logradouro == null || logradouro.length < 1) {
            alert("Logradouro Inv??lido");
         }

         else {

             var data = {
                "bairro": bairro,
                "estado": estado,
                "cep": cep,
                "logradouro": logradouro,
                "cidade": cidade,
                "numero": numero,
                "complemento": complemento
             }

             $.ajax({
               type: 'POST',
               url: "http://localhost:8080/user/" + userId + "/address",
               contentType: 'application/json',
               data:  JSON.stringify(data),
               success: function(data) {
                 alert("Endere??o cadastrado com sucesso")
                 window.location = '/usuario/'+ userId +'/enderecos';
               },
               error: function(error) {
                 alert(error['responseJSON']['fieldsMessage'])
               }
             });
         }
     }