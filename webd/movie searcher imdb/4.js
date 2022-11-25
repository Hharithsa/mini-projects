const but = document.querySelector('button');
const textarea = document.querySelector('.one')
const form = document.querySelector('form')
const mainbody = document.querySelector('#contain')
let no = 0;
const token = 'AAAAAAAAAAAAAAAAAAAAAJ2faAEAAAAAP0ErALCzXXYDonBACqmwsgaYwMA%3DH9IgCOicCp4jtbSbMzWYwMFkf0M6Ex1CEYojr8Mwy45irfP9Mv'

form.addEventListener('submit', async (evt) => {
    evt.preventDefault();
    gettweet();
})

axios.interceptors.request.use(
    config => {
        config.headers.authorization = `Bearer ${token}`;
        return config;
    },
    error => {
        return Promise.reject(error);
    }
)

/* form.addEventListener('submit', async (evt) => {
    evt.preventDefault();
    let searchname = textarea.value;
    if (searchname) {
        removechild(no)
        getmovies(searchname);
        textarea.value = "";
    }
}) */

const gettweet = async () => {
    try {
        /* const config = { headers: { "Authorization": `Bearer ${token}` } } */
        const tweetdata = await axios.get('https://api.twitter.com/2/tweets/1502369658862813185');
        console.log(tweetdata);
    }
    catch (e) {
        console.log('failed to get tweet', e);
    }
}

const getmovies = async (name) => {
    try {
        const alldata = await axios.get(`https://api.tvmaze.com/search/shows?q=${name}`);
        let movies = alldata.data;
        console.log(movies)
        for (let mov of movies) {
            no += 1;
            let newmovie = {
                imgsrc: mov.show.image ? mov.show.image.medium : '',
                names: mov.show.name,
                desc: mov.show.summary,
                rating: mov.show.rating.average ? mov.show.rating.average : "Not known",
                status: mov.show.status,
                url: mov.show.url
            }
            appendcards(newmovie)
        }
    } catch (e) {
        console.log('error', e)
    }
}
{/* <div class="cards extra">1
            <img src="https://www.industrialempathy.com/img/remote/ZiClJf-1920w.jpg" alt="">1
            <div class="heading a">2
                heading
            </div>
            <div class="free a">3
                desc
            </div>
            <div class="rigid a">4
                rating
            </div>
            <div class="rigid a">5
                running
            </div>
            <a href="https://www.google.co.in/" class="rigid a" target="block">1
                link
            </a>
        </div> */}
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
    div3.innerText = `${movie.desc}`;
    let div4 = document.createElement("DIV");
    div4.classList.add('rigid', 'a');
    div4.innerText = `IMDb rank: ${movie.rating}`;
    let div5 = document.createElement("DIV");
    div5.classList.add('rigid', 'a');
    div5.innerText = `Year of Release: ${movie.status}`;
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

