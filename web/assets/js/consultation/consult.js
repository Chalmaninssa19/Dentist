//Template
function getEtatDentTemplate(dent, etat) {
    return `
         <tr class="etatDent">
            <td>`+dent+`</td>
            <td>`+etat+`</td>
            <td>
                <a type="button" onclick="deleteEtatDent(this)" class="text-danger"><i class="glyphicon glyphicon-remove"></i></a>
            </td>
        </tr>
    `;
}


// fonction pour ajouter une nouvelle question
function addNewEtatDent() {
    var dentValue = $('#dentInput').val();
    var etatDentValue = $('#etatDentInput').val();
    
    // envoyer une ajax vers le controller
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/dentiste/AddEtatDent',
        data: {
            dent: dentValue,
            etatDent: etatDentValue
        },
        dataType: 'text',
        success: function (response) {
            var responseJson = JSON.parse(response);
            if(responseJson.error != null) {
                  console.log(responseJson.error);
            } else {
                console.log(responseJson);
                      
                // add template to the quiz container
                var etatDentList = $('#etatDentList');
                var etatDentTemplate = $(getEtatDentTemplate(responseJson.dent, responseJson.etatDent));
                etatDentList.append(etatDentTemplate);
                etatDentTemplate.attr('id', responseJson.dent);        // Redonner l'ID aux nouvelles éléments
            }
        },
        error: function (response) {
            console.log("ERREUR , voici la reponse");
            console.log("TEXT : " + response);
            var jsonResponse = JSON.parse(response);
            console.log("JSON : " + jsonResponse);
        }
    });
}


// fonction pour ajouter une nouvelle question
function addNewEtatDent2() {
    var dentValue = $('#dentInput').val();
    var etatDentValue = $('#etatDentInput').val();
    
    // envoyer une ajax vers le controller
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/dentiste/InsertConsultation2',
        data: {
            dent: dentValue,
            etatDent: etatDentValue
        },
        dataType: 'text',
        success: function (response) {
            window.location.href = "./InsertConsultation2";
        },
        error: function (response) {
            console.log("ERREUR , voici la reponse");
            console.log("TEXT : " + response);
            var jsonResponse = JSON.parse(response);
            console.log("JSON : " + jsonResponse);
        }
    });
}

// fonction pour supprimé un etat dent ajoute
function deleteEtatDent(bouton) {
    var etatDent = bouton.closest('.etatDent');
    var id = etatDent.id;
    console.log(id);
    // Ensuite supprimé du session
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/dentiste/DeleteEtatDent',
        data: {
            dent: id
        },
        success: function (reponse) {
            etatDent.remove();
        },
        error: function () {
            alert("Une erreur est survenue lors du suppression !");
        }
    });
}