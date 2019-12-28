function solution() {
    var answers = [1,2,3,4,5];
    var answer = [];
    var p1 = [1, 2, 3, 4, 5];
    var p1Count = 0;
    for(let i = 5 ; i < 10000 ; i++) {
        p1[i] = p1[i%5];
    }
    
    var p2 = [2, 1, 2, 3, 2, 4, 2, 5];
    var p2Count = 0;
    for(let i = 8 ; i < 10000 ; i++) {
        p2[i] = p2[i%8];
    }

    var p3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5];
    var p3Count = 0;
    for(let i = 10 ; i < 10000 ; i++) {
        p3[i] = p3[i%10];
    }
    
    for(let i=0 ; i<10000 ; i++) {
        if(answers[i] === p1[i]) p1Count++;
        if(answers[i] === p2[i]) p2Count++;
        if(answers[i] === p3[i]) p3Count++;
    }
    
    if(p1Count > p2Count) {
        if(p1Count > p3Count) {
            answer = [1];
        } else if(p1Count == p3Count) {
            answer = [1.3];
        } else if(p1Count < p3Count) {
            answer = [3];
        }
    } else if(p1Count == p2Count) {
         if(p1Count > p3Count) {
            answer = [1,2];
        } else if(p1Count == p3Count) {
            answer = [1.2,3];
        } else if(p1Count < p3Count) {
            answer = [3];
        }
    } else if(p1Count < p2Count) {
         if(p2Count > p3Count) {
            answer = [2];
        } else if(p2Count == p3Count) {
            answer = [2,3];
        } else if(p2Count < p3Count) {
            answer = [3];
        }
    }
    console.log(answer);
}