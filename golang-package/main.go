package main

import "fmt"
import "golang-package/Packages/calcuator"

func main() {
    number1 := 9
    number2 := 5
    fmt.Println(calcuator.Add(number1,number2))
    fmt.Println(calculator.Substract(number1,number2))
}
