package main
import (
    "fmt"
    "time"
    "github.com/go-co-op/gocron"
)

func My_Task_1() {
    fmt.Println("Hello Task 1")
}
// Example of having a go_cron execution
func main() {
    my_scheduler := gocron.NewScheduler(time.UTC)

    my_scheduler.Every(5).Seconds().Do(My_Task_1)
    my_scheduler.StartAsync()
    my_scheduler.StartBlocking()
}