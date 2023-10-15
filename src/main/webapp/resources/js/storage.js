function getStoredXs() {
    return JSON.parse(localStorage.getItem('x')) || []
}

function setStoredXs(xs) {
    localStorage.setItem('x', JSON.stringify(xs))
}

function xChangeHandler(values) {
    setStoredXs(values)
}

function getStoredY() {
    return localStorage.getItem('y')
}

function setStoredY(y) {
    localStorage.setItem('y', y)
}

function yChangeHandler(value) {
    setStoredY(value)
}

function getStoredR() {
    return localStorage.getItem('r') || 1
}

function setStoredR(r) {
    localStorage.setItem('r', r)
}

function rChangeHandler(value) {
    setStoredR(value)
}

document.addEventListener("DOMContentLoaded", () => {
    const xStored = getStoredXs()
    const yStored = getStoredY()
    const rStored = getStoredR()

    const xElements = [...document.getElementsByName('x')]
    const yElement = document.getElementById('y')
    const rElements = [...document.getElementsByName('r')]

    xElements.forEach((elem) => {
        if (elem.value in xStored) elem.checked = true
    })
    if (yStored) {
        yElement.value = yStored
    }
    for (let rElement of rElements) {
        if (rElement.value == rStored) {
            rElement.checked = true
            break
        }
    }

    xElements.forEach((elem) => {
        elem.addEventListener("change", () => {
            const checkedXs = xElements.filter(el => el.checked).map(el => el.value)
            console.log(`X changed to: ${checkedXs}`)
            xChangeHandler(checkedXs)
        })
    })

    yElement.addEventListener("change", () => {
        const newY = yElement.value
        console.log(`Y changed to: ${newY}`)
        yChangeHandler(newY)
    })

    rElements.forEach((elem) => {
        elem.addEventListener("change", () => {
            const newR = elem.value
            console.log(`R changed to: ${newR}`)
            rChangeHandler(newR)
        })
    })
})