let students = []
$.get("http://localhost:8080/api/students", function (data) {
    students = data
    updateContent(students)
});


function handleDelete(id) {
    $.ajax({
        url: `http://localhost:8080/api/students/${id}`,
        type: 'DELETE',
        success: function (resp) {
            for (let i in students) {
                if (students[i].id === id) {
                    students.splice(i, 1)
                    updateContent(students)
                }
            }
        }
    });
}

function validateInput() {
    let fields = ["#first_name", "#last_name", "#fathers_name", "#birth_date", "#group_name"]
    for (let i in fields) {
        if ($(fields[i]).val() === "") {
            return false
        }
    }
    return true;
}


function formattedString(obj) {
    str = `<tr>
                    <td><button onclick="handleDelete(${obj.id})" class="btn btn-danger">Удалить</button></td>
                    <td>${obj.id}</td>
                    <td>${obj.first_name}</td>
                    <td>${obj.last_name}</td>
                    <td>${obj.fathers_name}</td>
                    <td>${obj.birth_date}</td>
                    <td>${obj.group_name}</td>
               </tr>`
    return str
}

function prepareContent(data) {
    prepared = []
    for (let i = 0; i < data.length; i++) {
        prepared.push(formattedString(data[i]))
    }
    return prepared
}

function updateContent(content) {
    let formatted = prepareContent(content)
    $('#students_table_content').html(formatted)
}

$('#createStudent').click(e => {
    if (!validateInput()) {
        alert("Заполните все поля.")
        return
    }
    let stud = {
        first_name: $("#first_name").val(),
        last_name: $("#last_name").val(),
        fathers_name: $("#fathers_name").val(),
        birth_date: $("#birth_date").val(),
        group_name: $("#group_name").val()
    }
    $.post("http://localhost:8080/api/students", stud, function (data) {
        stud.id = data;
        students.push(stud)
        updateContent(students)
    });
})