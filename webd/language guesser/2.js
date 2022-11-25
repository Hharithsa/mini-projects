const franc = require("franc");
const langs = require("langs");
const colors = require("colors")

let sent = process.argv[2]

let ans = franc(`${sent}`);
if (ans === 'und') {
    console.log("please try again".red)
}
else {
    console.log(langs.where("3", `${ans}`).name.green)
}


