const CANVAS_TO_DIAMETER_FACTOR = 1.5
const AXIS_LENGTH_TO_DIAMETER_FACTOR = 1.25
const LINE_WIDTH_PX = 2
const MARK_LENGTH_PX = 10
const SQRT_HALF = Math.sqrt(0.5)
const FONT_HEIGHT = 18

function realToDrawnX(xToConvert, r, width) {
    return (xToConvert - width / 2) / width * CANVAS_TO_DIAMETER_FACTOR * r
}

function realToDrawnY(yToConvert, r, height) {
    return -(yToConvert - height / 2) / height * CANVAS_TO_DIAMETER_FACTOR * r
}

function drawnToRealX(xToConvert, r, width) {
    return width * xToConvert / CANVAS_TO_DIAMETER_FACTOR / 2 / r  + width / 2
}

function drawnToRealY(yToConvert, r, height) {
    return -height * yToConvert / CANVAS_TO_DIAMETER_FACTOR / 2 / r + height / 2
}

function dot(ctx, x, y, r, hit, width, height){
    ctx.fillStyle = hit ? "#94bc0e" : "#b6001e";
    ctx.beginPath()
    ctx.arc(
        drawnToRealX(x, r, width), drawnToRealY(y, r, height),
        5, 0, 2 * Math.PI, true
    );
    ctx.fill();
}

function redrawGraph(r) {
    const canvas = document.getElementById("aim")
    const ctx = canvas.getContext("2d")

    const width = canvas.clientWidth
    const height = canvas.clientHeight

    const radius = width / CANVAS_TO_DIAMETER_FACTOR / 2

    ctx.clearRect(0, 0, width, height)
    ctx.beginPath()
    ctx.fillStyle = "#069d9f80"

    // Отрисовка и заливка области
    ctx.arc(
        drawnToRealX(0, r, width), drawnToRealY(0, r, height),
        radius / 2, Math.PI / 2, Math.PI,
        false
    )
    ctx.lineTo(drawnToRealX(-r/2, r, width), drawnToRealY(0, r, height))
    ctx.lineTo(drawnToRealX(-r/2, r, width), drawnToRealY(r, r, height))
    ctx.lineTo(drawnToRealX(0, r, width), drawnToRealY(r, r, height))
    ctx.lineTo(drawnToRealX(0, r, width), drawnToRealY(0, r, height))
    ctx.lineTo(drawnToRealX(r, r, width), drawnToRealY(0, r, height))
    ctx.lineTo(drawnToRealX(0, r, width), drawnToRealY(-r, r, height))
    ctx.fill()

    // Отрисовка оси X и стрелочки
    ctx.beginPath()
    ctx.lineWidth = LINE_WIDTH_PX
    ctx.strokeStyle = "#f4f4f4"
    ctx.moveTo(
        drawnToRealX(-AXIS_LENGTH_TO_DIAMETER_FACTOR, 1, width),
        drawnToRealY(0, 1, height)
    )
    ctx.lineTo(
        drawnToRealX(AXIS_LENGTH_TO_DIAMETER_FACTOR, 1, width),
        drawnToRealY(0, 1, height)
    )
    ctx.lineTo(
        drawnToRealX(AXIS_LENGTH_TO_DIAMETER_FACTOR, 1, width) - SQRT_HALF * MARK_LENGTH_PX,
        drawnToRealY(0, 1, height) - SQRT_HALF * MARK_LENGTH_PX
    )
    ctx.moveTo(
        drawnToRealX(AXIS_LENGTH_TO_DIAMETER_FACTOR, 1, width) - SQRT_HALF * MARK_LENGTH_PX,
        drawnToRealY(0, 1, height) + SQRT_HALF * MARK_LENGTH_PX
    )
    ctx.lineTo(
        drawnToRealX(AXIS_LENGTH_TO_DIAMETER_FACTOR, 1, width),
        drawnToRealY(0, 1, height)
    )
    ctx.stroke()

    // Отрисовка оси Y и стрелочки
    ctx.beginPath()
    ctx.moveTo(
        drawnToRealX(0, 1, height),
        drawnToRealY(-AXIS_LENGTH_TO_DIAMETER_FACTOR, 1, height)
    )
    ctx.lineTo(
        drawnToRealX(0, 1, height),
        drawnToRealY(AXIS_LENGTH_TO_DIAMETER_FACTOR, 1, height)
    )
    ctx.lineTo(
        drawnToRealX(0, 1, height) - SQRT_HALF * MARK_LENGTH_PX,
        drawnToRealY(AXIS_LENGTH_TO_DIAMETER_FACTOR, 1, height) + SQRT_HALF * MARK_LENGTH_PX
    )
    ctx.moveTo(
        drawnToRealX(0, 1, height) + SQRT_HALF * MARK_LENGTH_PX,
        drawnToRealY(AXIS_LENGTH_TO_DIAMETER_FACTOR, 1, height) + SQRT_HALF * MARK_LENGTH_PX
    )
    ctx.lineTo(
        drawnToRealX(0, 1, height),
        drawnToRealY(AXIS_LENGTH_TO_DIAMETER_FACTOR, 1, height)
    )
    ctx.stroke()

    // Отрисовка засечек на оси X
    ctx.beginPath()
    ctx.moveTo(
        drawnToRealX(-1, 1, width),
        drawnToRealY(0, 1, height) - MARK_LENGTH_PX / 2
    )
    ctx.lineTo(
        drawnToRealX(-1, 1, width),
        drawnToRealY(0, 1, height) + MARK_LENGTH_PX / 2
    )
    ctx.moveTo(
        drawnToRealX(-0.5, 1, width),
        drawnToRealY(0, 1, height) - MARK_LENGTH_PX / 2
    )
    ctx.lineTo(
        drawnToRealX(-0.5, 1, width),
        drawnToRealY(0, 1, height) + MARK_LENGTH_PX / 2
    )
    ctx.moveTo(
        drawnToRealX(0.5, 1, width),
        drawnToRealY(0, 1, height) - MARK_LENGTH_PX / 2
    )
    ctx.lineTo(
        drawnToRealX(0.5, 1, width),
        drawnToRealY(0, 1, height) + MARK_LENGTH_PX / 2
    )
    ctx.moveTo(
        drawnToRealX(1, 1, width),
        drawnToRealY(0, 1, height) - MARK_LENGTH_PX / 2
    )
    ctx.lineTo(
        drawnToRealX(1, 1, width),
        drawnToRealY(0, 1, height) + MARK_LENGTH_PX / 2
    )
    ctx.stroke()

    // Отрисовка засечек на оси Y
    ctx.beginPath()
    ctx.moveTo(
        drawnToRealX(0, 1, width) - MARK_LENGTH_PX / 2,
        drawnToRealY(-1, 1, height)
    )
    ctx.lineTo(
        drawnToRealX(0, 1, width) + MARK_LENGTH_PX / 2,
        drawnToRealY(-1, 1, height)
    )
    ctx.moveTo(
        drawnToRealX(0, 1, width) - MARK_LENGTH_PX / 2,
        drawnToRealY(-0.5, 1, height)
    )
    ctx.lineTo(
        drawnToRealX(0, 1, width) + MARK_LENGTH_PX / 2,
        drawnToRealY(-0.5, 1, height)
    )
    ctx.moveTo(
        drawnToRealX(0, 1, width) - MARK_LENGTH_PX / 2,
        drawnToRealY(0.5, 1, height)
    )
    ctx.lineTo(
        drawnToRealX(0, 1, width) + MARK_LENGTH_PX / 2,
        drawnToRealY(0.5, 1, height)
    )
    ctx.moveTo(
        drawnToRealX(0, 1, width) - MARK_LENGTH_PX / 2,
        drawnToRealY(1, 1, height)
    )
    ctx.lineTo(
        drawnToRealX(0, 1, width) + MARK_LENGTH_PX / 2,
        drawnToRealY(1, 1, height)
    )
    ctx.stroke()

    // Отрисовка обозначений осей
    ctx.fillStyle = "#f4f4f4"
    ctx.font = `${FONT_HEIGHT}px monospace`
    ctx.fillText(
        "x",
        drawnToRealX(AXIS_LENGTH_TO_DIAMETER_FACTOR, 1, width),
        drawnToRealY(0, 1, height) - MARK_LENGTH_PX
    )
    ctx.fillText(
        "y",
        drawnToRealX(0, 1, width) + MARK_LENGTH_PX,
        drawnToRealY(AXIS_LENGTH_TO_DIAMETER_FACTOR, 1, height)
    )
    let halfR = r ? (r / 2).toString() : "R/2"
    let strR = r ? r.toString() : "R"
    ctx.fillText(
        "-"+strR,
        drawnToRealX(-1, 1, width) - MARK_LENGTH_PX,
        drawnToRealY(0, 1, height) - MARK_LENGTH_PX
    )
    ctx.fillText(
        "-"+strR,
        drawnToRealX(0, 1, width) + MARK_LENGTH_PX,
        drawnToRealY(-1, 1, height) + MARK_LENGTH_PX
    )
    ctx.fillText(
        "-"+halfR,
        drawnToRealX(-0.5, 1, width) - MARK_LENGTH_PX,
        drawnToRealY(0, 1, height) - MARK_LENGTH_PX
    )
    ctx.fillText(
        "-"+halfR,
        drawnToRealX(0, 1, width) + MARK_LENGTH_PX,
        drawnToRealY(-0.5, 1, height) + MARK_LENGTH_PX
    )
    ctx.fillText(
        halfR,
        drawnToRealX(0.5, 1, width) - MARK_LENGTH_PX,
        drawnToRealY(0, 1, height) - MARK_LENGTH_PX
    )
    ctx.fillText(
        halfR,
        drawnToRealX(0, 1, width) + MARK_LENGTH_PX,
        drawnToRealY(0.5, 1, height) + MARK_LENGTH_PX
    )
    ctx.fillText(
        strR,
        drawnToRealX(1, 1, width) - MARK_LENGTH_PX,
        drawnToRealY(0, 1, height) - MARK_LENGTH_PX
    )
    ctx.fillText(
        strR,
        drawnToRealX(0, 1, width) + MARK_LENGTH_PX,
        drawnToRealY(1, 1, height) + MARK_LENGTH_PX
    )
}

function getCoordinates(e) {
    const r = parseFloat(getR())
    if (isFinite(r) && r > 0) {
        console.info(r)
        const rect = e.target.getBoundingClientRect()
        const x = realToDrawnX(e.clientX - rect.left, r, rect.width())
        const y = realToDrawnY(e.clientY - rect.top, r, rect.height())
        return { x: x, y: y }
    }
    return null
}

document.addEventListener('DOMContentLoaded', () => {
    redrawGraph();
    document.getElementById("aim").onclick = (e) => {
        const coord = getCoordinates(e)
        console.log(`x: ${coord.x}, y: ${coord.y}`)
    }
})