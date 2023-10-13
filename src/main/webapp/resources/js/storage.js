const DEFAULT_R_VALUE = 1

function getStoredXs() {
    return json.parse(localStorage.getItem('x')) || []
}

function getStoredY() {
    return localStorage.getItem('y')
}

function getStoredR() {
    return localStorage.getItem('r')
}

function getR() {
    for (let rElem of document.getElementsByName("r")) {
        if (rElem.checked) {
            return rElem.value;
        }
    }
    return getStoredR() ?? DEFAULT_R_VALUE;
}

function setStoredXs(xs) {
    localStorage.setItem('x', JSON.stringify(xs));
}

function setStoredY(y) {
    localStorage.setItem('y', y);
}

function setStoredR(r) {
    localStorage.setItem('r', r);
}

document.addEventListener('DOMContentLoaded', () => {
    const storedXs = [...getStoredXs()]
    const storedY = getStoredY()
    const storedR = getStoredR() ?? DEFAULT_R_VALUE

    const xElements = [...document.getElementsByName("x")]
    const yElement = document.getElementById("y")
    const rElements = [...document.getElementsByName("r")]

    xElements.forEach((xElem) => {
        if (storedXs.includes(xElem.value)) {
            xElem.checked = true
        }
    })

    if (storedY) {
        yElement.value = storedY
    }

    rElements.forEach((rElem) => {
        if (rElem.value == storedR) {
            rElem.checked = true
        }
    })
})