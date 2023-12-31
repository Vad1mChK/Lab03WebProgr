let canvas = null;
const WIDTH = 400;
const HEIGHT = 400;
const RADIUS = 120;
const DOT_RADIUS = 3;

const HIT_COLOR_FOR_MATCHING_R = "#94bc0e";
const MISS_COLOR_FOR_MATCHING_R = "#d6001e";
const HIT_COLOR_FOR_UNMATCHING_R = "#157d26";
const MISS_COLOR_FOR_UNMATCHING_R = "#7c021f";
const EPSILON = 1e-6;
let R = 1;

let shots = [];

function cleanCanvas() {
    const ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, WIDTH, HEIGHT);
}

function cleanAll() {
    cleanCanvas()
    shots = []
}

function realToDrawnX(x, r) {
    return WIDTH / 2 + RADIUS * x / r;
}

function realToDrawnY(y, r) {
    return HEIGHT / 2 - RADIUS * y / r;
}

function drawnToRealX(x, r) {
    return (x - WIDTH / 2) * r / RADIUS;
}

function drawnToRealY(y, r) {
    return -(y - HEIGHT / 2) * r / RADIUS;
}

function addShot(x, y, r, hit) {
    console.log(`Adding shot (x: ${x}, y: ${y}, r: ${r}, hit: ${hit})`)
    shots.push({x, y, r, hit})
    drawShot(x, y, r, hit)
}

function drawShot(x, y, r, hit) {
    const ctx = canvas.getContext('2d', {alpha: true});
    const xDrawn = realToDrawnX(x, R);
    const yDrawn = realToDrawnY(y, R);

    let match = Math.abs(r - R) <= EPSILON;
    let shotColor = hit ?
        (match ? HIT_COLOR_FOR_MATCHING_R : HIT_COLOR_FOR_UNMATCHING_R) :
        (match ? MISS_COLOR_FOR_MATCHING_R : MISS_COLOR_FOR_UNMATCHING_R);

    console.log(`Drawing shot (x: ${x}, y: ${y}, r: ${r}, R: ${R}, match: ${match}, hit: ${hit})`)
    ctx.beginPath();
    ctx.arc(xDrawn, yDrawn, DOT_RADIUS, 0, 2 * Math.PI, false);
    ctx.fillStyle = shotColor;
    ctx.fill();
}

function redrawLabels(r) {
    let labelsToUpdate = [
        {id: 'minusRX', value: -r},
        {id: 'minusHalfRX', value: -r / 2},
        {id: 'halfRX', value: r / 2},
        {id: 'rX', value: r},
        {id: 'minusRY', value: -r},
        {id: 'minusHalfRY', value: -r / 2},
        {id: 'halfRY', value: r / 2},
        {id: 'rY', value: r}
    ];

    labelsToUpdate.forEach(label => {
        let element = document.getElementById(label.id);
        if (element) {
            element.textContent = label.value;
        }
    });
}

function redrawCanvas(r) {
    R = r;
    console.log(`R changed to ${R}`)
    console.log(`Redrawing canvas with R = ${R}`)
    cleanCanvas()
    for (let shot of shots) {
        drawShot(shot.x, shot.y, shot.r, shot.hit)
    }
    redrawLabels(r)
}

function getShotPosition(event, r) {
    const drawnX = event.offsetX;
    const drawnY = event.offsetY;
    return {x: drawnToRealX(drawnX, r), y: drawnToRealY(drawnY, r)};
}

function sendShotFromCanvas(x, y) {
    document.getElementById("graph-x").value = x;
    document.getElementById("graph-y").value = y;
    document.getElementById("graph-submit").click();
}

function getR() {
    let r = null;
    document.getElementsByName("r").forEach((elem) => {
        if (elem.checked) {
            r = elem.value;
        }
    });
    return r;
}

function canvasMain() {
    canvas = document.getElementById("aim-top");
    R = getR();
    if (R === null || isNaN(R)) {
        R = 1;
    }

    document.getElementsByName("r").forEach((elem) => {
        elem.addEventListener("change", onRChange);
    });

    cleanCanvas();
    canvas.onclick = (e) => {
        const realCoords = getShotPosition(e, R);
        if (!isFinite(realCoords.x) || !isFinite(realCoords.y)) {
            return;
        }
        console.log(realCoords);
        sendShotFromCanvas(realCoords.x, realCoords.y);
    };
}

function reattachRListeners() {
    document.getElementsByName("r").forEach((elem) => {
        elem.removeEventListener("change", onRChange)
        elem.addEventListener("change", onRChange);
    })
}

function onRChange() {
    const newR = getR();
    if (newR !== null) {
        redrawCanvas(newR);
    }
}

function test() {
    console.log('only invoked from server')
}