function getDateTime() {
    let date = new Date()
    return `${date.toLocaleDateString()}, ${date.toLocaleTimeString()}`
}

const CLOCK_UPDATE_PERIOD_MS = 6_000

window.onload = () => {
    const clock = document.getElementById("clock")
    clock.innerHTML = getDateTime()
    window.setInterval(() => {
        clock.innerHTML = getDateTime()
    }, CLOCK_UPDATE_PERIOD_MS)
}