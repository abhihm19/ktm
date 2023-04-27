const names = ["abhi", "jona", "jake"]

function myFun() {
    const arr = ['a','b','c','d','e','f']
    return arr;
}

const [x,y] = myFun()
const arrSecond = [...names]
console.log(x + " " + y)

const obj1 = {
    name: "myname",
    age: 24,
    address: {
        city: "hyd",
        pincode: 560073
    }
}

const { name: firstname, age = 25 } = obj1
console.log(age)
