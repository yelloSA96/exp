package main
import (
    "fmt"
//     "time"
    "github.com/gocolly/colly"
)

func domain() {

    c := colly.NewCollector(
//     Stores it locally
        colly.CacheDir("./domain_cache"),
        colly.AllowedDomains("www.domain.com.au","domain.com.au"),
    )
//
//     c.Limit(&colly.LimitRule{
//         Delay:       2 * time.Second,
//         RamdomDelay: 2 * time.Second,
//     })

    c.OnRequest(func(r *colly.Request) {
        fmt.Println("Visiting", r.URL)
    })

    c.OnResponse(func(r *colly.Response) {
        fmt.Println("Status Code: ",r.StatusCode)
    })
// Article has a CSS class - css-3xqrp1
//
    c.OnHTML(".css-3xqrp1", func(e *colly.HTMLElement) {
        suburb := e.ChildText(".css-1czqru0 > h3")
        fmt.Println(suburb)
    })

    c.Visit("https://www.domain.com.au/auction-results/adelaide/?utm_source=auctionresults&utm_medium=email&utm_content=CTA-&utm_campaign=c-all-")

    fmt.Println("Done")
}


func main() {
    domain()
}

// Lesson Learnt
// - Delays help with anti-scraping measures such as IP banning