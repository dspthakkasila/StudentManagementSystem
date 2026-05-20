function searchStudents() {

    let keyword =
        document.getElementById(
            "searchInput"
        ).value;

    let xhr =
        new XMLHttpRequest();

    xhr.open(
        "GET",
        "SearchStudentServlet?keyword="
        + keyword,
        true
    );

    xhr.onload = function() {

        if(this.status == 200) {

            document.getElementById(
                "studentTableBody"
            ).innerHTML =
                this.responseText;
        }
    };

    xhr.send();
}