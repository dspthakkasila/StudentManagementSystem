const marksValue =

document.getElementById(
"marksValue").value;

const ctx =

document.getElementById(
"marksChart");

new Chart(ctx, {

    type: 'bar',

    data: {

        labels: ['Marks'],

        datasets: [{

            label: 'Student Marks',

            data: [marksValue],

            borderWidth: 1
        }]
    },

    options: {

        responsive:true,

        scales: {

            y: {

                beginAtZero:true,

                max:100
            }
        }
    }
});