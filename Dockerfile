# FROM ruby:alpine3.12
FROM ruby:2.7.2
COPY tabler-dev-2020-12-10 /tabler-dev

WORKDIR /tabler-dev
# TODO: fix up the below step
# RUN npm install
# RUN apk add --no-cache ruby
# RUN gem install bundler
# RUN bundle installer

# Ruby approach with alpine 
# RUN apk add --no-cache nodejs
# RUN apk add --no-cache npm
# RUN apk add --no-cache git
# RUN npm install

# Ruby 2.7.2

RUN apt-get update && apt-get install -y nodejs npm
RUN npm install
RUN gem install bundler
RUN bundle install
ENTRYPOINT [ "npm","run","start" ]
EXPOSE 3000