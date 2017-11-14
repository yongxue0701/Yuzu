
function onClick_Submit_broadcast() {

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
            ansQ2 = ansQ2NL[i].value;
            counts = counts + 1;
        }
    }

    var answers = [ansQ1,ansQ2];
    BroadcastQuiz.getQuizAnsFromWebView(answers,counts);
}

function displayResult_broadcast() {

    var answer = BroadcastQuizResults.returnQuizAnsToWebView();
    var answers = answer.split(",");

    var correctAns = ["c","a"];

    var para_q1 = document.getElementById("q1_explain_p");
    var para_q2 = document.getElementById("q2_explain_p");

    var img_q1 = document.getElementById("q1_explain_img");
    var img_q2 = document.getElementById("q2_explain_img");

    var points = 0;
    for(var j = 0;j < correctAns.length;j++) {
        if(answers[j] == correctAns[j]) {
            points = points + 1;
        }
    }

    if (points == 1 || points == 0) {
        document.getElementById("quiz_results").innerHTML = "Your Score: " + points + "/2 point";
    } else if (points > 1) {
        document.getElementById("quiz_results").innerHTML = "Your Score: " + points + "/2 points";
    }

    for(var i = 1;i <= correctAns.length; i++) {
        document.getElementById("q"+i+"a").disabled = true;
        document.getElementById("q"+i+"b").disabled = true;
        document.getElementById("q"+i+"c").disabled = true;
        document.getElementById("q"+i+"d").disabled = true;
    }

    if(answers[0] != "empty") {
        document.getElementById("q1"+answers[0]).checked = true;
        if(answers[0] == correctAns[0]) {
            para_q1.innerHTML = "You are correct!";
            img_q1.src = "file:///android_res/drawable/correct_ans.png";
        } else {
            para_q1.innerHTML = "Broadcast receiver is a component of android which responds to "
            + "system wide broadcast announcements and acts like a gateway between outside world and your application.";
            img_q1.src = "file:///android_res/drawable/wrong_ans.png";
        }
    } else {
        para_q1.innerHTML = "You haven't answered this question!";
        img_q1.src = "file:///android_res/drawable/wrong_ans.png";
    }

    if(answers[1] != "empty") {
        document.getElementById("q2"+answers[1]).checked = true;
        if(answers[1] == correctAns[1]) {
            para_q2.innerHTML = "You are correct!";
            img_q2.src = "file:///android_res/drawable/correct_ans.png";
        } else {
            para_q2.innerHTML = "There is only one function in broad cast receiver, that is onReceive(). "
            + "Broadcast receiver's life will start before calling onReceive() method, and once control "
            + "comes out of onReceive() method, then it will be killed.";
            img_q2.src = "file:///android_res/drawable/wrong_ans.png";
        }
    } else {
        para_q2.innerHTML = "You haven't answered this question!";
        img_q2.src = "file:///android_res/drawable/wrong_ans.png";
    }
}