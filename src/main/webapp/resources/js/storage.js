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

function storageMain() {
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
    const xListElement = document.getElementById('x-validate')

    if (xStored && xStored.length) {
        let x = xStored[0]
        if (xElements[x]) {
            xElements[x].click()
        }
    }

    if (xListElement) {
        const xValues = JSON.stringify(
            Object.keys(xElements)
                .filter((it) => xElements[it].checked)
                .map((it) => parseInt(it, 10))
        )
        console.log('Values of X detected during page load: ' + xValues)
    }

    if (yStored) {
        yElement.value = yStored
    }

    for (let rElement of rElements) {
        if (rElement.value === rStored) {
            rElement.checked = true
            break
        }
    }

    Object.values(xElements).forEach((elem) => {
        elem.addEventListener("change", () => {
            const checkedXs = Object.values(xElements)
                .filter(el => el.checked)
                .map(el => parseInt(el.id.replace("x", ""), 10));
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
}

