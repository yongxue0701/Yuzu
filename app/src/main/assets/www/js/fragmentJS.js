
function onClick_Submit() {

    var ansQ1 = "empty";
    var ansQ2 = "empty";
    var counts = 0;
    var ansQ1NL = document.getElementsByName("q1");
    var ansQ2NL = document.getElementsByName("q2");

    for(var i = 0; i < ansQ1NL.length; i++) {
        if(ansQ1NL[i].checked) {
            ansQ1 = ansQ1NL[i].value;
            counts = counts + 1;
        }

        if(ansQ2NL[i].checked) {
            ansQ2 = ansQ1NL[i].value;
            counts = counts + 1;
        }
    }

    var answers = [ansQ1,ansQ2];
    FragmentQuiz.getQuizAnsFromWebView(answers,counts);
}

function displayResult() {

    var answer = FragmentQuizResults.returnQuizAnsToWebView();
    var answers = answer.split(",");

    document.getElementById("q1"+answers[0]).checked = true;
    document.getElementById("q2"+answers[1]).checked = true;

}