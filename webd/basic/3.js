function Color(r, g, b) {
    this.r = r;
    this.g = g;
    this.b = b;
    console.log(this);
}

/* const color = new Color(1, 2, 3); */

Color.prototype.a = function() {
    let { r, g, b } = this;
    console.log(r,g,b);
    console.log(this);
}