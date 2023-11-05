let canvas = null;
const WIDTH = 400;
const HEIGHT = 400;
const RADIUS = 120;
const DOT_RADIUS = 3;

const HIT_COLOR_FOR_MATCHING_R = "#94bc0e";
const MISS_COLOR_FOR_MATCHING_R = "#d6001e";

let shots = [];

function cleanCanvas() {
    const ctx = canvas.getContext('2d', { alpha: true });
    ctx.clearRect(0, 0, WIDTH, HEIGHT);
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

function drawShot(x, y, r, hit) {
    const ctx = canvas.getContext('2d', { alpha: true });
    const xDrawn = realToDrawnX(x, r);
    const yDrawn = realToDrawnY(y, r);
    shots.push({x, y, r, hit})

    let shotColor = hit ? HIT_COLOR_FOR_MATCHING_R : MISS_COLOR_FOR_MATCHING_R;

    ctx.beginPath();
    ctx.arc(xDrawn, yDrawn, DOT_RADIUS, 0, 2 * Math.PI, false);
    ctx.fillStyle = shotColor;
    ctx.fill();
}

function redrawCanvas(r) {
    cleanCanvas();
    for (let shot of shots) {
        drawShot(shot.x, shot.y, shot.r, shot.hit)
    }
}

function getShotPosition(event, r) {
    const drawnX = event.offsetX;
    const drawnY = event.offsetY;
    return { x: drawnToRealX(drawnX, r), y: drawnToRealY(drawnY, r) };
}

function sendShotFromCanvas(x, y) {
    document.getElementById("graph-x").value = x;
    document.getElementById("graph-y").value = y;
    document.getElementById("graph-submit").click();
}

document.addEventListener("DOMContentLoaded", () => {
    canvas = document.getElementById("aim-top");
    cleanCanvas();
    canvas.onclick = (e) => {
        const realCoords = getShotPosition(e, 5);
        if (!isFinite(realCoords.x) || !isFinite(realCoords.y)) {
            return;
        }
        console.log(realCoords);
        sendShotFromCanvas(realCoords.x, realCoords.y);
    }
});
