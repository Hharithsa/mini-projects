const but = document.querySelector('button');
const textarea = document.querySelector('.one')
const form = document.querySelector('form')
const mainbody = document.querySelector('#contain')
let no = 0;

form.addEventListener('submit', async (evt) => {
    evt.preventDefault();
    let searchname = textarea.value;
    if (searchname) {
        removechild(no)
        getmovies(searchname);
        textarea.value = "";
    }
})

const getmovies = async (name) => {
    const options = {
        method: 'GET',
        url: 'https://imdb8.p.rapidapi.com/auto-complete',
        params: { q: `${name}` },
        headers: {
            'x-rapidapi-host': 'imdb8.p.rapidapi.com',
            'x-rapidapi-key': '7e56c0fa0cmsh04f390edda28357p119cdbjsn98f5409e1304'
        }
    };

    axios.request(options)

        .then(function (response) {
            console.log(response.data);
            let movies = response.data.d;
            console.log(movies)
            for (let mov of movies) {
                no += 1;
                let newmovie = {
                    imgsrc: mov.i.imageUrl ? mov.i.imageUrl : '',
                    names: mov.l,
                    type: mov.q,
                    rank: mov.rank,
                    year: mov.y,
                    url: `https://www.imdb.com/title/${mov.id}/?ref_=fn_al_tt_1`
                }
                appendcards(newmovie)
            }
        })

        .catch(function (error) {
            console.error(error);
        });
}

const appendcards = (movie) => {
    let div1 = document.createElement("DIV");
    div1.classList.add('cards');
    let img1 = document.createElement("IMG");
    img1.src = `${movie.imgsrc}`;
    let div2 = document.createElement("DIV");
    div2.classList.add('heading', 'a');
    div2.innerText = `${movie.names}`;
    let div3 = document.createElement("DIV");
    div3.classList.add('free', 'a');
    div3.innerText = `${movie.type}`;
    let div4 = document.createElement("DIV");
    div4.classList.add('rigid', 'a');
    div4.innerText = `IMDb rank: ${movie.rank}`;
    let div5 = document.createElement("DIV");
    div5.classList.add('rigid', 'a');
    div5.innerText = `Year of Release: ${movie.year}`;
    let a1 = document.createElement("A");
    a1.classList.add('rigid', 'a');
    a1.setAttribute('target', 'block');
    a1.href = `${movie.url}`;
    a1.innerHTML = 'Visit the IMDb website';
    mainbody.append(div1);
    div1.append(img1, div2, div3, div4, div5, a1);
}

const removechild = (j) => {
    for (let i = 0; i < j; i++) {
        mainbody.removeChild(mainbody.firstElementChild);
        no = 0;
    }
}

{/* <div class="card">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                    card's content.</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">An item</li>
                <li class="list-group-item">A second item</li>
            </ul>
            <div class="card-body">
                <a href="#" class="card-link">Card link</a>
            </div>
        </div> */}
