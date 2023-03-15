package main
import (
    "fmt"
    "encoding/csv"
    "log"
    "os"
    "time"
    "github.com/gocolly/colly"
    "github.com/go-co-op/gocron"
)

type Book struct {
    Title string
    Price string
}

func BooksScraper() {
    fmt.Println("Start Scrapping")

    // Create file
    file, err := os.Create("export.csv")
    if err != nil {
        log.Fatal(err)
    }
    defer file.Close()

    // Declaration for writer
    writer := csv.NewWriter(file)
    defer writer.Flush()

    // Headers before rows of data
    headers := []string{"Title", "Price"}
    writer.Write(headers)

    // --- Web Scraping Framework ---
    //  New Collector with parameter setting the domain accessing
    c := colly.NewCollector(
        colly.AllowedDomains("books.toscrape.com"),
    )

    // OnRequest Event is raised and allows tracking of which urls at the time
    // Annonnymous function is called once OnRequest has been raised
    c.OnRequest(func(r *colly.Request) {
        fmt.Println("Visiting", r.URL)
    })

    // OnResponse
    c.OnResponse(func(r *colly.Response) {
        fmt.Println("Status Code: ",r.StatusCode)
    })

//     //     OnHTML event can be used to see specific html elements found
//     c.OnHTML("title", func(e *colly.HTMLElement) {
//         fmt.Println(e.Text)
//     })

//     // Non-Struct Approach
//     c.OnHTML(".product_pod", func(e *colly.HTMLElement) {
//         // ChildAttr function(CSS Selector, name of attribute)
//         title := e.ChildAttr(".image_container img", "alt")
//         price := e.ChildText(".price_color")
//         fmt.Println("TItle - ", title)
//         fmt.Println("Price - ", price)
//     })

    // Using Structs
    c.OnHTML(".product_pod", func(e *colly.HTMLElement) {
        book := Book{}
        book.Title = e.ChildAttr(".image_container img", "alt")
        book.Price = e.ChildText(".price_color")
        fmt.Println( book.Title, book.Price)
        row := []string{book.Title, book.Price}
        writer.Write(row)
    })

    // Pagination
    c.OnHTML(".next > a", func(e *colly.HTMLElement) {
        nextPage := e.Request.AbsoluteURL(e.Attr("href"))
        c.Visit(nextPage)
    })

    c.Visit("https://books.toscrape.com")

    fmt.Println("Done")
}


func main() {
    my_scheduler := gocron.NewScheduler(time.UTC)
    my_scheduler.Every(2).Minute().Do(BooksScraper)
    my_scheduler.StartBlocking()
}

// Lessons Learnt
// - Can not have unused imports or references otherwise spits out error
