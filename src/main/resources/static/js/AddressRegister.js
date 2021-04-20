
  var currentUrl = window.location.href;


    var splitURl = currentUrl.split("/");

    var userId = splitURl[4];

$(document).ready(function () {

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
            alert("Usuário não encontrado");
            $('#inputName')[0].value = "";
            $('#inputEmail')[0].value = "";
            $('#inputcpf')[0].value = "";
            $('#inputbirthDate')[0].value = "";
        }
    });
 });

 function onFormClick() {

         <!-- Passando os valores dos campos para variáveis-->

         var cep = $('#inputCep')[0].value;
         var estado = $('#inputEstado')[0].value;
         var cidade = $('#inputCidade')[0].value;
         var bairro = $('#inputBairro')[0].value;
         var numero = $('#inputNumero')[0].value;
         var logradouro = $('#inputLogradouro')[0].value;
         var complemento = $('#inputComplemento')[0].value;


         <!-- Validando dados localmente-->

         if(cep == null || cep.length != 9) {
             alert("Cep inválido");
         }
         else if(estado == null || estado.length < 1) {
             alert("Estado inválido");
         }
         else if(cidade == null || cidade.length < 1) {
            alert("Cidade Inválida");
         }
         else if(bairro == null || bairro.length < 1) {
            alert("Bairro Inválido");
         }
         else if(numero == null || numero.length < 1) {
            alert("Número Inválido");
         }
         else if(logradouro == null || logradouro.length < 1) {
            alert("Logradouro Inválido");
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
                 alert("Endereço cadastrado com sucesso")
                 window.location = '/usuario/'+ userId +'/enderecos';
               },
               error: function(error) {
                 alert(error['responseJSON']['fieldsMessage'])
               }
             });
         }
     }