let r = 0;
let g = 0;
let b = 0;

const h1 = document.querySelector('h1');
const but = document.querySelector('button');
const body = document.querySelector('body');

const rand = () => {
    r = Math.floor(Math.random() * 255) + 1;
    g = Math.floor(Math.random() * 255) + 1;
    b = Math.floor(Math.random() * 255) + 1;
    if(r+g+b < 100){
        h1.style.color = "rgb(255,255,255)";
    }
    else
    {
        h1.style.color = "rgb(0,0,0)";
    }
    h1.innerText = `rgb(${r}, ${g}, ${b})`;
    body.style.backgroundColor = `rgb(${r}, ${g}, ${b})`;
}  

but.addEventListener('click', rand)

 