FROM alpine:3
COPY tabler-dev-2020-12-10 /tabler
WORKDIR /tabler
RUN apk add --update --no-cache alpine-sdk ruby nodejs npm git
RUN npm install
RUN gem install bundler
RUN bundle install
ENTRYPOINT [ "npm","run","start" ]
EXPOSE 3000