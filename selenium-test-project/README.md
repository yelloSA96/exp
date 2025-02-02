# Selenium Module 
This module is for playing around with selenium and the selenium driver. 


## Real Estate Research
For the [Domain - Melbourne Auctions](https://www.domain.com.au/auction-results/melbourne), we can find that the page is broken up into different div #A, div #B, and so on to div #Y. Within each div #A, it has articles.
```html
div #A - Symbolising the alphabetical section
  article
    header - Suburb name
    div
    div
  article
```
### Execution
To run `realEstate` intelliJ Idea run configuration. 

## Supermarket Selenium Research
Supermarkets typically have a special section. 
- [ ] [FlyBuys Special Offers for you](https://experience.flybuys.com.au/offers/category/?cat=Offers%20just%20for%20you)
### Coles
Link - https://www.coles.com.au/on-special

Breakdown of the structure
Example:
![img.png](images/product-design.png)

Overview 
```html
<div data-testid="specials-product-tiles" class="..">
    <section data-testid="product-tile" class="jiberish..blah..blah">
        <div class="sc-cb339e35-5 gvhDoj coles-targeting-ProductTileHeaderWrapper">
            <header class="">
                <div class="product_header">
                    Product Image Area ..... (look below (A))
                </div>

            </header>
        </div>
        <div>
            Product pricing and details ....... (look below (C))
        </div>
    </section>
</div>
```
(A)
```html
                    <div class="product__image_area" >
                        <a class="product__link product__image" aria-label="" href="/product/coles-lamb-cutlets-approx.-775g-1757810">
                            <span class="sc-51f151be-0 irGhqP">
                                <img alt="Lindt Easter Medley Chocolate Pouch Bag | 415g" src="link">
                            </span>
                        </a>
                    </div>
```

(B) 
```html
                <div class="product__message-title_area">

                </div>
```

(C) Product pricing and details breakdown is shown below
```html
<div class="product__cta_section" bis_skin_checked="1">
    <div class="product__pricing_area hide-on-small-screen" data-testid="large-screen-pricing" bis_skin_checked="1">
        <section class="sc-6ec405eb-0 fQCQSI product__pricing product__pricing" data-testid="product_price">
            <div class="price" >
                <span class="price__value" data-testid="product-pricing" aria-label="Price $18.00">$18.00</span>
                <span data-testid="badge-wrapper" type="saving" class="sc-b5559c87-0 dSFfxD badge is-small badge is-small">
                    <section aria-label class="badge-label">Save $18.00</section>
                </span>
            </div>
            <div class="price__calculation_method">
                "$4.34 per 100g"
                <span class="price_was" | Was $36.00</span>
            </div>
        </section>
    </div>
</div>

```

### Woolworths


Pschology of priorities(ordered below)
How cheap
Picture of the item
Actual price 

Implement my value costing

#### Feautures
- [ ] Looking at items based on the category e.g. meats, what other meats are on special too? 
- [ ] Filtering of what is the most discounted 
- [ ] Comparing against other supermarkets
- [ ]


## References
[Browser Support](https://www.selenium.dev/documentation/webdriver/browsers/)
[Chrome Specific Options & Capabilities](https://chromedriver.chromium.org/capabilities)