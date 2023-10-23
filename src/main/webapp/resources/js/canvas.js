let canvas = null
const WIDTH = 400
const HEIGHT = 400
const RADIUS = 120
const DOT_RADIUS = 3

const HIT_COLOR_FOR_MATCHING_R = "#94bc0e"
const MISS_COLOR_FOR_MATCHING_R = "#d6001e"
const R_EPSILON = 1e-6

shots = []

function getCanvas() {
    return canvas
}

function setCanvas(newCanvas) {
    canvas = newCanvas
}

function cleanCanvas(canvas) {
    const ctx = canvas.getContext('2d', {alpha: true})
    ctx.clearRect(0, 0, 400, 400);
}

function realToDrawnX(x, r) {
    return WIDTH / 2 + RADIUS * x / r
}

function realToDrawnY(y, r) {
    return HEIGHT / 2 - RADIUS * y / r
}

function drawnToRealX(x, r) {
    return (x - WIDTH / 2) * r / RADIUS
}

function drawnToRealY(y, r) {
    return -(y - HEIGHT / 2) * r / RADIUS
}

function drawShot(canvas, r, shot) {
    const ctx = canvas.getContext('2d', {alpha: true})
    if (!shot) return
    if (shot.x == null || shot.y == null || shot.r == null || shot.hit == null) return

    const x = parseFloat(shot.x)
    const y = parseFloat(shot.y)
    const rShot = parseFloat(shot.r)
    const hit = shot.hit

    const xDrawn = realToDrawnX(x, r)
    const yDrawn = realToDrawnY(y, r)
    if (!isFinite(xDrawn) || !isFinite(yDrawn)) {
        return
    }

    let shotColor = hit ? HIT_COLOR_FOR_MATCHING_R : MISS_COLOR_FOR_MATCHING_R
    if (Math.abs(r - rShot) >= R_EPSILON) shotColor += "80"

    ctx.beginPath()
    ctx.arc(xDrawn, yDrawn, DOT_RADIUS, 0, 2 * Math.PI, false)
    ctx.fillStyle = shotColor
    ctx.fill()
}

function redrawCanvas(canvas, r, shots) {
    const ctx = canvas.getContext('2d', {alpha: true})
    cleanCanvas(canvas)
    shots.forEach((shot) => {
        drawShot(canvas, r, shot)
    })
}

function getShotPosition(canvas, r, event) {
    const drawnX = event.offsetX
    const drawnY = event.offsetY
    return {x: drawnToRealX(drawnX, r), y: drawnToRealY(drawnY, r)}
}

function sendShotFromCanvas(x, y) {
    document.getElementById("graph-x").value = x
    document.getElementById("graph-y").value = y
    document.getElementById("graph-submit").click()
}

document.addEventListener("DOMContentLoaded", () => {
    canvas = document.getElementById("aim-top")
    setCanvas(canvas)
    cleanCanvas(canvas)
    canvas.onclick = (e) => {
        const realCoords = getShotPosition(canvas, 5, e)
        if (!isFinite(realCoords.x) || !isFinite(realCoords.y)) {
            return
        }
        console.log(realCoords)
        sendShotFromCanvas(realCoords.x, realCoords.y)
    }
})