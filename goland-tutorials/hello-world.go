package main

// Import popular fmt package - includes formatting, printing to console
// import "fmt"

// Multipe packages imported
import (
    "fmt"
    "math/rand"
)

// run code - go run hello-world.go
func main() {
    fmt.Println("Hello World!")
    fmt.Println(rand.Intn(25))
}