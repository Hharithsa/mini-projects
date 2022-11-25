const express = require("express");
const app = express();
const path = require('path');
const token = 'AAAAAAAAAAAAAAAAAAAAAJ2faAEAAAAAP0ErALCzXXYDonBACqmwsgaYwMA%3DH9IgCOicCp4jtbSbMzWYwMFkf0M6Ex1CEYojr8Mwy45irfP9Mv'
/* const data = require('./data.json'); */

app.use(express.static(path.join(__dirname, '/public')));
/* public is the seperate directory in the pwd
which contains things like css and js which 
you want to access from ur templates etc */
/* u can access the app.css in public from the
html file just by link href='/app.css' */

app.set('views', path.join(__dirname, '/views'))
app.set('view engine', 'ejs');
/* set changes the value of the attribute */

app.get('/gandu', (req, res) => {
    const { q, color } = req.query;
    res.render('home');
    console.log(q, color);
    console.log(req.params);
})

const gettweet = async () => {
    try {
        const config = { headers: { "Authorization": `Bearer ${token}` } };
        const tweetdata = await axios.get('https://api.twitter.com/2/tweets/1502369658862813185', config);
        console.log(tweetdata);
    }
    catch (e) {
        console.log('failed to get tweet', e);
    }
}

app.get('/twitter', (req, res) => {
    gettweet();
})
/* app.get and app.post for get and post requests */

app.use((req, res) => {
    console.log("im listening")
    res.send("response")
})
/* use runs for any req u recive, irrespective of 
the kind of request */

app.listen(3000, () => {
    console.log("server started")
})
/* listen is used to start server ig */