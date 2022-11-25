const no = document.querySelector("#no");
const b1 = document.querySelector("#one");
const b2 = document.querySelector("#two");
const b3 = document.querySelector("#reset");
const s1 = document.querySelector("#first");
const s2 = document.querySelector("#second");
let max = 6;
let p1 = 0;
let p2 = 0;

no.addEventListener('input', function () {
    max = this.value;
})

b1.addEventListener('click', function () {
    p1 += 1;
    s1.innerText = p1;
    if (p1 >= max) {
        b1.setAttribute('disabled', true);
        b2.setAttribute('disabled', true);
        s1.style.backgroundColor = 'green';
        s2.style.backgroundColor = 'red';
    }
})

b2.addEventListener('click', function () {
    p2 += 1;
    s2.innerText = p2;
    if (p2 >= max) {
        b1.setAttribute('disabled', true);
        b2.setAttribute('disabled', true);
        s2.style.backgroundColor = 'green';
        s1.style.backgroundColor = 'red';
    }
})

b3.addEventListener('click', function () {
    p1 = 0;
    p2 = 0;
    s1.innerText = p1;
    s2.innerText = p2;
    b1.removeAttribute('disabled');
    b2.removeAttribute('disabled');
    s2.style.backgroundColor = 'white';
    s1.style.backgroundColor = 'white';
})

