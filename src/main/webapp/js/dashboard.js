const ctx =
document.getElementById(
"dashboardChart");

new Chart(ctx, {

    type:'pie',

    data:{

        labels:courseLabels,

        datasets:[{

            label:'Students Per Course',

            data:courseCounts,

            backgroundColor:[

                '#38bdf8',
                '#0ea5e9',
                '#0284c7',
                '#7c3aed',
                '#14b8a6',
                '#f97316',
                '#ef4444'

            ],

            borderWidth:0
        }]
    },

    options:{

        responsive:true,

        maintainAspectRatio:false,

        plugins:{

            legend:{

                position:'bottom',

                labels:{

                    color:'white',

                    padding:20,

                    font:{

                        size:14
                    }
                }
            }
        }
    }
});