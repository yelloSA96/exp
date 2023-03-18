package main
import (
    "fmt"
    "log"
//     "time"
    "github.com/gocolly/colly"
    "github.com/gocolly/colly/debug"
)

func domain() {

    c := colly.NewCollector(
        colly.Debugger(&debug.LogDebugger{}),
//         colly.AllowedDomains("www.domain.com.au",
//             "domain.com.au",
//             "https://www.domain.com.au",
//             "https://domain.com.au",
//             "www.domain.com.au/",
//             "domain.com.au/",
//             "https://www.domain.com.au/",
//             "https://domain.com.au/"),
    )

    c.OnRequest(func(r *colly.Request) {
        fmt.Println("Visiting", r.URL)
    })

    c.OnError(func(_ *colly.Response, err error) {
        log.Println("Something went wrong:", err)
    })

    c.OnResponse(func(r *colly.Response) {
        fmt.Println("Status Code: ",r.StatusCode)
    })
    c.OnHTML(".css-3xqrp1", func(e *colly.HTMLElement) {
        suburb := e.ChildText(".css-1czqru0 > h3")
        fmt.Println(suburb)
    })

    c.Visit("https://domain.com.au")

    fmt.Println("Done")
}


func main() {
    domain()
}

// Lesson Learnt
// - Delays help with anti-scraping measures such as IP banning