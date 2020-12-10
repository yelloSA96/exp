FROM ruby:alpine3.12
COPY tabler-dev-2020-12-10 /tabler-dev

WORKDIR /tabler-dev
# FROM alpine:3
# RUN apk add --update --no-cache alpine-sdk ruby nodejs npm git
# RUN npm install
# RUN gem install bundler
# RUN bundle install

RUN apk add --update --no-cache alpine-sdk nodejs npm git
RUN npm install
RUN gem install bundler
RUN bundle install
ENTRYPOINT [ "npm","run","start" ]
EXPOSE 3000