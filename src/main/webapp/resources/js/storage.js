const X_MIN = -3
const X_MAX = 5

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

function arrayAddIfNotExists(arr, elem) {
    if (!arr.includes(elem)) {
        arr.push(elem)
    }
}

function arrayRemoveIfExists(arr, elem) {
    const index = arr.indexOf(elem);
    if (index !== -1) {
        arr.splice(index, 1)
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const xStored = getStoredXs()
    const yStored = getStoredY()
    const rStored = getStoredR()

    const xElements = {}
    for (let i = X_MIN; i <= X_MAX; ++i) {
        const elem = document.getElementById(`x${i}`)
        if (elem) {
            xElements[i] = elem
        }
    }
    const yElement = document.getElementById('y')
    const rElements = [...document.getElementsByName('r')]

    xStored.forEach((x) => {
        if (xElements[x]) {
            xElements[x].checked = true
        }
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

    Object.values(xElements).forEach((elem) => {
        elem.addEventListener("change", () => {
            const checkedXs = Object.values(xElements).filter(el => el.checked).map(el => parseInt(el.value, 10));
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
            if (elem.checked) {
                const newR = elem.value
                console.log(`R changed to: ${newR}`)
                rChangeHandler(newR)
            }
        })
    })
})