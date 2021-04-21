 function onFormClick() {

        <!-- Passando os valores dos campos para variáveis-->

        var name = $('#inputName')[0].value;
        var email = $('#inputEmail')[0].value;
        var cpf = $('#inputcpf')[0].value;
        var birthDate = $('#inputbirthDate')[0].value;

        <!-- Validando dados localmente-->

        if(name == null || name.length < 1) {
            alert("Nome inválido");
        }
        else if(!validateEmail(email)) {
            alert("Email inválido");
        }
        else if(!validateCPF(cpf)) {
            alert("Cpf Inválido");
        }
        else if(!validateBirthDate(birthDate)) {
            alert("Data de nascimento inválida");
        }
        else {

            var data = {
                "name": name,
                "email": email,
                "cPF" : cpf,
                "birthDate": birthDate
            }

            $.ajax({
              type: 'POST',
              url: "http://localhost:8080/user",
              contentType: 'application/json',
              data:  JSON.stringify(data),
              success: function(data) {
                alert("Usuário cadastrado com sucesso")
                window.location = '/usuario/'+ data +'/enderecos';
              },
              error: function(error) {
                alert(error['responseJSON']['fieldsMessage'])
              }
            });
        }
    }

    function validateEmail(email) {
        var re = /\S+@\S+\.\S+/;

        return re.test(email);
    }

     function validateCPF(cpf) {
        var re = /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/;

        return re.test(cpf);
     }

     function validateBirthDate(birthDate){
       var re = /(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d/;

       return re.test(birthDate);

     }




    $(document).ready(function () {
      $('#inputcpf').mask('000.000.000-00', {reverse: true});
      $('#inputbirthDate').mask('00/00/0000');
    });